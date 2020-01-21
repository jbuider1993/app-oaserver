package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AppMyNoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/noteconsole", tags = "笔记管理")
public class AppMyNoteController {

	@Autowired
	private AppMyNoteService appMyNoteService;

	/**
	 * 
	     * @Title: queryNoteAllFile
	     * @Description: 获取笔记目录
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/myNote")
	@ApiOperation(value = "获取我的笔记目录", notes = "获取我的笔记目录", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header") })
	public String queryNoteAllFile(@RequestHeader String userToken) {
		return appMyNoteService.queryNoteAllFile(userToken); 
	}
	
	/**
	 * 
	     * @Title: queryNoteContent
	     * @Description: 获取笔记详情
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/myNoteDetail")
	@ApiOperation(value = "获取笔记详情", notes = "获取笔记详情", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"), 
		@ApiImplicitParam(name = "id", value = "笔记id", dataType = "String", required = true, paramType = "query")
	})
	public String queryNoteContent(@RequestHeader String userToken, String id) {
		return appMyNoteService.queryNoteContent(userToken, id); 
	}
	
	/**
	 * 
	     * @Title: queryNewNote
	     * @Description: 获取最新笔记
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/myNewNote")
	@ApiOperation(value = "获取最新笔记", notes = "获取最新笔记", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header") })
	public String queryNewNote(@RequestHeader String userToken) {
		return appMyNoteService.queryNewNote(userToken); 
	}
	
	/**
	 * 
	     * @Title: addNoteFolder
	     * @Description: 新建目录
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/myNoteFolder")
	@ApiOperation(value = "新建目录", notes = "新建目录", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"), 
		@ApiImplicitParam(name = "id", value = "文件父id", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "name", value = "文件名", dataType = "String", required = true, paramType = "query")
	})
	public String addNoteFolder(@RequestHeader String userToken, String id, String name) {
		return appMyNoteService.addNoteFolder(userToken, id, name); 
	}
	
	/**
	 * 
	     * @Title: addNoteContent
	     * @Description: 新增笔记
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/myNote")
	@ApiOperation(value = "新增笔记", notes = "新增笔记", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"), 
		@ApiImplicitParam(name = "pid", value = "父id", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "name", value = "名称", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "type", value = "笔记类型", dataType = "Integer", required = true, paramType = "query"),
		@ApiImplicitParam(name = "desc", value = "描述", dataType = "String", required = false, paramType = "query"), 
		@ApiImplicitParam(name = "content", value = "内容", dataType = "String", required = false, paramType = "query")
	})
	public String addNoteContent(@RequestHeader String userToken, String pid, String name, String type, String desc, String content) {
		return appMyNoteService.addNoteContent(userToken, pid, name, type, desc, content); 
	}
	
	/**
	 * 
	     * @Title: editNoteFileName
	     * @Description: 编辑文件/笔记的名称
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/myNoteFileName")
	@ApiOperation(value = "编辑文件/笔记的名称", notes = "编辑文件/笔记的名称", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"), 
		@ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "name", value = "名称", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "type", value = "类型", dataType = "String", required = true, paramType = "query")
	})
	public String editNoteFileName(@RequestHeader String userToken, String id, String name, String type) {
		return appMyNoteService.editNoteFileName(userToken, id, name, type); 
	}
	
	/**
	 * 
	     * @Title: editNoteContent
	     * @Description: 编辑笔记
	     * @return String 返回类型
	     * 
	 */
	@PutMapping("/myNote")
	@ApiOperation(value = "编辑笔记", notes = "编辑笔记", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"), 
		@ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "name", value = "名称", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "desc", value = "描述", dataType = "String", required = false, paramType = "query"), 
		@ApiImplicitParam(name = "content", value = "内容", dataType = "String", required = false, paramType = "query")
	})
	public String editNoteContent(@RequestHeader String userToken, String id, String name, String desc, String content) {
		return appMyNoteService.editNoteContent(userToken, id, name, desc, content); 
	}
	
	/**
	 * 
	     * @Title: deleteFileFolderById
	     * @Description: 删除文件夹以及文件夹下的所有文件
	     * @return String 返回类型
	     * 
	 */
	@DeleteMapping("/myNote")
	@ApiOperation(value = "删除文件夹以及文件夹下的所有文件", notes = "删除文件夹以及文件夹下的所有文件", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"), 
		@ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "type", value = "类型", dataType = "String", required = true, paramType = "query")
	})
	public String deleteFileFolderById(@RequestHeader String userToken, String id, String type) {
		return appMyNoteService.deleteFileFolderById(userToken, id, type); 
	}
	
	/**
	 * 
	     * @Title: queryMoveToFile
	     * @Description: 获取文件/笔记移动时的选择树
	     * @return String 返回类型
	     * 
	 */
	@GetMapping("/myMoveToFile")
	@ApiOperation(value = "获取文件/笔记移动时的选择树", notes = "获取文件/笔记移动时的选择树", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"), 
		@ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "type", value = "类型", dataType = "String", required = true, paramType = "query")
	})
	public String queryMoveToFile(@RequestHeader String userToken, String id, String type) {
		return appMyNoteService.queryMoveToFile(userToken, id, type); 
	}
	
	/**
	 * 
	     * @Title: editNoteToMoveById
	     * @Description: 保存文件/笔记移动后的信息
	     * @return String 返回类型
	     * 
	 */
	@PostMapping("/myMoveToFile")
	@ApiOperation(value = "保存文件/笔记移动后的信息", notes = "保存文件/笔记移动后的信息", produces = "application/json")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", required = true, paramType = "header"), 
		@ApiImplicitParam(name = "moveid", value = "选择移动的id", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "toid", value = "移动到的id", dataType = "String", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "type", value = "类型", dataType = "String", required = true, paramType = "query")
	})
	public String editNoteToMoveById(@RequestHeader String userToken, String moveid, String toid, String type) {
		return appMyNoteService.editNoteToMoveById(userToken, moveid, toid, type); 
	}
	
}