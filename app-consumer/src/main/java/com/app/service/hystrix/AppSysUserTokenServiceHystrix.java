package com.app.service.hystrix;

import org.springframework.stereotype.Component;

import com.app.service.AppSysUserTokenService;

@Component
public class AppSysUserTokenServiceHystrix implements AppSysUserTokenService{
	
	@Override
	public String querySysUserMation(String userToken) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String logout(String userToken) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
}
