package com.app.producer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.app.common.util.ToolUtil;
import com.app.dao.WxSmallProUserMationDao;
import com.app.redis.JedisClientService;
import com.app.wechat.util.WxchatUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @author 卫志强
 *
 */
@RestController
public class WxSmallProUserMationController {
	
	@Autowired
	private WxSmallProUserMationDao wxSmallProUserMationDao;
	
	@Autowired
	private JedisClientService jedisClientService;

	/**
	 * 
	     * @Title: queryUserMationByOpenId
	     * @Description: 根据openId获取用户信息
	     * @return String 返回类型
	     * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getUserMationByOpenId")
	public void queryUserMationByOpenId(HttpServletResponse response, @RequestParam String openId) throws Exception{
		Map<String, Object> map = new HashMap<>();
		//判断该微信用户在redis中是否存在数据
		String key = WxchatUtil.getWechatUserOpenIdMation(openId);
		if(ToolUtil.isBlank(jedisClientService.get(key))){
			//该用户没有绑定账号
			//判断该用户的openId是否存在于数据库
			Map<String, Object> bean = wxSmallProUserMationDao.queryUserMationByOpenId(openId);
			if(bean != null && !bean.isEmpty()){
				//存在数据库
				map.putAll(bean);
				jedisClientService.set(key, JSON.toJSONString(bean));
			}else{
				//不存在
				map.put("id", ToolUtil.getSurFaceId());
				map.put("joinTime", ToolUtil.getTimeAndToString());
				map.put("openId", openId);
				map.put("userId", "");
				wxSmallProUserMationDao.insertWxUserMation(map);
				jedisClientService.set(key, JSON.toJSONString(map));
			}
		}else{
			map = JSONObject.fromObject(jedisClientService.get(key));
		}
		ToolUtil.sendMessageToPageComJson(response, map);
	}
	
}