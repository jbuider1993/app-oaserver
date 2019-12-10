package com.app.service.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.JobDiaryService;

@Component
public class JobDiaryServiceHystrix implements JobDiaryService{
	
	@Override
	public String queryJobDiaryDayReceived(
			@RequestParam(value = "userToken") String userToken, 
			@RequestParam(value = "limit") int limit, 
			@RequestParam(value = "page") int page, 
			@RequestParam(value = "diaryType") int diaryType) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String insertDayJobDiary(String userToken, String completedJob, String incompleteJob, String coordinaJob, String jobRemark, String jobTitle, String userIds, String enclosureInfo) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryJobDiaryDayMysend(String userToken, int limit, int page, int diaryType) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String editJobDiaryDayMysend(String userToken,  String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String editMyReceivedJobDiary(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String editJobDiaryDayMysendToDelete(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String editDayJobDiary(String userToken, String id,
			String completedJob, String incompleteJob, String coordinaJob,
			String jobRemark, String jobTitle, String userIds,
			String enclosureInfo) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String querySysEveUserStaff(String userToken, int limit, int page, String userName) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

}
