package com.app.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.hystrix.HelloRemoteHystrix;

/**
 * 添加fallback属性（在HelloRemote类添加指定fallback类，在服务熔断的时候返回fallback类中的内容）
 * @author 卫志强
 */
@FeignClient(name= "app-producer", fallback = HelloRemoteHystrix.class)
public interface HelloRemoteService {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
    
}