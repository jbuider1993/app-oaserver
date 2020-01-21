package com.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.hystrix.AppFileConsoleServiceHystrix;


@FeignClient(name= "app-pro-disk-cloud", fallback = AppFileConsoleServiceHystrix.class)
public interface AppFileConsoleService {
	
	@RequestMapping(value = "/queryFilesListByFolderId", method = RequestMethod.GET)
	public String queryFilesListByFolderId(
			@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "folderId") String folderId);
	
}
