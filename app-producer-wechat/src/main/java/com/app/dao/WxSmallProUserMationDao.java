package com.app.dao;

import java.util.Map;

public interface WxSmallProUserMationDao {

	public Map<String, Object> queryUserMationByOpenId(String openId) throws Exception;

	public int insertWxUserMation(Map<String, Object> map) throws Exception;

}
