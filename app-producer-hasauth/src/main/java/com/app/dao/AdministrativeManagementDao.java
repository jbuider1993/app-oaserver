package com.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface AdministrativeManagementDao {

	public List<Map<String, Object>> selectAllConferenceRoomMation(@Param("minCapacity") int minCapacity, @Param("maxCapacity") int maxCapacity, PageBounds pageBounds);

}
