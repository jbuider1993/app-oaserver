package com.app.service.hystrix;

import org.springframework.stereotype.Component;

import com.app.service.ConferenceRoomService;

@Component
public class ConferenceRoomServiceHystrix implements ConferenceRoomService{
	
	@Override
	public String selectAllConferenceRoomMation(String userToken, int limit, int page, int minCapacity, int maxCapacity) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

}
