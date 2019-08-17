package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.HelloRemoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "测试类")
public class ConsumerController {

	@Autowired
	private HelloRemoteService HelloRemote;

	@GetMapping("/hello")
	@ApiOperation(value = "/hello", notes = "测试")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "用户名", dataType = "String", required = true, paramType = "query") })
	public String index(String name) {
		return HelloRemote.hello(name);
	}

}