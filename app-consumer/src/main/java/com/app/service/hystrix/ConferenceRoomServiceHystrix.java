package com.app.service.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.ConferenceRoomService;

@Component
public class ConferenceRoomServiceHystrix implements ConferenceRoomService{
	
	@Override
	public String selectAllConferenceRoomMation(
			@RequestParam(value = "userToken") String userToken, 
			@RequestParam(value = "limit") int limit, 
			@RequestParam(value = "page") int page, 
			@RequestParam(value = "minCapacity") int minCapacity, 
			@RequestParam(value = "maxCapacity") int maxCapacity) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

}
