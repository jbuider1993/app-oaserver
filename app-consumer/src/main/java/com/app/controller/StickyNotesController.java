package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.StickyNotesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 卫志强
 *
 */
@RestController
@Api(value = "/stickyNotes", tags = "便签管理")
public class StickyNotesController {
	
	@Autowired
	private StickyNotesService stickyNotesService;
	
	/**
	 * 
	     * @Title: queryStickyNotesByUserId
	     * @Description: 获取我的便签
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/stickyNotes")
	@ApiOperation(value = "获取我的便签", notes = "获取我的便签", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "limit", value = "分页参数,每页多少条数据", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "page", value = "分页参数,第几页", dataType = "Integer", required = true, paramType = "query"),
	})
	public String queryStickyNotesByUserId(@RequestHeader String userToken, int limit, int page) {
		return stickyNotesService.queryStickyNotesByUserId(userToken, limit, page); 
	}
	
	/**
	 * 
	     * @Title: insertStickyNotesByUserId
	     * @Description: 新增便签
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/stickyNotes")
	@ApiOperation(value = "新增便签", notes = "新增便签", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "content", value = "便签内容", dataType = "String", required = true, paramType = "query"),
	})
	public String insertStickyNotesByUserId(@RequestHeader String userToken, String content) {
		return stickyNotesService.insertStickyNotesByUserId(userToken, content); 
	}
	
	/**
	 * 
	     * @Title: queryStickyNotesDetailById
	     * @Description: 便签编辑回显/详情
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/stickyNotesDetail")
	@ApiOperation(value = "便签编辑回显", notes = "便签编辑回显", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "id", value = "便签id", dataType = "String", required = true, paramType = "query"),
	})
	public String queryStickyNotesDetailById(@RequestHeader String userToken, String id) {
		return stickyNotesService.queryStickyNotesDetailById(userToken, id); 
	}
	
	/**
	 * 
	     * @Title: editStickyNotesById
	     * @Description: 编辑便签
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/stickyNotes")
	@ApiOperation(value = "编辑便签", notes = "编辑便签", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "id", value = "便签id", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "content", value = "便签内容", dataType = "String", required = true, paramType = "query"),
	})
	public String editStickyNotesById(@RequestHeader String userToken, String id, String content) {
		return stickyNotesService.editStickyNotesById(userToken, id, content); 
	}
	
	/**
	 * 
	     * @Title: deleteStickyNotesById
	     * @Description: 删除便签
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/delStickyNotes")
	@ApiOperation(value = "删除便签", notes = "删除便签", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"),
		@ApiImplicitParam(name = "ids", value = "多个便签同时删除的便签id串", dataType = "String", required = true, paramType = "query"),
	})
	public String deleteStickyNotesById(@RequestHeader String userToken, String[] ids) {
		return stickyNotesService.deleteStickyNotesById(userToken, ids); 
	}
	
}
