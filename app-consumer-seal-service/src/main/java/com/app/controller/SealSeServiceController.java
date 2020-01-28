package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.SealServiceOrderEntity;
import com.app.service.SealSeServiceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "/sealseservice", tags = "售后服务管理")
public class SealSeServiceController {
	
	@Autowired
	private SealSeServiceService sealSeServiceService;
	
	/**
	 * 
	     * @Title: sealServiceOrder
	     * @Description: 获取全部工单列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceOrder")
	@ApiOperation(value = "获取全部工单列表", notes = "获取全部工单列表", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String querySealSeServiceList(int limit, int page) {
		return sealSeServiceService.querySealSeServiceList(limit, page); 
	}
	
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
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String querySealServiceWaitToWorkOrder(int limit, int page) {
		return sealSeServiceService.querySealServiceWaitToWorkOrder(limit, page); 
	}
	
	/**
	 * 
	     * @Title: sealServiceWaitToReceiveOrder
	     * @Description: 获取当前登录人待接单的列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceWaitToReceiveOrder")
	@ApiOperation(value = "获取当前登录人待接单的列表", notes = "获取当前登录人待接单的列表", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String querySealSeServiceWaitToReceiveList(@RequestHeader String userToken, int limit, int page) {
		return sealSeServiceService.querySealSeServiceWaitToReceiveList(userToken, limit, page); 
	}
	
	/**
	 * 
	     * @Title: sealServiceWaitToSignonOrder
	     * @Description: 获取当前登录人待签到的列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceWaitToSignonOrder")
	@ApiOperation(value = "获取当前登录人待签到的列表", notes = "获取当前登录人待签到的列表", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String querySealSeServiceWaitToSignonList(@RequestHeader String userToken, int limit, int page) {
		return sealSeServiceService.querySealSeServiceWaitToSignonList(userToken, limit, page); 
	}
	
	/**
	 * 
	     * @Title: sealServiceWaitToFinishOrder
	     * @Description: 获取当前登录人待完工的列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceWaitToFinishOrder")
	@ApiOperation(value = "获取当前登录人待完工的列表", notes = "获取当前登录人待完工的列表", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String querySealSeServiceWaitToFinishList(@RequestHeader String userToken, int limit, int page) {
		return sealSeServiceService.querySealSeServiceWaitToFinishList(userToken, limit, page); 
	}
	
	/**
	 * 
	     * @Title: sealServiceWaitToAssessmentOrder
	     * @Description: 获取当前登录人待评价的列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceWaitToAssessmentOrder")
	@ApiOperation(value = "获取当前登录人待评价的列表", notes = "获取当前登录人待评价的列表", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String querySealSeServiceWaitToAssessmentList(@RequestHeader String userToken, int limit, int page) {
		return sealSeServiceService.querySealSeServiceWaitToAssessmentList(userToken, limit, page); 
	}
	
	/**
	 * 
	     * @Title: sealServiceAllWaitToAssessmentOrder
	     * @Description: 获取全部待评价的列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceAllWaitToAssessmentOrder")
	@ApiOperation(value = "获取全部待评价的列表", notes = "获取全部待评价的列表", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String queryAllSealSeServiceWaitToAssessmentList(int limit, int page) {
		return sealSeServiceService.queryAllSealSeServiceWaitToAssessmentList(limit, page); 
	}
	
	/**
	 * 
	     * @Title: sealServiceAllWaitToCheckOrder
	     * @Description: 获取全部待审核的列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceAllWaitToCheckOrder")
	@ApiOperation(value = "获取全部待审核的列表", notes = "获取全部待审核的列表", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String queryAllSealSeServiceWaitToCheckList(int limit, int page) {
		return sealSeServiceService.queryAllSealSeServiceWaitToCheckList(limit, page); 
	}
	
	/**
	 * 
	     * @Title: sealServiceAllFinishedOrder
	     * @Description: 获取全部已完成的列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceAllFinishedOrder")
	@ApiOperation(value = "获取全部已完成的列表", notes = "获取全部已完成的列表", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query") })
	public String queryAllSealSeServiceFinishedList(int limit, int page) {
		return sealSeServiceService.queryAllSealSeServiceFinishedList(limit, page); 
	}
	
	/**
	 * 
	     * @Title: sealServiceOrderDetail
	     * @Description: 获取工单详情
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/sealServiceOrderDetail")
	@ApiOperation(value = "获取工单详情", notes = "获取工单详情", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "id", value = "工单id", dataType = "String", required = true, paramType = "query") })
	public String querySealSeServiceDetail(String id) {
		return sealSeServiceService.querySealSeServiceDetail(id); 
	}
	
	/**
	 * 
	     * @Title: sealServiceOrder
	     * @Description: 新增工单
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/sealServiceOrder")
	@ApiOperation(value = "新增工单", notes = "新增工单", produces = "application/json")
	public String insertSealSeServiceMation(@ApiParam(name = "sealServiceOrderEntity", value = "售后订单模型") SealServiceOrderEntity sealServiceOrderEntity) {
		return sealSeServiceService.insertSealSeServiceMation(sealServiceOrderEntity); 
	}
	
}
