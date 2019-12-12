package com.app.producer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.util.ToolUtil;
import com.app.dao.AdministrativeManagementDao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@RestController
public class AdministrativeManagementController {
	
	@Autowired
	private AdministrativeManagementDao administrativeManagementDao;

	/**
	 * 
	     * @Title: selectAllConferenceRoomMation
	     * @Description: 查看合适的会议室
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/selectAllConferenceRoomMation")
	public void selectAllConferenceRoomMation(HttpServletResponse response, 
			@RequestParam int limit, 
			@RequestParam int page,
			@RequestParam int minCapacity,
			@RequestParam int maxCapacity) {
		List<Map<String, Object>> beans = administrativeManagementDao.selectAllConferenceRoomMation(minCapacity, maxCapacity, new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}

}
