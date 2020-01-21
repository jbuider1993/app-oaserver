package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.SealSeServiceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/sealseservice", tags = "售后服务管理")
public class SealSeServiceController {
	
	@Autowired
	private SealSeServiceService sealSeServiceService;
	
	/**
	 * 
	     * @Title: sealServiceWaitToWorkOrder
	     * @Description: 获取全部待派工的工单列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceWaitToWorkOrder")
	@ApiOperation(value = "获取全部待派工的工单列表", notes = "获取全部待派工的工单列表", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String querySealServiceWaitToWorkOrder(@RequestHeader String userToken, int limit, int page) {
		return sealSeServiceService.querySealServiceWaitToWorkOrder(userToken, limit, page); 
	}
	
}
