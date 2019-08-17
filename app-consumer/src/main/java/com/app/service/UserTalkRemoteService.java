package com.app.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.hystrix.UserTalkRemoteServiceHystrix;

/**
 * 添加fallback属性（在XXXRemote类添加指定fallback类，在服务熔断的时候返回fallback类中的内容）
 * @author 卫志强
 */
@FeignClient(name= "app-producer-hastoken", fallback = UserTalkRemoteServiceHystrix.class)
public interface UserTalkRemoteService {

	@RequestMapping(value = "/hello1")
    public String hello1(@RequestParam("name")String name, @RequestParam("userToken")String userToken);

}
