package com.app.service.hystrix;

import org.springframework.stereotype.Component;

import com.app.service.SealSeServiceService;

@Component
public class SealSeServiceServiceHystrix implements SealSeServiceService{

	@Override
	public String querySealSeServiceList(int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String querySealServiceWaitToWorkOrder(int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String querySealSeServiceWaitToReceiveList(String userToken, int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String querySealSeServiceWaitToSignonList(String userToken, int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String querySealSeServiceWaitToFinishList(String userToken, int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String querySealSeServiceWaitToAssessmentList(String userToken, int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryAllSealSeServiceWaitToAssessmentList(int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryAllSealSeServiceWaitToCheckList(int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryAllSealSeServiceFinishedList(int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}


}
