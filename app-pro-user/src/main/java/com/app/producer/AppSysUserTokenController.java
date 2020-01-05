package com.app.producer;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.util.ToolUtil;
import com.app.redis.JedisClientService;

import net.sf.json.JSONObject;

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
	     * @Description: 从session中获取用户信息
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/sysUserMation")
	public void querySysUserMation(HttpServletResponse response, @RequestHeader String userToken) throws Exception{
		Map<String, Object> user = JSONObject.fromObject(jedisClient.get("userMation:" + userToken + "-APP"));
		ToolUtil.sendMessageToPageComJson(response, user);
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
	public void deleteUserMationBySession(HttpServletResponse response, @RequestHeader String userToken) throws Exception{
		jedisClient.del("userMation:" + userToken+ "-APP");
		jedisClient.del("allMenuMation:" + userToken);
		jedisClient.del("authPointsMation:" + userToken);
		ToolUtil.sendMessageToPageComJson(response, "注销成功", "0");
	}
	
}