package com.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.entity.SealServiceOrderEntity;
import com.app.service.hystrix.SealSeServiceServiceHystrix;

/**
 * 添加fallback属性（在HelloRemote类添加指定fallback类，在服务熔断的时候返回fallback类中的内容）
 * @author 卫志强
 */
@FeignClient(name= "app-pro-seal-service", fallback = SealSeServiceServiceHystrix.class)
public interface SealSeServiceService {
	
	@RequestMapping(value = "/sealServiceOrder", method = RequestMethod.GET)
	public String querySealSeServiceList(
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);
	
	@RequestMapping(value = "/sealServiceWaitToWorkOrder", method = RequestMethod.GET)
	public String querySealServiceWaitToWorkOrder(
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);

	@RequestMapping(value = "/sealServiceWaitToReceiveOrder", method = RequestMethod.GET)
	public String querySealSeServiceWaitToReceiveList(
			@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);

	@RequestMapping(value = "/sealServiceWaitToSignonOrder", method = RequestMethod.GET)
	public String querySealSeServiceWaitToSignonList(
			@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);

	@RequestMapping(value = "/sealServiceWaitToFinishOrder", method = RequestMethod.GET)
	public String querySealSeServiceWaitToFinishList(
			@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);

	@RequestMapping(value = "/sealServiceWaitToAssessmentOrder", method = RequestMethod.GET)
	public String querySealSeServiceWaitToAssessmentList(
			@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);

	@RequestMapping(value = "/sealServiceAllWaitToAssessmentOrder", method = RequestMethod.GET)
	public String queryAllSealSeServiceWaitToAssessmentList(
			@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);

	@RequestMapping(value = "/sealServiceAllWaitToCheckOrder", method = RequestMethod.GET)
	public String queryAllSealSeServiceWaitToCheckList(
			@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);

	@RequestMapping(value = "/sealServiceAllFinishedOrder", method = RequestMethod.GET)
	public String queryAllSealSeServiceFinishedList(
			@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);

	@RequestMapping(value = "/sealServiceOrderDetail", method = RequestMethod.GET)
	public String querySealSeServiceDetail(@RequestParam(value = "id") String id);

	@RequestMapping(value = "/sealServiceOrder", method = RequestMethod.POST)
	public String insertSealSeServiceMation(@RequestBody SealServiceOrderEntity sealServiceOrderEntity);

}