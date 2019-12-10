package com.app.redis.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RedisToolUtil {
	
	/**
	 * 
	     * @Title: getTimeAndToString
	     * @Description: 获取当前日期(2016-12-29 11:23:09)
	     * @param @return    参数
	     * @return String    返回类型
	     * @throws
	 */
	public static String getTimeAndToString() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		return nowTime;
	}
	
	/**
	 * 
	     * @Title: getDateStr
	     * @Description: 将日期转化为正常的年月日时分秒
	     * @param @param timeStmp
	     * @param @return    参数
	     * @return String    返回类型
	     * @throws
	 */
	public static String getDateStr(long timeStmp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date(timeStmp));
	}
	
}
