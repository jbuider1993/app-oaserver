package com.app.producer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.util.ToolUtil;
import com.app.dao.AppCompanyChatDao;

/**
 * 
 * @author 卫志强
 * 公司管理
 */
@RestController
public class AppCompanyChatController {
	
	@Autowired
	private AppCompanyChatDao appCompanyChatDao;

	/**
	 * 
	     * @Title: queryCompanyDepartment
	     * @Description: 获取公司部门数据
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/compantUserChan")
	public void queryCompanyDepartment(HttpServletResponse response, @RequestParam String userToken) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userToken);
		//获取公司单位
		List<Map<String, Object>> companyDepartment = appCompanyChatDao.queryCompanyDepartmentByUserId(map);
		
		//循环获取分组的人列表
		for(Map<String, Object> depart : companyDepartment){
			List<Map<String, Object>> userList = null;
			userList = appCompanyChatDao.queryDepartmentUserByDepartId(depart);
			depart.put("list", userList);
		}
		ToolUtil.sendMessageToPageComJson(response, companyDepartment);
	}
	
}