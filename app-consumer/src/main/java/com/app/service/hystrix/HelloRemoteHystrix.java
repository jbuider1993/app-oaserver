package com.app.service.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.HelloRemoteService;

@Component
public class HelloRemoteHystrix implements HelloRemoteService {

	@Override
	public String hello(@RequestParam(value = "name") String name) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
}
