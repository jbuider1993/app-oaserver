package com.app.producer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.util.ToolUtil;
import com.app.dao.SealSeServiceDao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * @author 卫志强
 *
 */
@RestController
public class SealSeServiceController {
	
	@Autowired
	private SealSeServiceDao sealSeServiceDao;
	
	/**
	 * 
	     * @Title: querySealSeServiceList
	     * @Description: 获取全部工单列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceOrder")
	public void querySealSeServiceList(HttpServletResponse response, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = sealSeServiceDao.querySealSeServiceList(new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: querySealServiceWaitToWorkOrder
	     * @Description: 获取全部待派工的工单列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceWaitToWorkOrder")
	public void querySealServiceWaitToWorkOrder(HttpServletResponse response, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = sealSeServiceDao.querySealServiceWaitToWorkOrder(new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceWaitToReceiveList
	     * @Description: 获取当前登录人待接单的列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceWaitToReceiveOrder")
	public void querySealSeServiceWaitToReceiveList(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = sealSeServiceDao.querySealSeServiceWaitToReceiveList(userToken, new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceWaitToSignonList
	     * @Description: 获取当前登录人待签到的列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceWaitToSignonOrder")
	public void querySealSeServiceWaitToSignonList(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = sealSeServiceDao.querySealSeServiceWaitToSignonList(userToken, new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceWaitToFinishList
	     * @Description: 获取当前登录人待完工的列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceWaitToFinishOrder")
	public void querySealSeServiceWaitToFinishList(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = sealSeServiceDao.querySealSeServiceWaitToFinishList(userToken, new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceWaitToFinishList
	     * @Description: 获取当前登录人待评价的列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceWaitToAssessmentOrder")
	public void querySealSeServiceWaitToAssessmentList(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = sealSeServiceDao.querySealSeServiceWaitToAssessmentList(userToken, new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceWaitToFinishList
	     * @Description: 获取当前登录人待评价的列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceAllWaitToAssessmentOrder")
	public void queryAllSealSeServiceWaitToAssessmentList(HttpServletResponse response, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = sealSeServiceDao.queryAllSealSeServiceWaitToAssessmentList(new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
}