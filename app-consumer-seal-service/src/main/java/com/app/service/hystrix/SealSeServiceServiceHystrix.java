package com.app.service.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.SealSeServiceService;

@Component
public class SealSeServiceServiceHystrix implements SealSeServiceService{

	@Override
	public String querySealServiceWaitToWorkOrder(
			@RequestParam(value = "userToken") String userToken, 
			@RequestParam(value = "limit") int limit, 
			@RequestParam(value = "page") int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

}
