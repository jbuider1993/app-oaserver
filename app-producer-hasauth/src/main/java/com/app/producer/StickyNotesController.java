package com.app.producer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.util.ToolUtil;
import com.app.dao.StickyNotesDao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * @author 卫志强
 *
 */
@RestController
public class StickyNotesController {
	
	@Autowired
	private StickyNotesDao stickyNotesDao;
	
	/**
	 * 
	     * @Title: queryJobDiaryDayReceived
	     * @Description: 遍历我收到的日志
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@GetMapping("/stickyNotes")
	public void queryStickyNotesByUserId(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam int limit, 
			@RequestParam int page) {
		List<Map<String, Object>> beans = stickyNotesDao.selectStickyNotesMation(userToken, new PageBounds(page, limit));
		PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
		ToolUtil.sendMessageToPageComJson(response, beans, beansPageList.getPaginator().getTotalCount());
	}
	
	/**
	 * 
	     * @Title: insertStickyNotesByUserId
	     * @Description: 新增便签
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/stickyNotes")
	public void insertStickyNotesByUserId(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam String content) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", ToolUtil.getSurFaceId());
		map.put("content", content);
		map.put("createId", userToken);
		map.put("createTime", ToolUtil.getTimeAndToString());
		stickyNotesDao.insertStickyNotesMation(map);
		ToolUtil.sendMessageToPageComJson(response);
	}
	
	/**
	 * 
	     * @Title: queryStickyNotesDetailById
	     * @Description: 便签编辑回显/详情
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/stickyNotesDetail")
	public void queryStickyNotesDetailById(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam String id) {
		Map<String, Object> map = stickyNotesDao.queryStickyNotesDetailById(id, userToken);
		if(map != null && !map.isEmpty()){
			ToolUtil.sendMessageToPageComJson(response, map);
		}else{
			ToolUtil.sendMessageToPageComJson(response, "数据不存在.", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: editStickyNotesById
	     * @Description: 编辑便签
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/stickyNotes")
	public void editStickyNotesById(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam String id,
			@RequestParam String content) {
		Map<String, Object> map = stickyNotesDao.queryStickyNotesDetailById(id, userToken);
		//如果对象不为空，则编辑
		if(map != null && !map.isEmpty()){
			stickyNotesDao.editStickyNotesById(id, content, userToken);
			ToolUtil.sendMessageToPageComJson(response);
		}else{
			ToolUtil.sendMessageToPageComJson(response, "数据不存在.", "-9999");
		}
	}
	
	/**
	 * 
	     * @Title: deleteStickyNotesById
	     * @Description: 删除便签
	     * @return String 返回类型
	     * 
	 */
	@DeleteMapping("/stickyNotes")
	public void deleteStickyNotesById(HttpServletResponse response, 
			@RequestHeader String userToken, 
			@RequestParam String[] ids) {
		stickyNotesDao.deleteStickyNotesMation(ids);
		System.out.println(ids);
		ToolUtil.sendMessageToPageComJson(response);
	}
	
}
