package com.app.common.auth.aop;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.app.common.auth.annotation.AuthAnnotation;
import com.app.redis.JedisClientService;

import net.sf.json.JSONArray;

@Aspect
@Component
public class AuthHandler implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthHandler.class);
	
	@Autowired
	public JedisClientService jedisClient;
	
	//是否开启权限校验
	@Value("${auth.annotation}")
	private boolean authAnnotation;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object handler) throws Exception {
		//是否开启权限校验
		if(authAnnotation){
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				// 获取方法上的注解
				AuthAnnotation requiredPermission = handlerMethod.getMethod().getAnnotation(AuthAnnotation.class);
				// 如果方法上的注解为空 则获取类的注解
				if (requiredPermission == null) {
					requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(AuthAnnotation.class);
				}
				// 如果标记了注解，则判断权限
				if (requiredPermission != null) {
					logger.info("权限校验");
					//分别送请求和cookies中获取usertoken
					String userToken = getUserToken(request);
					if(StringUtils.isNotBlank(requiredPermission.value()) && StringUtils.isNotBlank(userToken)){
						//注解权限点不为空
						//判断用户是否登录
						if(!jedisClient.exists("userMation:" + userToken + "-APP")){
							//未登录
							throw new RuntimeException("您还未登录，请先登录.");
						}
						// redis中获取该用户的权限信息 并判断是否有权限
						//所有权限信息
						List<Map<String, Object>> authPoints = JSONArray.fromObject(jedisClient.get("authPointsMation:" + userToken + "-APP").toString());
						if(authPoints(authPoints, requiredPermission.value())){
							return true;
						}else{
							throw new RuntimeException("您不具备该权限.");
						}
					}else{
						//注解权限点为空,则说明需要登录无需赋权就能访问
						if(StringUtils.isNotBlank(userToken)){
							return true;
						}else{
							throw new RuntimeException("您还未登录，请先登录.");
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 获取userToken
	 * @param request
	 * @return
	 */
	public String getUserToken(HttpServletRequest request){
		String userToken = request.getParameter("userToken");
		//从cookies中获取
		if(StringUtils.isBlank(userToken)){
			userToken = getCookiesByName(request, "userToken");
		}
		//从header中获取
		if(StringUtils.isBlank(userToken)){
			userToken = request.getHeader("userToken");
		}
		return userToken;
	}
	
	/**
	 * 从cookies获取内容
	 * @param request
	 * @param name
	 * @return
	 */
	public String getCookiesByName(HttpServletRequest request, String name){
		Cookie[] cookies =  request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(name)){
					return cookie.getValue();
				}
			}
		}
		return "";
	}
	
	/**
	 * 权限点校验
	 * @param authPoints
	 * @param key
	 * @return
	 */
	public boolean authPoints(List<Map<String, Object>> authPoints, String key){
		for(Map<String, Object> bean : authPoints){
			if(key.equals(bean.get("menuUrl").toString())){
				return true;
			}
		}
		return false;
	}
	
}
