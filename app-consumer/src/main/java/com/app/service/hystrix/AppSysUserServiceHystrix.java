package com.app.service.hystrix;

import org.springframework.stereotype.Component;

import com.app.service.AppSysUserService;

@Component
public class AppSysUserServiceHystrix implements AppSysUserService {

	@Override
	public String login(String name, String password) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
}
