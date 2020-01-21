package com.app.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface SealSeServiceDao {

	public List<Map<String, Object>> querySealServiceWaitToWorkOrder(PageBounds pageBounds);
	
	
}
