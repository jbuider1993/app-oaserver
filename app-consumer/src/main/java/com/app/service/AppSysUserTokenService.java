package com.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.service.hystrix.AppSysUserTokenServiceHystrix;

/**
 * 添加fallback属性（在HelloRemote类添加指定fallback类，在服务熔断的时候返回fallback类中的内容）
 * @author 
 */
@FeignClient(name= "app-pro-user", fallback = AppSysUserTokenServiceHystrix.class)
public interface AppSysUserTokenService {

    @RequestMapping(value = "/sysUserMation", method = RequestMethod.GET)
	public String querySysUserMation(@RequestHeader(value = "userToken") String userToken);
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(@RequestHeader(value = "userToken") String userToken);
    
}
