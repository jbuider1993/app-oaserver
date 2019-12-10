package com.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface JobDiaryDao {

	public List<Map<String, Object>> queryJobDiaryDayReceived(@Param("receivedId") String userToken, @Param("diaryType") int diaryType, PageBounds pageBounds);

	public int insertDayJobDiary(Map<String, Object> map) throws Exception;

	public int insertDayJobDiaryReceived(List<Map<String, Object>> beans) throws Exception;

	public List<Map<String, Object>> queryJobDiaryDayMysend(@Param("createId") String userToken, @Param("diaryType") int diaryType, PageBounds pageBounds);

	public Map<String, Object> queryCreateTime(@Param("id") String id);

	public int editJobDiaryDayMysendState(@Param("id") String id);

	public int editJobDiaryWeekMysendState(@Param("id") String id);

	public int editJobDiaryMonthMysendState(@Param("id") String id);

	public Map<String, Object> queryDiaryType(@Param("id") String id);

	public int editMyReceivedJobDiary(@Param("id") String id);

	public int editMyReceivedWeekJobDiary(@Param("id") String id);

	public int editMyReceivedMonthJobDiary(@Param("id") String id);

	public Map<String, Object> queryJobDiaryType(@Param("id") String id);

	public int editJobDiaryDayMysendDelete(@Param("id") String id);

	public int editJobDiaryWeekMysendDelete(@Param("id") String id);

	public int editJobDiaryMonthMysendDelete(@Param("id") String id);

	public int editDayJobDiary(Map<String, Object> map);

	public int deleteJobDiaryReceived(@Param("id") String id);

}
