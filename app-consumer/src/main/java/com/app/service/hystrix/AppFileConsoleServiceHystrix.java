package com.app.service.hystrix;

import org.springframework.stereotype.Component;

import com.app.service.AppFileConsoleService;

@Component
public class AppFileConsoleServiceHystrix implements AppFileConsoleService{

	@Override
	public String queryFilesListByFolderId(String userToken, String folderId) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
}
