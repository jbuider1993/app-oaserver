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

/**
 * 
 * @author 卫志强
 *
 */
@RestController
public class HelloController {
	
	@Autowired
	private JedisClientService jedisService;

	@RequestMapping("/hello")
	@ResponseBody
	public void index(HttpServletResponse response, @RequestParam String name){
		jedisService.set("name", "张三111");
		System.out.println("producer:" + name);
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		ToolUtil.sendMessageToPageComJson(response, map);
	}
	
}