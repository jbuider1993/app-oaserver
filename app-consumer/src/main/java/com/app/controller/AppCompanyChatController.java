package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AppCompanyChatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/userchat", tags = "通讯录管理")
public class AppCompanyChatController {

	@Autowired
	private AppCompanyChatService appCompanyChatService;

	/**
	 * 
	     * @Title: querycompanyDepartment
	     * @Description: 获取通讯录数据
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/compantUserChan")
	@ApiOperation(value = "获取通讯录数据", notes = "获取通讯录数据", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "query") })
	public String querycompanyDepartment(String userToken) {
		return appCompanyChatService.querycompanyDepartment(userToken); 
	}
	
}