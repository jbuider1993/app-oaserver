package com.app.producer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.util.ToolUtil;
import com.app.dao.AppSysUserDao;
import com.app.redis.JedisClientService;

/**
 * 
 * @author 
 *
 */
@RestController
public class AppSysUserController {
	
	@Autowired
	private JedisClientService jedisClient;
	@Autowired
	private AppSysUserDao appSysUserDao;

	/**
	 * 
	     * @Title: login
	     * @Description: 手机端用户登录
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/AppSysUserTokenController/queryMenuBySession")
	@ResponseBody
	public void login(HttpServletResponse response, @RequestParam String userToken) throws Exception{
		Map<String, Object> map = new HashMap<>();
		ToolUtil.sendMessageToPageComJson(response,"密码输入错误！", "-9999");
	}
	
}