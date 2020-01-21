package com.app.service.hystrix;

import org.springframework.stereotype.Component;

import com.app.service.SealSeServiceService;

@Component
public class SealSeServiceServiceHystrix implements SealSeServiceService{

	@Override
	public String querySealServiceWaitToWorkOrder(String userToken, int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

}
