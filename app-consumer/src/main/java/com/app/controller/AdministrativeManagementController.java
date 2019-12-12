package com.app.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AdministrativeManagementService;

@RestController
@Api(value = "/administrative", tags = "行政管理")
public class AdministrativeManagementController {

	@Autowired
	private AdministrativeManagementService administrativeManagementService;

	/**
	 * 
	     * @Title: selectAllConferenceRoomMation
	     * @Description: 查看合适的会议室
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/selectAllConferenceRoomMation")
	@ApiOperation(value = "查看合适的会议室", notes = "根据容纳人数查看合适的会议室", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "minCapacity", value = "容纳人数：m人 ~ n人", dataType = "Integer", required = false, paramType = "query"),
		@ApiImplicitParam(name = "maxCapacity", value = "容纳人数：m人 ~ n人", dataType = "Integer", required = false, paramType = "query")})
	public String selectAllConferenceRoomMation(@RequestHeader String userToken, int limit, int page, int minCapacity, int maxCapacity) {
		return administrativeManagementService.selectAllConferenceRoomMation(userToken, limit, page, minCapacity, maxCapacity); 
	}

}