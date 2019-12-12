package com.app.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.hystrix.AdministrativeManagementServiceHystrix;

/**
 * 添加fallback属性（在HelloRemote类添加指定fallback类，在服务熔断的时候返回fallback类中的内容）
 * @author 
 */
@FeignClient(name= "app-producer-hasauth", fallback = AdministrativeManagementServiceHystrix.class)
public interface AdministrativeManagementService {

    @RequestMapping(value = "/selectAllConferenceRoomMation", method = RequestMethod.GET)
    public String selectAllConferenceRoomMation(@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page, 
    		@RequestParam(value = "minCapacity") int minCapacity, 
    		@RequestParam(value = "maxCapacity") int maxCapacity);
 
}
