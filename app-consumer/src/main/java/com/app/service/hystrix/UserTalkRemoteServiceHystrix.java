package com.app.service.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.UserTalkRemoteService;

@Component
public class UserTalkRemoteServiceHystrix implements UserTalkRemoteService {
	
	@Override
	public String hello1(@RequestParam("name") String name, @RequestParam("userToken")String userToken) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
}
