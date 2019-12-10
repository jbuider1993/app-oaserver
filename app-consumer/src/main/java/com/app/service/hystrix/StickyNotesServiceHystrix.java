package com.app.service.hystrix;

import org.springframework.stereotype.Component;

import com.app.service.StickyNotesService;

@Component
public class StickyNotesServiceHystrix implements StickyNotesService{

	@Override
	public String queryStickyNotesByUserId(String userToken, int limit, int page) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String insertStickyNotesByUserId(String userToken, String content) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String queryStickyNotesDetailById(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String editStickyNotesById(String userToken, String id, String content) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String deleteStickyNotesById(String userToken, String[] ids) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

}
