package com.app.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.hystrix.JobDiaryServiceHystrix;

/**
 * 添加fallback属性（在HelloRemote类添加指定fallback类，在服务熔断的时候返回fallback类中的内容）
 * @author 
 */
@FeignClient(name= "app-producer-hasauth", fallback = JobDiaryServiceHystrix.class)
public interface JobDiaryService {

    @RequestMapping(value = "/jobDiary", method = RequestMethod.GET)
    public String queryJobDiaryDayReceived(@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page, 
    		@RequestParam(value = "diaryType") int diaryType);
    
    @RequestMapping(value = "/jobDiary", method = RequestMethod.POST)
    public String insertDayJobDiary(@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "completedJob") String completedJob, 
    		@RequestParam(value = "incompleteJob") String incompleteJob, 
    		@RequestParam(value = "coordinaJob") String coordinaJob, 
    		@RequestParam(value = "jobRemark") String jobRemark,
    		@RequestParam(value = "jobTitle") String jobTitle, 
    		@RequestParam(value = "userInfo") String userIds,
    		@RequestParam(value = "enclosureInfo") String enclosureInfo);
    
    @RequestMapping(value = "/jobDiaryMySend", method = RequestMethod.GET)
	public String queryJobDiaryDayMysend(@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page, 
    		@RequestParam(value = "diaryType") int diaryType);

    @RequestMapping(value = "/jobDiary", method = RequestMethod.PUT)
	public String editJobDiaryDayMysend(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/jobDiaryEdit", method = RequestMethod.PUT)
	public String editMyReceivedJobDiary(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "ids") String[] ids);

    @RequestMapping(value = "/jobDiaryEditMySend", method = RequestMethod.PUT)
	public String editJobDiaryDayMysendToDelete(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "ids") String[] ids);

    @RequestMapping(value = "/editDayJobDiary", method = RequestMethod.PUT)
	public String editDayJobDiary(@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "rowId") String id, 
    		@RequestParam(value = "completedJob") String completedJob, 
    		@RequestParam(value = "incompleteJob") String incompleteJob, 
    		@RequestParam(value = "coordinaJob") String coordinaJob, 
    		@RequestParam(value = "jobRemark") String jobRemark,
    		@RequestParam(value = "jobTitle") String jobTitle, 
    		@RequestParam(value = "userInfo") String userIds,
    		@RequestParam(value = "enclosureInfo") String enclosureInfo);

    @RequestMapping(value = "/querySysEveUserStaff", method = RequestMethod.GET)
	public String querySysEveUserStaff(@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page, 
    		@RequestParam(value = "userName") String userName);

    @RequestMapping(value = "/queryJobDiaryDetails", method = RequestMethod.GET)
	public String queryJobDiaryDetails(@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/selectMysendDetails", method = RequestMethod.GET)
	public String selectMysendDetails(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/insertWeekJobDiary", method = RequestMethod.POST)
	public String insertWeekJobDiary(@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "completedJob") String completedJob,
			@RequestParam(value = "thisWorkSummary") String thisWorkSummary, 
			@RequestParam(value = "nextWorkPlan") String nextWorkPlan, 
			@RequestParam(value = "coordinaJob") String coordinaJob,
			@RequestParam(value = "jobRemark") String jobRemark, 
			@RequestParam(value = "jobTitle") String jobTitle, 
			@RequestParam(value = "userInfo") String userInfo,
			@RequestParam(value = "weekenclosureInfo") String weekenclosureInfo);

    @RequestMapping(value = "/selectMysendWeekDetails", method = RequestMethod.GET)
	public String selectMysendWeekDetails(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/queryWeekJobDiaryDetails", method = RequestMethod.GET)
	public String queryWeekJobDiaryDetails(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/insertMonthJobDiary", method = RequestMethod.POST)
	public String insertMonthJobDiary(@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "completedJob") String completedJob,
			@RequestParam(value = "thisWorkSummary") String thisWorkSummary, 
			@RequestParam(value = "nextWorkPlan") String nextWorkPlan, 
			@RequestParam(value = "coordinaJob") String coordinaJob,
			@RequestParam(value = "jobRemark") String jobRemark, 
			@RequestParam(value = "jobTitle") String jobTitle, 
			@RequestParam(value = "userInfo") String userInfo,
			@RequestParam(value = "monthenclosureInfo") String monthenclosureInfo);

    @RequestMapping(value = "/selectMysendMonthDetails", method = RequestMethod.GET)
	public String selectMysendMonthDetails(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/queryMonthJobDiaryDetails", method = RequestMethod.GET)
	public String queryMonthJobDiaryDetails(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/queryJobDiaryDayMysendToEdit", method = RequestMethod.GET)
	public String queryJobDiaryDayMysendToEdit(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/queryWeekJobDiaryDayMysendToEdit", method = RequestMethod.GET)
	public String queryWeekJobDiaryDayMysendToEdit(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/queryMonthJobDiaryDayMysendToEdit", method = RequestMethod.GET)
	public String queryMonthJobDiaryDayMysendToEdit(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/editWeekDayJobDiary", method = RequestMethod.PUT)
	public String editWeekDayJobDiary(@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "rowId") String rowId,
			@RequestParam(value = "completedJob") String completedJob, 
			@RequestParam(value = "thisWorkSummary") String thisWorkSummary, 
			@RequestParam(value = "nextWorkPlan") String nextWorkPlan,
			@RequestParam(value = "coordinaJob") String coordinaJob, 
			@RequestParam(value = "jobRemark") String jobRemark, 
			@RequestParam(value = "jobTitle") String jobTitle,
			@RequestParam(value = "userInfo") String userInfo, 
			@RequestParam(value = "weekenclosureInfo") String enclosureInfo);

    @RequestMapping(value = "/editMonthDayJobDiary", method = RequestMethod.PUT)
	public String editMonthDayJobDiary(@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "rowId") String rowId,
			@RequestParam(value = "completedJob") String completedJob, 
			@RequestParam(value = "thisWorkSummary") String thisWorkSummary, 
			@RequestParam(value = "nextWorkPlan") String nextWorkPlan,
			@RequestParam(value = "coordinaJob") String coordinaJob, 
			@RequestParam(value = "jobRemark") String jobRemark, 
			@RequestParam(value = "jobTitle") String jobTitle,
			@RequestParam(value = "userInfo") String userInfo, 
			@RequestParam(value = "monthenclosureInfo") String enclosureInfo);

    @RequestMapping(value = "/editReceivedJobDiaryToAlreadyRead", method = RequestMethod.PUT)
	public String editReceivedJobDiaryToAlreadyRead(@RequestHeader(value = "userToken") String userToken);

    @RequestMapping(value = "/queryJobDiaryDayNumber", method = RequestMethod.GET)
	public String queryJobDiaryDayNumber(@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "firstTime") String firstTime,
			@RequestParam(value = "lastTime") String lastTime);
}
