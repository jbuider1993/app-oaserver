package com.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface StickyNotesDao {

	public List<Map<String, Object>> selectStickyNotesMation(@Param("createId") String userToken, PageBounds pageBounds);
	
	public int insertStickyNotesMation(Map<String, Object> map);

	public int deleteStickyNotesMation(String[] ids);

	public Map<String, Object> queryStickyNotesDetailById(@Param("id") String id, @Param("createId") String userToken);

	public int editStickyNotesById(@Param("id")String id, @Param("content")String content, @Param("createId")String userToken);

}
