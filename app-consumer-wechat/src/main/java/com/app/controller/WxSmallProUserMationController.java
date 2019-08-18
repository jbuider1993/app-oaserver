package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.WxSmallProUserMationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "微信用户登录类")
public class WxSmallProUserMationController {

	@Autowired
	private WxSmallProUserMationService appSysUserTokenService;

	/**
	 * 
	     * @Title: queryUserMationByOpenId
	     * @Description: 根据openId获取用户信息
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/post/WxSmallProUserMationController/queryUserMationByOpenId")
	@ApiOperation(value = "/post/WxSmallProUserMationController/queryUserMationByOpenId", notes = "根据openId获取用户信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "openId", value = "微信用户openId", dataType = "String", required = true, paramType = "query") })
	public String queryUserMationByOpenId(String openId) {
		return appSysUserTokenService.queryUserMationByOpenId(openId);
	}
	
}