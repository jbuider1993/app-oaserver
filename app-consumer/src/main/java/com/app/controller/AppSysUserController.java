package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AppSysUserService;
import com.app.service.AppSysUserTokenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/appsysuser", tags = "APP登录管理")
public class AppSysUserController {

	@Autowired
	private AppSysUserService appSysUserService;
	
	@Autowired
	private AppSysUserTokenService appSysUserTokenService;

	/**
	 * 
	     * @Title: login
	     * @Description: APP手机端用户登录
	     * @return String 返回类型
	     * 
	 */
	@PostMapping ("/appLogin")
	@ApiOperation(value = "APP手机端用户登录", notes = "APP手机端用户登录", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "name", value = "用户名", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true, paramType = "query")
	})
	public String login(String name, String password) {
		return appSysUserService.login(name, password); 
	}
	
	/**
	 * 
	     * @Title: querySysUserMation
	     * @Description: 从session中获取用户的信息
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sysUserMation")
	@ApiOperation(value = "从session中获取用户的信息", notes = "从session中获取用户的信息", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header") })
	public String querySysUserMation(@RequestHeader String userToken) {
		return appSysUserTokenService.querySysUserMation(userToken);
	}
	
	/**
	 * 
	     * @Title: logout
	     * @Description: 手机端用户注销登录
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/logout")
	@ApiOperation(value = "APP手机端用户注销", notes = "APP手机端用户注销", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header") })
	public String logout(@RequestHeader String userToken) {
		return appSysUserTokenService.logout(userToken);
	}
	
}