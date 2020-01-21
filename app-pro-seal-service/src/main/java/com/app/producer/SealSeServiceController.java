package com.app.producer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}