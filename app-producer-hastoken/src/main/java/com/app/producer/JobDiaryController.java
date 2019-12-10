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
