package com.app.producer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.util.ToolUtil;
import com.app.dao.JobDiaryDao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@RestController
public class JobDiaryController {
	
	@Autowired
	private JobDiaryDao jobDiaryDao;

	/**
	 * 
	     * @Title: queryJobDiaryDayReceived
	     * @Description: 遍历我收到的日志
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/jobDiary")
	public void queryJobDiaryDayReceived(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam int limit, 
			@RequestParam int page,
			@RequestParam int diaryType) {
		List<Map<String, Object>> beans = jobDiaryDao.queryJobDiaryDayReceived(userToken, diaryType, new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: insertDayJobDiary
	     * @Description: 发表日报
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/jobDiary")
	public void insertDayJobDiary(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam String completedJob, 
			@RequestParam String incompleteJob, 
			@RequestParam String coordinaJob, 
			@RequestParam String jobRemark,
			@RequestParam String jobTitle, 
			@RequestParam(value = "userInfo") String userIds,
			@RequestParam String enclosureInfo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", ToolUtil.getSurFaceId());
		map.put("completedJob", completedJob);
		map.put("incompleteJob", incompleteJob);
		map.put("coordinaJob", coordinaJob);
		map.put("jobRemark", jobRemark);
		map.put("jobTitle", jobTitle);
		map.put("createId", userToken);
		map.put("enclosureInfo", enclosureInfo);
		map.put("createTime", ToolUtil.getTimeAndToString());
		map.put("state", "1");
		jobDiaryDao.insertDayJobDiary(map);
		List<Map<String,Object>> beans = new ArrayList<>();
		String[] userId = userIds.split(",");//把字符串以","分截成字符数组
		if(userId.length > 0){  //如果数组长度大于0
			Map<String, Object> item;
			for(String str : userId){  //遍历数组
				item = new HashMap<>();
				item.put("id", ToolUtil.getSurFaceId());
				item.put("diaryDayId", map.get("id"));
				if(!ToolUtil.isBlank(str)){
					item.put("receivedId", str);
				}
				item.put("state", "1");
				beans.add(item); //把一个个item对象放入集合beans
			}
		}
		jobDiaryDao.insertDayJobDiaryReceived(beans);  //在数据库中插入集合beans
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * @throws Exception 
	 * 
	     * @Title: queryJobDiaryDayMysend
	     * @Description: 查看所有发出的日报
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/jobDiaryMySend")
	public void queryJobDiaryDayMysend(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam int limit, 
			@RequestParam int page,
			@RequestParam int diaryType) throws Exception {
		List<Map<String, Object>> beans = jobDiaryDao.queryJobDiaryDayMysend(userToken, diaryType, new PageBounds(page, limit));
		for(Map<String, Object> str : beans){  //遍历数组
			long twoHour = ToolUtil.getDistanceDays(ToolUtil.getTimeAndToString(), str.get("realCreateTime").toString());//计算当前时间和创建时间的时间差，返回分钟
			if(twoHour < 120){  //两个小时之内可以撤销
				str.put("isrepeal", "1");
			}else{
				str.put("isrepeal", "2");
			}
		}
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * @throws Exception 
	 * 
	     * @Title: queryJobDiaryDayReceived
	     * @Description: 撤销我发出的日报
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PutMapping("/jobDiary")
	public void editJobDiaryDayMysend(HttpServletResponse response, @RequestParam(value = "rowId") String id) throws Exception {
		Map<String, Object> createtime = jobDiaryDao.queryCreateTime(id);
		long twoHour = ToolUtil.getDistanceDays(ToolUtil.getTimeAndToString(), createtime.get("realCreateTime").toString());//计算当前时间和创建时间的时间差，返回分钟
		if(twoHour < 120){  //两个小时之内可以撤销
			if("1".equals(createtime.get("diaryType").toString())){
				jobDiaryDao.editJobDiaryDayMysendState(id);  //修改日报表状态
			}else if("2".equals(createtime.get("diaryType").toString())){
				jobDiaryDao.editJobDiaryWeekMysendState(id);  //修改周报表状态
			}else if("3".equals(createtime.get("diaryType").toString())){
				jobDiaryDao.editJobDiaryMonthMysendState(id);  //修改月报表状态
			}
		}
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: editMyReceivedJobDiary
	     * @Description: 删除我收到的日志
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PutMapping("/jobDiaryEdit")
	public void editMyReceivedJobDiary(HttpServletResponse response, @RequestParam String[] ids) {
		jobDiaryDao.editMyReceivedJobDiary(ids);
		jobDiaryDao.editMyReceivedWeekJobDiary(ids);
		jobDiaryDao.editMyReceivedMonthJobDiary(ids);
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: editMyReceivedJobDiary
	     * @Description: 删除我发出的日志
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PutMapping("/jobDiaryEditMySend")
	public void editJobDiaryDayMysendToDelete(HttpServletResponse response, @RequestParam String[] ids) {
		jobDiaryDao.editJobDiaryDayMysendDelete(ids);  //修改日报表状态变删除
		jobDiaryDao.editJobDiaryWeekMysendDelete(ids);  //修改周报表状态变删除
		jobDiaryDao.editJobDiaryMonthMysendDelete(ids);  //修改月报表状态变删除
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: editDayJobDiary
	     * @Description: 提交撤回的日志
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/editDayJobDiary")
	public void editDayJobDiary(HttpServletResponse response, 
			@RequestParam(value = "rowId") String id, 
			@RequestParam String completedJob, 
			@RequestParam String incompleteJob, 
			@RequestParam String coordinaJob, 
			@RequestParam String jobRemark,
			@RequestParam String jobTitle, 
			@RequestParam String userInfo,
			@RequestParam String enclosureInfo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("completedJob", completedJob);
		map.put("incompleteJob", incompleteJob);
		map.put("coordinaJob", coordinaJob);
		map.put("jobRemark", jobRemark);
		map.put("jobTitle", jobTitle);
		map.put("state", "1");
		map.put("enclosureInfo", enclosureInfo);
		jobDiaryDao.editDayJobDiary(map);
		jobDiaryDao.deleteJobDiaryReceived(id);
		List<Map<String,Object>> beans = new ArrayList<>();
		String[] userId = userInfo.split(",");//把字符串以","分截成字符数组
		if(userId.length > 0){  //如果数组长度大于0
			Map<String, Object> item;
			for(String str : userId){  //遍历数组
				item = new HashMap<>();
				item.put("id", ToolUtil.getSurFaceId());
				item.put("diaryDayId", map.get("id"));
				if(!ToolUtil.isBlank(str)){
					item.put("receivedId", str);
				}
				item.put("state", "1");
				beans.add(item); //把一个个item对象放入集合beans
			}
		}
		jobDiaryDao.insertDayJobDiaryReceived(beans);  //在数据库中插入集合beans
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: querySysEveUserStaff
	     * @Description: 查看所有有账户的员工列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/querySysEveUserStaff")
	public void querySysEveUserStaff(HttpServletResponse response, 
			@RequestHeader(value = "userToken") String userId, 
			@RequestParam int limit, 
			@RequestParam int page,
			@RequestParam String userName) {
		List<Map<String, Object>> beans = jobDiaryDao.querySysEveUserStaff(userId, userName, new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: queryJobDiaryDetails
	     * @Description: 阅读接收到的日报并改变阅读状态
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/queryJobDiaryDetails")
	public void queryJobDiaryDetails(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object> state = jobDiaryDao.queryJobDiaryState(id);
		if("1".equals(state.get("state").toString())){
			String readTime = ToolUtil.getTimeAndToString();
			jobDiaryDao.editJobDiaryState(readTime, id);
		}
		Map<String, Object>	bean = jobDiaryDao.queryJobDiaryDetails(id);
		if(!ToolUtil.isBlank(bean.get("enclosureInfo").toString())){
            List<Map<String,Object>> beans = jobDiaryDao.queryJobDiaryDayEnclosureInfo(bean.get("enclosureInfo").toString());
            bean.put("enclosureInfo", beans);
        }
		ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: selectMysendDetails
	     * @Description: 我发出的日报详情
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/selectMysendDetails")
	public void selectMysendDetails(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object>	bean = jobDiaryDao.selectMysendDetails(id);
		if(!ToolUtil.isBlank(bean.get("enclosureInfo").toString())){
            List<Map<String,Object>> beans = jobDiaryDao.queryJobDiaryDayEnclosureInfo(bean.get("enclosureInfo").toString());
            bean.put("enclosureInfo", beans);
        }
		ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: insertWeekJobDiary
	     * @Description: 发表周报
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/insertWeekJobDiary")
	public void insertWeekJobDiary(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam String completedJob, 
			@RequestParam String thisWorkSummary, 
			@RequestParam String nextWorkPlan, 
			@RequestParam String coordinaJob, 
			@RequestParam String jobRemark,
			@RequestParam String jobTitle, 
			@RequestParam String userInfo,
			@RequestParam(value = "weekenclosureInfo")  String enclosureInfo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", ToolUtil.getSurFaceId());
		map.put("completedJob", completedJob);
		map.put("thisWorkSummary", thisWorkSummary);
		map.put("nextWorkPlan", nextWorkPlan);
		map.put("coordinaJob", coordinaJob);
		map.put("jobRemark", jobRemark);
		map.put("jobTitle", jobTitle);
		map.put("createId", userToken);
		map.put("enclosureInfo", enclosureInfo);
		map.put("createTime", ToolUtil.getTimeAndToString());
		map.put("state", "1");
		jobDiaryDao.insertWeekJobDiary(map);
		List<Map<String,Object>> beans = new ArrayList<>();
		String[] userId = userInfo.split(",");//把字符串以","分截成字符数组
		if(userId.length > 0){  //如果数组长度大于0
			Map<String, Object> item;
			for(String str : userId){  //遍历数组
				item = new HashMap<>();
				item.put("id", ToolUtil.getSurFaceId());
				item.put("diaryWeekId", map.get("id"));
				if(!ToolUtil.isBlank(str)){
					item.put("receivedId", str);
				}
				item.put("state", "1");
				beans.add(item); //把一个个item对象放入集合beans
			}
		}
		jobDiaryDao.insertWeekJobDiaryReceived(beans);  //在数据库中插入集合beans
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: selectMysendDetails
	     * @Description: 我发出的周报详情
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/selectMysendWeekDetails")
	public void selectMysendWeekDetails(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object>	bean = jobDiaryDao.selectMysendWeekDetails(id);
		if(!ToolUtil.isBlank(bean.get("enclosureInfo").toString())){
            List<Map<String,Object>> beans = jobDiaryDao.queryJobDiaryDayEnclosureInfo(bean.get("enclosureInfo").toString());
            bean.put("enclosureInfo", beans);
        }
		ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: queryWeekJobDiaryDetails
	     * @Description: 阅读接收到的周报并改变阅读状态
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/queryWeekJobDiaryDetails")
	public void queryWeekJobDiaryDetails(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object> state = jobDiaryDao.queryWeekJobDiaryState(id);
		if("1".equals(state.get("state").toString())){
			String readTime = ToolUtil.getTimeAndToString();
			jobDiaryDao.editWeekJobDiaryState(readTime, id);
		}
		Map<String, Object>	bean = jobDiaryDao.queryWeekJobDiaryDetails(id);
		if(!ToolUtil.isBlank(bean.get("enclosureInfo").toString())){
            List<Map<String,Object>> beans = jobDiaryDao.queryJobDiaryDayEnclosureInfo(bean.get("enclosureInfo").toString());
            bean.put("enclosureInfo", beans);
        }
		ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: insertWeekJobDiary
	     * @Description: 发表月报
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/insertMonthJobDiary")
	public void insertMonthJobDiary(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam String completedJob, 
			@RequestParam String thisWorkSummary, 
			@RequestParam String nextWorkPlan, 
			@RequestParam String coordinaJob, 
			@RequestParam String jobRemark,
			@RequestParam String jobTitle, 
			@RequestParam String userInfo,
			@RequestParam(value = "monthenclosureInfo")  String enclosureInfo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", ToolUtil.getSurFaceId());
		map.put("completedJob", completedJob);
		map.put("thisWorkSummary", thisWorkSummary);
		map.put("nextWorkPlan", nextWorkPlan);
		map.put("coordinaJob", coordinaJob);
		map.put("jobRemark", jobRemark);
		map.put("jobTitle", jobTitle);
		map.put("createId", userToken);
		map.put("enclosureInfo", enclosureInfo);
		map.put("createTime", ToolUtil.getTimeAndToString());
		map.put("state", "1");
		jobDiaryDao.insertMonthJobDiary(map);
		List<Map<String,Object>> beans = new ArrayList<>();
		String[] userId = userInfo.split(",");//把字符串以","分截成字符数组
		if(userId.length > 0){  //如果数组长度大于0
			Map<String, Object> item;
			for(String str : userId){  //遍历数组
				item = new HashMap<>();
				item.put("id", ToolUtil.getSurFaceId());
				item.put("diaryMonthId", map.get("id"));
				if(!ToolUtil.isBlank(str)){
					item.put("receivedId", str);
				}
				item.put("state", "1");
				beans.add(item); //把一个个item对象放入集合beans
			}
		}
		jobDiaryDao.insertMonthJobDiaryReceived(beans);  //在数据库中插入集合beans
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: selectMysendMonthDetails
	     * @Description: 我发出的月报详情
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/selectMysendMonthDetails")
	public void selectMysendMonthDetails(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object>	bean = jobDiaryDao.selectMysendMonthDetails(id);
		if(!ToolUtil.isBlank(bean.get("enclosureInfo").toString())){
            List<Map<String,Object>> beans = jobDiaryDao.queryJobDiaryDayEnclosureInfo(bean.get("enclosureInfo").toString());
            bean.put("enclosureInfo", beans);
        }
		ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: queryMonthJobDiaryDetails
	     * @Description: 阅读接收到的月报并改变阅读状态
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/queryMonthJobDiaryDetails")
	public void queryMonthJobDiaryDetails(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object> state = jobDiaryDao.queryMonthJobDiaryState(id);
		if("1".equals(state.get("state").toString())){
			String readTime = ToolUtil.getTimeAndToString();
			jobDiaryDao.editMonthJobDiaryState(readTime, id);
		}
		Map<String, Object>	bean = jobDiaryDao.queryMonthJobDiaryDetails(id);
		if(!ToolUtil.isBlank(bean.get("enclosureInfo").toString())){
            List<Map<String,Object>> beans = jobDiaryDao.queryJobDiaryDayEnclosureInfo(bean.get("enclosureInfo").toString());
            bean.put("enclosureInfo", beans);
        }
		ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: queryJobDiaryDayMysendToEdit
	     * @Description: 回显我撤回的日报
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/queryJobDiaryDayMysendToEdit")
	public void queryJobDiaryDayMysendToEdit(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object>	bean = jobDiaryDao.queryJobDiaryDayMysendToEdit(id);
		List<Map<String,Object>> beans = new ArrayList<>();
		if(!ToolUtil.isBlank(bean.get("enclosureInfo").toString())){
			beans = jobDiaryDao.queryJobDiaryDayEnclosureInfo(bean.get("enclosureInfo").toString());
            bean.put("enclosureInfo", beans);
        }
		beans = jobDiaryDao.queryJobDiaryDayReceivedUserInfoById(id);
        bean.put("userInfo", beans);
		ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: queryWeekJobDiaryDayMysendToEdit
	     * @Description: 回显我撤回的周报
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/queryWeekJobDiaryDayMysendToEdit")
	public void queryWeekJobDiaryDayMysendToEdit(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object>	bean = jobDiaryDao.queryWeekJobDiaryDayMysendToEdit(id);
		List<Map<String,Object>> beans = new ArrayList<>();
		if(!ToolUtil.isBlank(bean.get("enclosureInfo").toString())){
			beans = jobDiaryDao.queryJobDiaryDayEnclosureInfo(bean.get("enclosureInfo").toString());
            bean.put("enclosureInfo", beans);
        }
		beans = jobDiaryDao.queryWeekJobDiaryDayReceivedUserInfoById(id);
        bean.put("userInfo", beans);
		ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: queryMonthJobDiaryDayMysendToEdit
	     * @Description: 回显我撤回的月报
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/queryMonthJobDiaryDayMysendToEdit")
	public void queryMonthJobDiaryDayMysendToEdit(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object>	bean = jobDiaryDao.queryMonthJobDiaryDayMysendToEdit(id);
		List<Map<String,Object>> beans = new ArrayList<>();
		if(!ToolUtil.isBlank(bean.get("enclosureInfo").toString())){
			beans = jobDiaryDao.queryJobDiaryDayEnclosureInfo(bean.get("enclosureInfo").toString());
            bean.put("enclosureInfo", beans);
        }
		beans = jobDiaryDao.queryMonthJobDiaryDayReceivedUserInfoById(id);
        bean.put("userInfo", beans);
		ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: editWeekDayJobDiary
	     * @Description: 提交撤回的周报
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/editWeekDayJobDiary")
	public void editWeekDayJobDiary(HttpServletResponse response, 
			@RequestParam(value = "rowId") String id, 
			@RequestParam String completedJob, 
			@RequestParam String thisWorkSummary, 
			@RequestParam String nextWorkPlan, 
			@RequestParam String coordinaJob, 
			@RequestParam String jobRemark,
			@RequestParam String jobTitle, 
			@RequestParam String userInfo,
			@RequestParam(value = "weekenclosureInfo") String enclosureInfo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("completedJob", completedJob);
		map.put("thisWorkSummary", thisWorkSummary);
		map.put("nextWorkPlan", nextWorkPlan);
		map.put("coordinaJob", coordinaJob);
		map.put("jobRemark", jobRemark);
		map.put("jobTitle", jobTitle);
		map.put("state", "1");
		map.put("enclosureInfo", enclosureInfo);
		jobDiaryDao.editWeekDayJobDiary(map);
		jobDiaryDao.deleteWeekJobDiaryReceived(map);
		List<Map<String,Object>> beans = new ArrayList<>();
		String[] userId = userInfo.split(",");//把字符串以","分截成字符数组
		if(userId.length > 0){  //如果数组长度大于0
			Map<String, Object> item;
			for(String str : userId){  //遍历数组
				item = new HashMap<>();
				item.put("id", ToolUtil.getSurFaceId());
				item.put("diaryWeekId", map.get("id"));
				if(!ToolUtil.isBlank(str)){
					item.put("receivedId", str);
				}
				item.put("state", "1");
				beans.add(item); //把一个个item对象放入集合beans
			}
		}
		jobDiaryDao.insertWeekJobDiaryReceived(beans);  //在数据库中插入集合beans
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: editMonthDayJobDiary
	     * @Description: 提交撤回的月报
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/editMonthDayJobDiary")
	public void editMonthDayJobDiary(HttpServletResponse response, 
			@RequestParam(value = "rowId") String id, 
			@RequestParam String completedJob, 
			@RequestParam String thisWorkSummary, 
			@RequestParam String nextWorkPlan,
			@RequestParam String coordinaJob, 
			@RequestParam String jobRemark,
			@RequestParam String jobTitle, 
			@RequestParam String userInfo,
			@RequestParam(value = "monthenclosureInfo") String enclosureInfo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("completedJob", completedJob);
		map.put("thisWorkSummary", thisWorkSummary);
		map.put("nextWorkPlan", nextWorkPlan);
		map.put("coordinaJob", coordinaJob);
		map.put("jobRemark", jobRemark);
		map.put("jobTitle", jobTitle);
		map.put("state", "1");
		map.put("enclosureInfo", enclosureInfo);
		jobDiaryDao.editMonthDayJobDiary(map);
		jobDiaryDao.deleteMonthJobDiaryReceived(map);
		List<Map<String,Object>> beans = new ArrayList<>();
		String[] userId = userInfo.split(",");//把字符串以","分截成字符数组
		if(userId.length > 0){  //如果数组长度大于0
			Map<String, Object> item;
			for(String str : userId){  //遍历数组
				item = new HashMap<>();
				item.put("id", ToolUtil.getSurFaceId());
				item.put("diaryMonthId", map.get("id"));
				if(!ToolUtil.isBlank(str)){
					item.put("receivedId", str);
				}
				item.put("state", "1");
				beans.add(item); //把一个个item对象放入集合beans
			}
		}
		jobDiaryDao.insertMonthJobDiaryReceived(beans);  //在数据库中插入集合beans
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: editReceivedJobDiaryToAlreadyRead
	     * @Description: 我收到的日志全部设置为已读
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PutMapping("/editReceivedJobDiaryToAlreadyRead")
	public void editReceivedJobDiaryToAlreadyRead(HttpServletResponse response, @RequestHeader(value = "userToken") String userId) {
		String readTime = ToolUtil.getTimeAndToString();
        jobDiaryDao.editReceivedJobDiaryToAlreadyRead(readTime, userId);
        jobDiaryDao.editReceivedWeekJobDiaryToAlreadyRead(readTime, userId);
        jobDiaryDao.editReceivedMonthJobDiaryToAlreadyRead(readTime, userId);
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: queryJobDiaryDayNumber
	     * @Description: 查询日志类型各个类型的条数
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/queryJobDiaryDayNumber")
	public void queryJobDiaryDayNumber(HttpServletResponse response, @RequestHeader(value = "userToken") String userId, @RequestParam(value = "firstTime") String firstTime, @RequestParam(value = "lastTime") String lastTime) {
		List<Map<String, Object>> beans = jobDiaryDao.queryJobDiaryDayNumber(userId, firstTime, lastTime);
		ToolUtil.sendMessageToPageComJson(response, beans);
	}

}
