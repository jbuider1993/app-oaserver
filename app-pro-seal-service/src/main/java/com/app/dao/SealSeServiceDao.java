package com.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface SealSeServiceDao {

	public List<Map<String, Object>> querySealSeServiceList(PageBounds pageBounds);
	
	public List<Map<String, Object>> querySealServiceWaitToWorkOrder(PageBounds pageBounds);

	public List<Map<String, Object>> querySealSeServiceWaitToReceiveList(@Param("receiver") String userToken, PageBounds pageBounds);

	public List<Map<String, Object>> querySealSeServiceWaitToSignonList(@Param("receiver") String userToken, PageBounds pageBounds);

	public List<Map<String, Object>> querySealSeServiceWaitToFinishList(@Param("receiver") String userToken, PageBounds pageBounds);

	public List<Map<String, Object>> querySealSeServiceWaitToAssessmentList(@Param("receiver") String userToken, PageBounds pageBounds);

	public List<Map<String, Object>> queryAllSealSeServiceWaitToAssessmentList(PageBounds pageBounds);

	public List<Map<String, Object>> queryAllSealSeServiceWaitToCheckList(PageBounds pageBounds);

	public List<Map<String, Object>> queryAllSealSeServiceFinishedList(PageBounds pageBounds);

	public Map<String, Object> querySealSeServiceToDetails(@Param("id") String id);

	public List<Map<String, Object>> queryEnclosureInfo(Map<String, Object> bean);

	public List<Map<String, Object>> queryComEnclosureInfo(Map<String, Object> bean);

	public List<Map<String, Object>> queryCooperationUserNameById(@Param("id") String id);

	public List<Map<String, Object>> queryMaterialMationById(@Param("id") String id);

	public List<Map<String, Object>> queryFeedbackMationById(@Param("id") String id);
	
}
