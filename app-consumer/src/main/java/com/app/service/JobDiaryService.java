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

    @RequestMapping(value = "/JobDiary", method = RequestMethod.GET)
    public String queryJobDiaryDayReceived(@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page, 
    		@RequestParam(value = "diaryType") int diaryType);
    
    @RequestMapping(value = "/JobDiary", method = RequestMethod.POST)
    public String insertDayJobDiary(@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "completedJob") String completedJob, 
    		@RequestParam(value = "incompleteJob") String incompleteJob, 
    		@RequestParam(value = "coordinaJob") String coordinaJob, 
    		@RequestParam(value = "jobRemark") String jobRemark,
    		@RequestParam(value = "jobTitle") String jobTitle, 
    		@RequestParam(value = "userInfo") String userIds,
    		@RequestParam(value = "enclosureInfo") String enclosureInfo);
    
    @RequestMapping(value = "/JobDiaryMySend", method = RequestMethod.GET)
	public String queryJobDiaryDayMysend(@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page, 
    		@RequestParam(value = "diaryType") int diaryType);

    @RequestMapping(value = "/JobDiary", method = RequestMethod.PUT)
	public String editJobDiaryDayMysend(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/JobDiaryEdit", method = RequestMethod.PUT)
	public String editMyReceivedJobDiary(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/JobDiaryEditMySend", method = RequestMethod.PUT)
	public String editJobDiaryDayMysendToDelete(@RequestHeader(value = "userToken") String userToken, @RequestParam(value = "rowId") String id);

    @RequestMapping(value = "/EditDayJobDiary", method = RequestMethod.PUT)
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

 
}
