package com.app.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.JobDiaryHasTokenService;
import com.app.service.JobDiaryService;

@RestController
@Api(value = "/job", tags = "日志管理")
public class JobDiaryController {

	@Autowired
	private JobDiaryService jobDiaryService;
	
	@Autowired
	private JobDiaryHasTokenService jobDiaryHasTokenService;

	/**
	 * 
	     * @Title: queryJobDiaryDayReceived
	     * @Description: 查看所有收到的日志
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/jobDiary")
	@ApiOperation(value = "查看所有收到的日志", notes = "日志包括日报、周报和月报，查看所有收到的日志", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "diaryType", value = "报表类型：1、日报，2、周报，3、月报", dataType = "Integer", required = false, paramType = "query")})
	public String queryJobDiaryDayReceived(@RequestHeader String userToken, int limit, int page, int diaryType) {
		return jobDiaryService.queryJobDiaryDayReceived(userToken, limit, page, diaryType); 
	}
	
	/**
	 * 
	     * @Title: insertDayJobDiary
	     * @Description: 发表日报
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/jobDiary")
	@ApiOperation(value = "发表日报", notes = "发表日报", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "completedJob", value = "今日完成工作", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "incompleteJob", value = "今日未完成工作", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "coordinaJob", value = "需协调工作", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobRemark", value = "今日工作备注", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobTitle", value = "日报主题", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userInfo", value = "接收人id拼接串，逗号隔开", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "enclosureInfo", value = "附件信息", dataType = "String", required = false, paramType = "query")})
	public String insertDayJobDiary(@RequestHeader String userToken, String completedJob, String incompleteJob, String coordinaJob, String jobRemark, String jobTitle, String userInfo, String enclosureInfo) {
		return jobDiaryService.insertDayJobDiary(userToken, completedJob, incompleteJob, coordinaJob, jobRemark, jobTitle, userInfo, enclosureInfo); 
	}
	
	/**
	 * 
	     * @Title: queryJobDiaryDayMysend
	     * @Description: 查看所有发出的日志
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/jobDiaryMySend")
	@ApiOperation(value = "查看所有发出的日志", notes = "日志包括日报、周报和月报，查看所有发出的日志", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "diaryType", value = "报表类型：1、日报，2、周报，3、月报", dataType = "Integer", required = false, paramType = "query")})
	public String queryJobDiaryDayMysend(@RequestHeader String userToken, int limit, int page, int diaryType) {
		return jobDiaryService.queryJobDiaryDayMysend(userToken, limit, page, diaryType); 
	}
	
	/**
	 * 
	     * @Title: editJobDiaryDayMysend
	     * @Description: 撤销我发出的日志
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/jobDiary")
	@ApiOperation(value = "撤销我发出的日志", notes = "日志包括日报、周报和月报，发出的日志未超过两个小时可撤销", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "日报Id", dataType = "String", required = true, paramType = "query")})
	public String editJobDiaryDayMysend(@RequestHeader String userToken, String rowId) {
		return jobDiaryService.editJobDiaryDayMysend(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: editMyReceivedJobDiary
	     * @Description: 删除我收到的日志
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/jobDiaryEdit")
	@ApiOperation(value = "删除我收到的日志", notes = "日志包括日报、周报和月报，删除我收到的日志", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "日志关联表Id", dataType = "String", required = true, paramType = "query")})
	public String editMyReceivedJobDiary(@RequestHeader String userToken, String rowId) {
		return jobDiaryService.editMyReceivedJobDiary(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: editJobDiaryDayMysendToDelete
	     * @Description: 删除我发出的日志
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/jobDiaryEditMySend")
	@ApiOperation(value = "删除我发出的日志", notes = "日志包括日报、周报和月报，删除我发出的日志", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "日志id", dataType = "String", required = true, paramType = "query")})
	public String editJobDiaryDayMysendToDelete(@RequestHeader String userToken, String rowId) {
		return jobDiaryService.editJobDiaryDayMysendToDelete(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: editDayJobDiary
	     * @Description: 提交撤回的日报
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/editDayJobDiary")
	@ApiOperation(value = "提交撤回的日报", notes = "提交撤回的日报", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "日报id", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "completedJob", value = "今日完成工作", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "incompleteJob", value = "今日未完成工作", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "coordinaJob", value = "需协调工作", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobRemark", value = "今日工作备注", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobTitle", value = "日报主题", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userInfo", value = "接收人id拼接串，逗号隔开", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "enclosureInfo", value = "附件信息", dataType = "String", required = false, paramType = "query")})
	public String editDayJobDiary(@RequestHeader String userToken, String rowId, String completedJob, String incompleteJob, String coordinaJob, String jobRemark, String jobTitle, String userInfo, String enclosureInfo) {
		return jobDiaryService.editDayJobDiary(userToken, rowId, completedJob, incompleteJob, coordinaJob, jobRemark, jobTitle, userInfo, enclosureInfo); 
	}
	
	/**
	 * 
	     * @Title: querySysEveUserStaff
	     * @Description: 查看所有有账户的员工列表
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/querySysEveUserStaff")
	@ApiOperation(value = "查看所有有账户的员工列表", notes = "查看所有有账号的员工用于下拉框选择", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userName", value = "员工姓名", dataType = "String", required = false, paramType = "query")})
	public String querySysEveUserStaff(@RequestHeader String userToken, int limit, int page, String userName) {
		return jobDiaryHasTokenService.querySysEveUserStaff(userToken, limit, page, userName); 
	}
	
	/**
	 * 
	     * @Title: queryJobDiaryDetails
	     * @Description: 阅读接收到的日报并改变阅读状态
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/queryJobDiaryDetails")
	@ApiOperation(value = "阅读接收到的日报并改变阅读状态", notes = "阅读接收到的日报并把阅读状态改变成已读", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "日报关联表Id", dataType = "String", required = true, paramType = "query")})
	public String queryJobDiaryDetails(@RequestHeader String userToken, String rowId) {
		return jobDiaryHasTokenService.queryJobDiaryDetails(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: selectMysendDetails
	     * @Description: 我发出的日报详情
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/selectMysendDetails")
	@ApiOperation(value = "我发出的日报详情", notes = "获取我的日报详情", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "日报Id", dataType = "String", required = true, paramType = "query")})
	public String selectMysendDetails(@RequestHeader String userToken, String rowId) {
		return jobDiaryHasTokenService.selectMysendDetails(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: insertWeekJobDiary
	     * @Description: 发表周报
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/insertWeekJobDiary")
	@ApiOperation(value = "发表周报", notes = "发表周报", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "completedJob", value = "已完成工作", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "thisWorkSummary", value = "本周工作总结", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "nextWorkPlan", value = "下周工作计划", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "coordinaJob", value = "需协调工作", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobRemark", value = "本周工作备注", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobTitle", value = "周报主题", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userInfo", value = "接收人id拼接串，逗号隔开", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "weekenclosureInfo", value = "附件信息", dataType = "String", required = false, paramType = "query")})
	public String insertWeekJobDiary(@RequestHeader String userToken, String completedJob, String thisWorkSummary, String nextWorkPlan, String coordinaJob, String jobRemark, String jobTitle, String userInfo, String weekenclosureInfo) {
		return jobDiaryHasTokenService.insertWeekJobDiary(userToken, completedJob, thisWorkSummary, nextWorkPlan, coordinaJob, jobRemark, jobTitle, userInfo, weekenclosureInfo); 
	}
	
	/**
	 * 
	     * @Title: selectMysendWeekDetails
	     * @Description: 我发出的周报详情
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/selectMysendWeekDetails")
	@ApiOperation(value = "我发出的周报详情", notes = "获取我的周报详情", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "周报Id", dataType = "String", required = true, paramType = "query")})
	public String selectMysendWeekDetails(@RequestHeader String userToken, String rowId) {
		return jobDiaryHasTokenService.selectMysendWeekDetails(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: queryWeekJobDiaryDetails
	     * @Description: 阅读接收到的周报并改变阅读状态
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/queryWeekJobDiaryDetails")
	@ApiOperation(value = "阅读接收到的周报并改变阅读状态", notes = "阅读接收到的周报并把阅读状态改变成已读", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "周报关联表Id", dataType = "String", required = true, paramType = "query")})
	public String queryWeekJobDiaryDetails(@RequestHeader String userToken, String rowId) {
		return jobDiaryHasTokenService.queryWeekJobDiaryDetails(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: insertMonthJobDiary
	     * @Description: 发表月报
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/insertMonthJobDiary")
	@ApiOperation(value = "发表月报", notes = "发表月报", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "completedJob", value = "已完成工作", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "thisWorkSummary", value = "本月工作总结", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "nextWorkPlan", value = "下月工作计划", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "coordinaJob", value = "需协调工作", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobRemark", value = "本月工作备注", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobTitle", value = "月报主题", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userInfo", value = "接收人id拼接串，逗号隔开", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "monthenclosureInfo", value = "附件信息", dataType = "String", required = false, paramType = "query")})
	public String insertMonthJobDiary(@RequestHeader String userToken, String completedJob, String thisWorkSummary, String nextWorkPlan, String coordinaJob, String jobRemark, String jobTitle, String userInfo, String monthenclosureInfo) {
		return jobDiaryHasTokenService.insertMonthJobDiary(userToken, completedJob, thisWorkSummary, nextWorkPlan, coordinaJob, jobRemark, jobTitle, userInfo, monthenclosureInfo); 
	}
	
	/**
	 * 
	     * @Title: selectMysendMonthDetails
	     * @Description: 我发出的月报详情
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/selectMysendMonthDetails")
	@ApiOperation(value = "我发出的月报详情", notes = "获取我的月报详情", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "月报Id", dataType = "String", required = true, paramType = "query")})
	public String selectMysendMonthDetails(@RequestHeader String userToken, String rowId) {
		return jobDiaryHasTokenService.selectMysendMonthDetails(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: queryMonthJobDiaryDetails
	     * @Description: 阅读接收到的月报并改变阅读状态
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/queryMonthJobDiaryDetails")
	@ApiOperation(value = "阅读接收到的月报并改变阅读状态", notes = "阅读接收到的月报并把阅读状态改变成已读", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "月报关联表Id", dataType = "String", required = true, paramType = "query")})
	public String queryMonthJobDiaryDetails(@RequestHeader String userToken, String rowId) {
		return jobDiaryHasTokenService.queryMonthJobDiaryDetails(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: queryJobDiaryDayMysendToEdit
	     * @Description: 回显我撤回的日报
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/queryJobDiaryDayMysendToEdit")
	@ApiOperation(value = "回显我撤回的日报", notes = "回显我撤回的日报用于重新编辑发送", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "日报id", dataType = "String", required = true, paramType = "query")})
	public String queryJobDiaryDayMysendToEdit(@RequestHeader String userToken, String rowId) {
		return jobDiaryHasTokenService.queryJobDiaryDayMysendToEdit(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: queryWeekJobDiaryDayMysendToEdit
	     * @Description: 回显我撤回的周报
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/queryWeekJobDiaryDayMysendToEdit")
	@ApiOperation(value = "回显我撤回的周报", notes = "回显我撤回的周报用于重新编辑发送", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "周报Id", dataType = "String", required = true, paramType = "query")})
	public String queryWeekJobDiaryDayMysendToEdit(@RequestHeader String userToken, String rowId) {
		return jobDiaryHasTokenService.queryWeekJobDiaryDayMysendToEdit(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: queryMonthJobDiaryDayMysendToEdit
	     * @Description: 回显我撤回的月报
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/queryMonthJobDiaryDayMysendToEdit")
	@ApiOperation(value = "回显我撤回的月报", notes = "回显我撤回的月报用于重新编辑发送", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "月报Id", dataType = "String", required = true, paramType = "query")})
	public String queryMonthJobDiaryDayMysendToEdit(@RequestHeader String userToken, String rowId) {
		return jobDiaryHasTokenService.queryMonthJobDiaryDayMysendToEdit(userToken, rowId); 
	}
	
	/**
	 * 
	     * @Title: editWeekDayJobDiary
	     * @Description: 提交撤回的周报
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/editWeekDayJobDiary")
	@ApiOperation(value = "提交撤回的周报", notes = "提交撤回的周报", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "周报Id", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "completedJob", value = "已完成工作", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "thisWorkSummary", value = "本周工作总结", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "nextWorkPlan", value = "下周工作计划", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "coordinaJob", value = "需协调工作", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobRemark", value = "本周工作备注", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobTitle", value = "周报主题", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userInfo", value = "接收人id拼接串，逗号隔开", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "weekenclosureInfo", value = "附件信息", dataType = "String", required = false, paramType = "query")})
	public String editWeekDayJobDiary(@RequestHeader String userToken, String rowId, String completedJob, String thisWorkSummary, String nextWorkPlan, String coordinaJob, String jobRemark, String jobTitle, String userInfo, String weekenclosureInfo) {
		return jobDiaryHasTokenService.editWeekDayJobDiary(userToken, rowId, completedJob, thisWorkSummary, nextWorkPlan, coordinaJob, jobRemark, jobTitle, userInfo, weekenclosureInfo); 
	}
	
	/**
	 * 
	     * @Title: editMonthDayJobDiary
	     * @Description: 提交撤回的月报
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/editMonthDayJobDiary")
	@ApiOperation(value = "提交撤回的月报", notes = "提交撤回的月报", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "rowId", value = "月报id", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "completedJob", value = "已完成工作", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "thisWorkSummary", value = "本月工作总结", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "nextWorkPlan", value = "下月工作计划", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "coordinaJob", value = "需协调工作", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobRemark", value = "本月工作备注", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "jobTitle", value = "月报主题", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userInfo", value = "接收人id拼接串，逗号隔开", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "monthenclosureInfo", value = "附件信息", dataType = "String", required = false, paramType = "query")})
	public String editMonthDayJobDiary(@RequestHeader String userToken, String rowId, String completedJob, String thisWorkSummary, String nextWorkPlan, String coordinaJob, String jobRemark, String jobTitle, String userInfo, String monthenclosureInfo) {
		return jobDiaryHasTokenService.editMonthDayJobDiary(userToken, rowId, completedJob, thisWorkSummary, nextWorkPlan, coordinaJob, jobRemark, jobTitle, userInfo, monthenclosureInfo); 
	}
	
	/**
	 * 
	     * @Title: editReceivedJobDiaryToAlreadyRead
	     * @Description: 我收到的日志全部设置为已读
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/editReceivedJobDiaryToAlreadyRead")
	@ApiOperation(value = "我收到的日志全部设置为已读", notes = "我收到的日志全部设置为已读", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header")})
	public String editReceivedJobDiaryToAlreadyRead(@RequestHeader String userToken) {
		return jobDiaryHasTokenService.editReceivedJobDiaryToAlreadyRead(userToken); 
	}
	
	/**
	 * 
	     * @Title: queryJobDiaryDayNumber
	     * @Description: 查询日志类型各个类型的条数
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/queryJobDiaryDayNumber")
	@ApiOperation(value = "查询日志类型各个类型的条数", notes = "查询日志类型各个类型的条数", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "firstTime", value = "查询时间段的起始时间", dataType = "String", required = false, paramType = "query"),
		@ApiImplicitParam(name = "lastTime", value = "查询时间段的结束时间", dataType = "String", required = false, paramType = "query")})
	public String queryJobDiaryDayNumber(@RequestHeader String userToken, String firstTime, String lastTime) {
		return jobDiaryHasTokenService.queryJobDiaryDayNumber(userToken, firstTime, lastTime); 
	}
	
}