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
	@GetMapping("/JobDiary")
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
	@PostMapping("/JobDiary")
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
	@GetMapping("/JobDiaryMySend")
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
	@PutMapping("/JobDiary")
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
	@PutMapping("/JobDiaryEdit")
	public void editMyReceivedJobDiary(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object> diaryType = jobDiaryDao.queryDiaryType(id);
		if("1".equals(diaryType.get("diaryType").toString())){
			jobDiaryDao.editMyReceivedJobDiary(id);
		}else if("2".equals(diaryType.get("diaryType").toString())){
			jobDiaryDao.editMyReceivedWeekJobDiary(id);
		}else if("3".equals(diaryType.get("diaryType").toString())){
			jobDiaryDao.editMyReceivedMonthJobDiary(id);
		}
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
	@PutMapping("/JobDiaryEditMySend")
	public void editJobDiaryDayMysendToDelete(HttpServletResponse response, @RequestParam(value = "rowId") String id) {
		Map<String, Object> createtime = jobDiaryDao.queryJobDiaryType(id);
		if("1".equals(createtime.get("diaryType").toString())){
			jobDiaryDao.editJobDiaryDayMysendDelete(id);  //修改日报表状态变删除
		}else if("2".equals(createtime.get("diaryType").toString())){
			jobDiaryDao.editJobDiaryWeekMysendDelete(id);  //修改周报表状态变删除
		}else if("3".equals(createtime.get("diaryType").toString())){
			jobDiaryDao.editJobDiaryMonthMysendDelete(id);  //修改月报表状态变删除
		}
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: editDayJobDiary
	     * @Description: 提交撤回的日志
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/EditDayJobDiary")
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

}
