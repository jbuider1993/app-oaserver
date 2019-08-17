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
import com.app.redis.JedisClientService;

@RestController
public class UserTalkController {
	
	@Autowired
	private JedisClientService jedisService;

	@RequestMapping("/hello1")
	@ResponseBody
	public void index(HttpServletResponse response, String name, String userToken){
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("userToken", userToken);
		ToolUtil.sendMessageToPageComJson(response, map);
	}
	
}
