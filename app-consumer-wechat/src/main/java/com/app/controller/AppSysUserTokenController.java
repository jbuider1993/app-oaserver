package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AppSysUserTokenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "菜单类")
public class AppSysUserTokenController {

	@Autowired
	private AppSysUserTokenService appSysUserTokenService;

	/**
	 * 
	     * @Title: queryMenuBySession
	     * @Description: 从session中获取用户拥有的菜单信息
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/post/AppSysUserTokenController/queryMenuBySession")
	@ApiOperation(value = "/post/AppSysUserTokenController/queryMenuBySession", notes = "从session中获取用户拥有的菜单信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "query") })
	public String queryMenuBySession(String userToken) {
		return appSysUserTokenService.queryMenuBySession(userToken);
	}
	
}