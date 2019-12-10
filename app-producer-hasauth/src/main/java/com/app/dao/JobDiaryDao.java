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
	
	public List<Map<String, Object>> querySysEveUserStaff(@Param("userId") String userId, @Param("userName") String userName, PageBounds pageBounds);

	public Map<String, Object> queryJobDiaryState(@Param("id") String id);

	public int editJobDiaryState(@Param("readTime") String readTime, @Param("id") String id);

	public Map<String, Object> queryJobDiaryDetails(@Param("id") String id);

	public List<Map<String, Object>> queryJobDiaryDayEnclosureInfo(@Param("enclosureInfo") String string);

	public Map<String, Object> selectMysendDetails(@Param("id") String id);

	public int insertWeekJobDiary(Map<String, Object> map);

	public int insertWeekJobDiaryReceived(List<Map<String, Object>> beans);

	public Map<String, Object> selectMysendWeekDetails(@Param("id") String id);

	public Map<String, Object> queryWeekJobDiaryState(@Param("id") String id);

	public int editWeekJobDiaryState(@Param("readTime") String readTime, @Param("id") String id);

	public Map<String, Object> queryWeekJobDiaryDetails(@Param("id") String id);

	public int insertMonthJobDiary(Map<String, Object> map);

	public int insertMonthJobDiaryReceived(List<Map<String, Object>> beans);

	public Map<String, Object> selectMysendMonthDetails(@Param("id") String id);

	public Map<String, Object> queryMonthJobDiaryState(@Param("id") String id);

	public int editMonthJobDiaryState(@Param("readTime") String readTime, @Param("id") String id);

	public Map<String, Object> queryMonthJobDiaryDetails(@Param("id") String id);

	public Map<String, Object> queryJobDiaryDayMysendToEdit(@Param("id") String id);

	public List<Map<String, Object>> queryJobDiaryDayReceivedUserInfoById(@Param("id") String id);

	public Map<String, Object> queryWeekJobDiaryDayMysendToEdit(@Param("id") String id);

	public List<Map<String, Object>> queryWeekJobDiaryDayReceivedUserInfoById(@Param("id") String id);

	public Map<String, Object> queryMonthJobDiaryDayMysendToEdit(@Param("id") String id);

	public List<Map<String, Object>> queryMonthJobDiaryDayReceivedUserInfoById(@Param("id") String id);

	public int editWeekDayJobDiary(Map<String, Object> map);

	public int deleteWeekJobDiaryReceived(Map<String, Object> map);

	public int editMonthDayJobDiary(Map<String, Object> map);

	public int deleteMonthJobDiaryReceived(Map<String, Object> map);

	public int editReceivedJobDiaryToAlreadyRead(@Param("readTime") String readTime, @Param("userId") String userId);

	public int editReceivedWeekJobDiaryToAlreadyRead(@Param("readTime") String readTime, @Param("userId") String userId);

	public int editReceivedMonthJobDiaryToAlreadyRead(@Param("readTime") String readTime, @Param("userId") String userId);

	public List<Map<String, Object>> queryJobDiaryDayNumber(@Param("userId") String userId, @Param("firstTime") String firstTime, @Param("lastTime") String lastTime);

}
