package com.app.producer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.util.ToolUtil;
import com.app.dao.SealSeServiceDao;
import com.app.entity.SealServiceOrderEntity;
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
	     * @Title: querySealSeServiceWaitToAssessmentList
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
	     * @Title: queryAllSealSeServiceWaitToAssessmentList
	     * @Description: 获取所有待评价的列表
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
	
	/**
	 * 
	     * @Title: queryAllSealSeServiceWaitToCheckList
	     * @Description: 获取所有待审核的列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceAllWaitToCheckOrder")
	public void queryAllSealSeServiceWaitToCheckList(HttpServletResponse response, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = sealSeServiceDao.queryAllSealSeServiceWaitToCheckList(new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: queryAllSealSeServiceFinishedList
	     * @Description: 获取所有已完成的列表
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceAllFinishedOrder")
	public void queryAllSealSeServiceFinishedList(HttpServletResponse response, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = sealSeServiceDao.queryAllSealSeServiceFinishedList(new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceDetail
	     * @Description: 获取工单详情
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceOrderDetail")
	public void querySealSeServiceDetail(HttpServletResponse response, 
			@RequestParam String id) {
		Map<String, Object> bean = sealSeServiceDao.querySealSeServiceToDetails(id);
		//集合中放入附件信息
		bean.put("enclosureInfo", sealSeServiceDao.queryEnclosureInfo(bean));
		//集合中放入完工附件信息
		bean.put("comEnclosureInfo", sealSeServiceDao.queryComEnclosureInfo(bean));
        //集合中放入工单协助人信息
        bean.put("cooperationUserId", sealSeServiceDao.queryCooperationUserNameById(id));
        //集合中放入配件使用信息
        bean.put("materialMation", sealSeServiceDao.queryMaterialMationById(id));
        //集合中放入反馈信息
        bean.put("feedbackMation", sealSeServiceDao.queryFeedbackMationById(id));
        ToolUtil.sendMessageToPageComJson(response, bean);
	}
	
	/**
	 * 
	     * @Title: insertSealSeServiceMation
	     * @Description: 新增工单
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PostMapping("/sealServiceOrder")
	public void insertSealSeServiceMation(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestBody SealServiceOrderEntity sealServiceOrderEntity) {
		Map<String, Object> map = ToolUtil.javaBean2Map(sealServiceOrderEntity);
		if(ToolUtil.isBlank(map.get("productWarranty").toString())){
			map.put("productWarranty", null);
		}
		map.put("id", ToolUtil.getSurFaceId());
		map.put("orderNum", "SHFW" + ToolUtil.getUniqueKey());
		map.put("createTime", ToolUtil.getTimeAndToString());
		map.put("state", "1");
		if(!ToolUtil.isBlank(map.get("serviceUserId").toString())){//接收人不为空，则进入待接单状态
			map.put("state", '2');
			map.put("serviceTime", ToolUtil.getTimeAndToString());
		}
		map.put("type", "2");
		map.put("parentId", "0");
		map.put("createId", userToken);
		map.put("declarationId", userToken);
		System.out.println(map);
		int size = sealSeServiceDao.insertSealSeServiceMation(map);
		if(size > 0){
			ToolUtil.sendMessageToPageComJson(response);
		}else{
			ToolUtil.sendMessageToPageComJson(response, "新增工单失败。", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceMationToEdit
	     * @Description: 根据id获取售后服务信息用于编辑回显
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealServiceOrderEdit")
//	@AuthAnnotation
	public void querySealSeServiceMationToEdit(HttpServletResponse response, 
			@RequestParam String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		Map<String, Object> bean = sealSeServiceDao.querySealSeServiceMationToEdit(map);
		if(bean != null && !bean.isEmpty()){
			//集合中放入附件信息
			bean.put("enclosureInfo", sealSeServiceDao.queryEnclosureInfo(bean));
			//集合中放入工单接收人信息
			bean.put("serviceUserId", sealSeServiceDao.queryServiceUserNameById(map));
			//集合中放入工单协助人信息
			bean.put("cooperationUserId", sealSeServiceDao.queryCooperationUserNameById(id));
			ToolUtil.sendMessageToPageComJson(response, bean);
		}else{
			ToolUtil.sendMessageToPageComJson(response, "不存在的工单信息。", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: editSealSeServiceMationById
	     * @Description: 编辑售后服务信息
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PutMapping("/sealServiceOrderEdit")
	public void editSealSeServiceMationById(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam String id,
			@RequestBody SealServiceOrderEntity sealServiceOrderEntity) {
		Map<String, Object> map = ToolUtil.javaBean2Map(sealServiceOrderEntity);
		Map<String, Object> bean = sealSeServiceDao.querySealSeServiceState(id);
		if(bean != null && !bean.isEmpty()){
			if("1".equals(bean.get("state").toString()) || "2".equals(bean.get("state").toString())){//1.待派工  2.待接单可以进行编辑
				if(ToolUtil.isBlank(map.get("productWarranty").toString())){
					map.put("productWarranty", null);
				}
				if(!ToolUtil.isBlank(map.get("serviceUserId").toString())){//接收人不为空，则进入待接单状态
					map.put("state", '2');
					if("1".equals(bean.get("state").toString())){
						map.put("serviceTime", ToolUtil.getTimeAndToString());
					}
				}else{
					map.put("state", '1');
				}
				map.put("id", id);
				int size = sealSeServiceDao.editSealSeServiceMationById(map);
				if(size > 0){
					ToolUtil.sendMessageToPageComJson(response);
				}else{
					ToolUtil.sendMessageToPageComJson(response, "编辑工单失败。", "-9999");
				}
			}else{
				ToolUtil.sendMessageToPageComJson(response, "该数据状态已改变，请刷新页面！", "-9999");
			}
		}else{
			ToolUtil.sendMessageToPageComJson(response, "不存在的工单信息。", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceWaitToWorkMation
	     * @Description: 派工时获取派工信息
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealSeServiceWaitToWorkMation")
	public void querySealSeServiceWaitToWorkMation(HttpServletResponse response, 
			@RequestParam String id) {
		Map<String, Object> bean = sealSeServiceDao.querySealSeServiceWaitToWorkMation(id);
		if(bean != null && !bean.isEmpty()){
			ToolUtil.sendMessageToPageComJson(response, bean);
		}else{
			ToolUtil.sendMessageToPageComJson(response, "不存在的工单信息。", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: editSealSeServiceWaitToWorkMation
	     * @Description: 派工
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PostMapping("/sealSeServiceWaitToWorkMation")
	public void editSealSeServiceWaitToWorkMation(HttpServletResponse response, 
			@RequestParam String id,
			@RequestParam String serviceUserId,
			@RequestParam String cooperationUserId) {
		Map<String, Object> bean = sealSeServiceDao.querySealSeServiceState(id);
		if(bean != null && !bean.isEmpty()){
			if("1".equals(bean.get("state").toString())){//1.待派工可以进行派工
				Map<String, Object> map = new HashMap<>();
				map.put("serviceTime", ToolUtil.getTimeAndToString());
				map.put("serviceUserId", serviceUserId);
				map.put("cooperationUserId", cooperationUserId);
				map.put("id", id);
				int size = sealSeServiceDao.editSealSeServiceWaitToWorkMation(map);
				if(size > 0){
					ToolUtil.sendMessageToPageComJson(response);
				}else{
					ToolUtil.sendMessageToPageComJson(response, "派工失败。", "-9999");
				}
			}else{
				ToolUtil.sendMessageToPageComJson(response, "该数据状态已改变，请刷新页面。", "-9999");
			}
		}else{
			ToolUtil.sendMessageToPageComJson(response, "不存在的工单信息。", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceWaitToReceiveMation
	     * @Description: 接单时获取接单信息
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealSeServiceWaitToReceiveMation")
	public void querySealSeServiceWaitToReceiveMation(HttpServletResponse response, 
			@RequestParam String id) {
		Map<String, Object> bean = sealSeServiceDao.querySealSeServiceWaitToReceiveMation(id);
		if(bean != null && !bean.isEmpty()){
			ToolUtil.sendMessageToPageComJson(response, bean);
		}else{
			ToolUtil.sendMessageToPageComJson(response, "不存在的工单信息。", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: insertSealSeServiceWaitToReceiveMation
	     * @Description: 接单
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PostMapping("/sealSeServiceWaitToReceiveMation")
	public void insertSealSeServiceWaitToReceiveMation(HttpServletResponse response, 
			@RequestHeader(value = "userToken") String userId, 
			@RequestParam String id,
			@RequestParam String subscribeTime,
			@RequestParam String remark) {
		Map<String, Object> bean = sealSeServiceDao.querySealSeServiceState(id);
		if(bean != null && !bean.isEmpty()){
			if("2".equals(bean.get("state").toString())){//2.待接单可以进行接单
				Map<String, Object> map = new HashMap<>();
				map.put("serviceId", id);
				map.put("id", ToolUtil.getSurFaceId());
				map.put("createTime", ToolUtil.getTimeAndToString());
				map.put("receiverId", userId);
				map.put("subscribeTime", subscribeTime);
				map.put("remark", remark);
				sealSeServiceDao.insertSealSeServiceWaitToReceiveMation(map);
				sealSeServiceDao.editSealSeServiceWaitToReceiveMation(map);
			}else{
				ToolUtil.sendMessageToPageComJson(response, "该数据状态已改变，请刷新页面。", "-9999");
			}
		}else{
			ToolUtil.sendMessageToPageComJson(response, "不存在的工单信息。", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: querySealSeServiceWaitToSignonMation
	     * @Description: 签到时获取签到信息
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/sealSeServiceWaitToSignonMation")
	public void querySealSeServiceWaitToSignonMation(HttpServletResponse response, 
			@RequestParam String id) {
		Map<String, Object> bean = sealSeServiceDao.querySealSeServiceWaitToSignonMation(id);
		if(bean != null && !bean.isEmpty()){
			ToolUtil.sendMessageToPageComJson(response, bean);
		}else{
			ToolUtil.sendMessageToPageComJson(response, "不存在的工单信息。", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: insertSealSeServiceWaitToReceiveMation
	     * @Description: 签到
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@PostMapping("/sealSeServiceWaitToSignonMation")
	public void insertSealSeServiceWaitToSignonMation(HttpServletResponse response, 
			@RequestHeader(value = "userToken") String userId, 
			@RequestParam String id,
			@RequestParam String longitude,
			@RequestParam String latitude,
			@RequestParam String address,
			@RequestParam String remark) {
		Map<String, Object> bean = sealSeServiceDao.querySealSeServiceState(id);
		if(bean != null && !bean.isEmpty()){
			if("3".equals(bean.get("state").toString())){//3.待签到可以进行签到
				Map<String, Object> map = new HashMap<>();
				map.put("serviceId", id);
				map.put("id", ToolUtil.getSurFaceId());
				map.put("createTime", ToolUtil.getTimeAndToString());
				map.put("registerId", userId);
				map.put("remark", remark);
				map.put("longitude", longitude);
				map.put("latitude", latitude);
				map.put("address", address);
				sealSeServiceDao.insertSealSeServiceWaitToSignonMation(map);
				sealSeServiceDao.editSealSeServiceWaitToSignonMation(map);
			}else{
				ToolUtil.sendMessageToPageComJson(response, "该数据状态已改变，请刷新页面！", "-9999");
			}
		}else{
			ToolUtil.sendMessageToPageComJson(response, "不存在的工单信息。", "-9999");
		}
	}
	
}