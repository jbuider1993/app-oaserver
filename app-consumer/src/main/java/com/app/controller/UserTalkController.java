package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.UserTalkRemoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "测试类1")
public class UserTalkController {

	@Autowired
	private UserTalkRemoteService userTalkRemoteService;

	@GetMapping("/hello1")
	@ApiOperation(value = "/hello1", notes = "测试")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "name", value = "用户名", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "query")
	})
	public String index(String name, String userToken) {
		return userTalkRemoteService.hello1(name, userToken);
	}

}