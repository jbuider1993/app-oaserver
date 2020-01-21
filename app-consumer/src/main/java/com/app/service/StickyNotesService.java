package com.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.hystrix.StickyNotesServiceHystrix;

/**
 * 添加fallback属性（在HelloRemote类添加指定fallback类，在服务熔断的时候返回fallback类中的内容）
 * @author 卫志强
 */
@FeignClient(name= "app-pro-sticky-notes", fallback = StickyNotesServiceHystrix.class)
public interface StickyNotesService {
	
	@RequestMapping(value = "/stickyNotes", method = RequestMethod.GET)
	public String queryStickyNotesByUserId(
			@RequestHeader(value = "userToken") String userToken, 
    		@RequestParam(value = "limit") int limit, 
    		@RequestParam(value = "page") int page);
	
	@RequestMapping(value = "/stickyNotes", method = RequestMethod.POST)
	public String insertStickyNotesByUserId(
			@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "content") String content);

	@RequestMapping(value = "/stickyNotesDetail", method = RequestMethod.GET)
	public String queryStickyNotesDetailById(
			@RequestHeader(value = "userToken") String userToken,
			@RequestParam(value = "id") String id);

	@RequestMapping(value = "/stickyNotes", method = RequestMethod.PUT)
	public String editStickyNotesById(
			@RequestHeader(value = "userToken") String userToken,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "content") String content);

	@RequestMapping(value = "/delStickyNotes", method = RequestMethod.POST)
	public String deleteStickyNotesById(
			@RequestHeader(value = "userToken") String userToken, 
			@RequestParam(value = "ids") String[] ids);

}
