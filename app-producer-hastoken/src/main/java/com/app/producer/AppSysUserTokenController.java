package com.app.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.app.common.util.ToolUtil;
import com.app.redis.JedisClientService;

/**
 * 
 * @author 卫志强
 *
 */
@RestController
public class AppSysUserTokenController {
	
	@Autowired
	private JedisClientService jedisClient;

	/**
	 * 
	     * @Title: querySysUserMation
	     * @Description: 从session中获取用户拥有的菜单信息
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/sysUserMation")
	public void querySysUserMation(HttpServletResponse response, @RequestParam String userToken) throws Exception{
		if(userToken.contains(",")){
        	userToken = userToken.substring(0, userToken.indexOf(","));
        }
		String allMenuMation = jedisClient.get("allMenuMation:" + userToken);
		List<Object> list =JSON.parseArray(allMenuMation);
        List< Map<String,Object>> listmap = new ArrayList<Map<String,Object>>();
        for (Object object : list){
	        Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
	        listmap.add(ret);
        }
		ToolUtil.sendMessageToPageComJson(response, listmap);
	}
	
	/**
	 * 
	     * @Title: deleteUserMationBySession
	     * @Description: 手机端用户注销登录
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PostMapping("/logout")
	@ResponseBody
	public void deleteUserMationBySession(HttpServletResponse response, @RequestParam String userToken) throws Exception{
		jedisClient.del("userMation:" + userToken);
		jedisClient.del("allMenuMation:" + userToken);
		jedisClient.del("authPointsMation:" + userToken);
		ToolUtil.sendMessageToPageComJson(response, "注销成功", "0");
	}
	
}