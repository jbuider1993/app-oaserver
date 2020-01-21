package com.app.service.hystrix;

import org.springframework.stereotype.Component;

import com.app.service.JobDiaryService;

@Component
public class JobDiaryServiceHystrix implements JobDiaryService{
	
	@Override
	public String queryJobDiaryDayReceived(String userToken, int limit, int page, int diaryType) {
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
	public String editMyReceivedJobDiary(String userToken, String[] ids) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String editJobDiaryDayMysendToDelete(String userToken, String[] ids) {
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

	@Override
	public String queryJobDiaryDetails(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String selectMysendDetails(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String insertWeekJobDiary(String userToken, String completedJob, String thisWorkSummary, String nextWorkPlan, String coordinaJob,
			String jobRemark, String jobTitle, String userInfo, String weekenclosureInfo) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String selectMysendWeekDetails(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryWeekJobDiaryDetails(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String insertMonthJobDiary(String userToken, String completedJob, String thisWorkSummary, String nextWorkPlan, String coordinaJob,
			String jobRemark, String jobTitle, String userInfo, String monthenclosureInfo) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String selectMysendMonthDetails(String userToken, String rowId) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryMonthJobDiaryDetails(String userToken, String rowId) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryJobDiaryDayMysendToEdit(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryWeekJobDiaryDayMysendToEdit(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryMonthJobDiaryDayMysendToEdit(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String editWeekDayJobDiary(String userToken, String rowId, String completedJob, String thisWorkSummary, String nextWorkPlan,
			String coordinaJob, String jobRemark, String jobTitle, String userInfo, String enclosureInfo) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String editMonthDayJobDiary(String userToken, String rowId, String completedJob, String thisWorkSummary, String nextWorkPlan,
			String coordinaJob, String jobRemark, String jobTitle, String userInfo, String enclosureInfo) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String editReceivedJobDiaryToAlreadyRead(String userToken) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryJobDiaryDayNumber(String userToken, String firstTime, String lastTime) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

}
