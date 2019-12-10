package com.app.service.hystrix;

import org.springframework.stereotype.Component;

import com.app.service.AppMyNoteService;

@Component
public class AppMyNoteServiceHystrix implements AppMyNoteService{
	
	@Override
	public String queryNoteAllFile(String userToken) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String queryNoteContent(String userToken, String id) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String queryNewNote(String userToken) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String addNoteFolder(String userToken, String id, String name) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String addNoteContent(String userToken, String pid, String name, String type, String desc, String content) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String editNoteFileName(String userToken, String id, String name, String type) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String editNoteContent(String userToken, String id, String name, String desc, String content) {
		throw new RuntimeException("请求失败，服务无法访问");
	}

	@Override
	public String deleteFileFolderById(String userToken, String id, String type) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String queryMoveToFile(String userToken, String id, String type) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
	@Override
	public String editNoteToMoveById(String userToken, String moveid, String toid, String type) {
		throw new RuntimeException("请求失败，服务无法访问");
	}
	
}
