package com.app.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.common.constans.Constants;

public class ToolUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ToolUtil.class);

	/**
	 * 
	 * @Title: useLoop @Description: 检查数组是否包含某个值的方法 @param @param
	 *         arr @param @param targetValue @param @return 参数 @return boolean
	 *         返回类型 @throws
	 */
	public static boolean useLoop(String[] arr, String targetValue) {
		for (String s : arr) {
			if (s.equals(targetValue))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: useLoopIndex @Description: 检查数组是否包含某个值的方法 @param @param
	 *         arr @param @param targetValue @param @return 参数 @return boolean
	 *         返回类型 @throws
	 */
	public static int useLoopIndex(String[] arr, String targetValue) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(targetValue))
				return i;
		}
		return -1;
	}

	/**
	 * 
	 * @Title: getUrlParams @Description: 将url参数转换成map @param @param param
	 *         aa=11&bb=22&cc=33 @param @return 参数 @return Map
	 *         <String,Object> 返回类型 @throws
	 */
	public static Map<String, Object> getUrlParams(String param) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (ToolUtil.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=", 2);
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 
	 * @Title: isBlank @Description: 判断字符串是否为空 @param @param str @param @return
	 *         参数 @return boolean 返回类型 @throws
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: isNumeric @Description: 判断是不是数字 @param @param str @param @return
	 *         参数 @return boolean 返回类型 @throws
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: isPhone @Description: 检测手机号是否合格 @param @param
	 *         mobiles @param @return 参数 @return boolean 返回类型 @throws
	 */
	public static boolean isPhone(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 
	 * @Title: isEmail @Description: 验证邮箱 @param @param str @param @return
	 *         参数 @return boolean 返回类型 @throws
	 */
	public static boolean isEmail(String str) {
		String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return match(regex, str);
	}

	/**
	 * 
	 * @Title: isIP @Description: 验证IP地址 @param @param str @param @return
	 *         参数 @return boolean 返回类型 @throws
	 */
	public static boolean isIP(String str) {
		String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
		return match(regex, str);
	}

	/**
	 * 
	 * @Title: isDate @Description: 验证日期时间 @param @param str @param @return
	 *         参数 @return boolean 返回类型 @throws
	 */
	public static boolean isDate(String str) {
		String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		return match(regex, str);
	}

	/**
	 * 
	 * @Title: IsUrl @Description: 验证网址Url @param @param str @param @return
	 *         参数 @return boolean 返回类型 @throws
	 */
	public static boolean isUrl(String str) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return match(regex, str);
	}

	/**
	 * 
	 * @Title: IsPostalcode @Description: 验证输入邮政编号 @param @param
	 *         str @param @return 参数 @return boolean 返回类型 @throws
	 */
	public static boolean isPostalcode(String str) {
		String regex = "^\\d{6}$";
		return match(regex, str);
	}

	/**
	 * 
	 * @Title: match @Description: 如果str 符合 regex的正则表达式格式,返回true, 否则返回
	 *         false; @param @param regex @param @param str @param @return
	 *         参数 @return boolean 返回类型 @throws
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 
	 * @Title: IsIDcard @Description: 验证输入身份证号 @param @param str @param @return
	 *         参数 @return boolean 返回类型 @throws
	 */
	public static boolean isIDcard(String str) {
		String regex = "(^\\d{18}$)|(^\\d{15}$)";
		return match(regex, str);
	}

	/**
	 * 
	 * @Title: isDouble @Description: 验证小数点后两位,一般用于金钱验证 @param @param
	 *         str @param @return 参数 @return boolean 返回类型 @throws
	 */
	public static boolean isDouble(String str) {
		String regex = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
		return match(regex, str);
	}

	/**
	 * 
	 * @Title: getTimeAndToString @Description: 获取当前日期(2016-12-29
	 *         11:23:09) @param @return 参数 @return String 返回类型 @throws
	 */
	public static String getTimeAndToString() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		return nowTime;
	}

	/**
	 * 
	 * @Title: getYmdTimeAndToString @Description:
	 *         获取当前年月日(2016-12-29) @param @return 参数 @return String 返回类型 @throws
	 */
	public static String getYmdTimeAndToString() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = df.format(dt);
		return nowTime;
	}

	/**
	 * @throws ParseException
	 * 
	 * @Title: getThisYmdTimeAndToString @Description:
	 *         获取确定时间对应的年月日(2016-12-29) @param @return 参数 @return String
	 *         返回类型 @throws
	 */
	public static String getThisYmdTimeAndToString(String dt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date b = sdf.parse(dt);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(b);
		return time;
	}

	/**
	 * 
	 * @Title: getHmsTimeAndToString @Description:
	 *         获取当前时分秒(11:23:09) @param @return 参数 @return String 返回类型 @throws
	 */
	public static String getHmsTimeAndToString() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String nowTime = df.format(dt);
		return nowTime;
	}

	/**
	 * 
	 * @Title: getHmTimeAndToString @Description: 获取当前时分秒(11:23) @param @return
	 *         参数 @return String 返回类型 @throws
	 */
	public static String getHmTimeAndToString() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm");
		String nowTime = df.format(dt);
		return nowTime;
	}

	/**
	 * 
	 * @Title: getTimeDifference @Description: 两个时分秒之差 @param @return 参数 @return
	 *         String 返回类型 @throws
	 */
	public static int getTimeDifference(String s1, String s2) {
		String[] t1 = s1.split(":");
		String[] t2 = s2.split(":");
		int a = Integer.parseInt(t1[2]) + Integer.parseInt(t1[1]) * 60 + Integer.parseInt(t1[0]) * 3600;
		int b = Integer.parseInt(t2[2]) + Integer.parseInt(t2[1]) * 60 + Integer.parseInt(t2[0]) * 3600;
		if (a > b) {
			return a - b;
		} else {
			return b - a;
		}

	}

	/**
	 * 
	 * @Title: getSurFaceId @Description: 获取ID @param @return 参数 @return String
	 *         返回类型 @throws
	 */
	public static String getSurFaceId() {
		UUID uuid = java.util.UUID.randomUUID();
		return "" + uuid.toString().replaceAll("-", "");
	}

	/**
	 * 
	 * @Title: MD5 @Description: MD5加密技术 @param @param
	 *         str @param @return @param @throws Exception 参数 @return String
	 *         返回类型 @throws
	 */
	public static String MD5(String str) {
		byte[] bt = str.getBytes();
		StringBuffer sbf = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}
		byte[] bt1 = md.digest(bt);
		sbf = new StringBuffer();
		for (int i = 0; i < bt1.length; i++) {
			int val = ((int) bt1[i]) & 0xff;
			if (val < 16)
				sbf.append("0");
			sbf.append(Integer.toHexString(val));
		}
		return sbf.toString();
	}

	/**
	 * 使用递归方法建树
	 * 
	 * @param treeNodes
	 * @return
	 */
	public static List<Map<String, Object>> allMenuToTree(List<Map<String, Object>> allMenu) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Map<String, Object> bean : allMenu) {
			if ("0".equals(bean.get("parentId").toString())) {
				resultList.add(findChildren(bean, allMenu, 0));
			}
		}
		return resultList;
	}

	/**
	 * 递归查找子节点
	 * 
	 * @param treeNodes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> findChildren(Map<String, Object> treeNode, List<Map<String, Object>> treeNodes,
			int level) {
		List<Map<String, Object>> child = null;
		for (Map<String, Object> it : treeNodes) {
			if (Integer.parseInt(it.get("menuLevel").toString()) == level + 1) {
				if (treeNode.get("id").toString().equals(it.get("parentId").toString().split(",")[level])) {
					child = (List<Map<String, Object>>) treeNode.get("childs");
					if (child == null) {
						child = new ArrayList<>();
					}
					child.add(findChildren(it, treeNodes, level + 1));
					treeNode.put("childs", child);
				}
			}
		}
		return treeNode;
	}

	/**
	 * 使用递归方法建树(笔记)
	 * 
	 * @param treeNodes
	 * @return
	 */
	public static List<Map<String, Object>> allFileToTreeNote(List<Map<String, Object>> beans) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Map<String, Object> bean : beans) {
			if ("2,".equals(bean.get("parentId").toString())) {
				resultList.add(findChildrenNote(bean, beans));
			}
		}
		return resultList;
	}

	/**
	 * 递归查找子节点(笔记)
	 * 
	 * @param treeNodes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> findChildrenNote(Map<String, Object> treeNode,
			List<Map<String, Object>> treeNodes) {
		List<Map<String, Object>> child = null;
		for (Map<String, Object> it : treeNodes) {
			if (treeNode.get("pid").toString().equals(it.get("parentId").toString())) {
				child = (List<Map<String, Object>>) treeNode.get("children");
				if (child == null) {
					child = new ArrayList<>();
				}
				child.add(findChildrenNote(it, treeNodes));
				treeNode.put("children", child);
			}
		}
		return treeNode;
	}

	/**
	 * 使用递归建树
	 * 
	 * @param deskTops
	 * @return
	 */
	public static List<Map<String, Object>> deskTopsTree(List<Map<String, Object>> deskTops) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Map<String, Object> bean : deskTops) {
			if ("0".equals(bean.get("parentId").toString())) {
				resultList.add(findChildren(bean, deskTops, 0));
			}
		}
		for (Map<String, Object> bean : deskTops) {
			if (!findChildren(resultList, bean.get("id").toString())) {
				resultList.add(bean);
			}
		}
		return resultList;
	}

	/**
	 * 递归判断id是否在集合中存在
	 * 
	 * @param treeNode
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean findChildren(List<Map<String, Object>> treeNode, String id) {
		List<Map<String, Object>> child = null;
		for (Map<String, Object> bean : treeNode) {
			if (id.equals(bean.get("id").toString())) {
				return true;
			} else {
				child = (List<Map<String, Object>>) bean.get("childs");
				if (child != null) {
					boolean in = findChildren(child, id);
					if (in) {
						return in;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 获取ip.properties路径
	 * 
	 * @return
	 */
	public static String getIPPropertiesPath() {
		String contextPath = new Object() {
			public String getPath() {
				return this.getClass().getResource("/").getPath();
			}
		}.getPath().substring(1);
		String path = contextPath + "/properties/ip.properties";
		return path;
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 随机不重复的6-8位
	 * 
	 * @return
	 */
	public static int card() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < 6; i++) {
			result = result * 10 + array[i];
		}
		return result;
	}

	/**
	 * 将表名转为Java经常使用的名字，如code_model转CodeModel
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceUnderLineAndUpperCase(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		int count = sb.indexOf("_");
		while (count != 0) {
			int num = sb.indexOf("_", count);
			count = num + 1;
			if (num != -1) {
				char ss = sb.charAt(count);
				char ia = (char) (ss - 32);
				sb.replace(count, count + 1, ia + "");
			}
		}
		String result = sb.toString().replaceAll("_", "");
		return StringUtils.capitalize(result);
	}

	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 写入内容到文件
	 * 
	 * @param content
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean writeTxtFile(String content, File fileName) throws Exception {
		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("GBK"));
			o.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mm != null) {
				mm.close();
			}
		}
		return flag;
	}

	/**
	 * 
	 * @Title: getDateStr @Description: 将日期转化为正常的年月日时分秒 @param @param
	 *         timeStmp @param @return 参数 @return String 返回类型 @throws
	 */
	public static String getDateStr(long timeStmp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date(timeStmp));
	}

	/**
	 * 根据request获取ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpByRequest(HttpServletRequest request) {
		String ip;
		ip = request.getHeader("x-forwarded-for");
		// 针对IP是否使用代理访问进行处理
		if (ToolUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ToolUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ToolUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(0, ip.indexOf(","));
		}
		if (!isBlank(request.getParameter("loginPCIp"))) {
			for (String str : Constants.FILTER_FILE_IP_OPTION) {
				if (ip.indexOf(str) != -1) {
					ip = request.getParameter("loginPCIp");
					break;
				}
			}
		}
		return ip;
	}

	/**
	 * listToTree
	 * <p>
	 * 方法说明
	 * <p>
	 * 将JSONArray数组转为树状结构
	 * 
	 * @param arr
	 *            需要转化的数据
	 * @param id
	 *            数据唯一的标识键值
	 * @param pid
	 *            父id唯一标识键值
	 * @param child
	 *            子节点键值
	 * @return JSONArray
	 */
	public static JSONArray listToTree(JSONArray arr, String id, String pid, String child) {
		JSONArray r = new JSONArray();
		JSONObject hash = new JSONObject();
		// 将数组转为Object的形式，key为数组中的id
		for (int i = 0; i < arr.size(); i++) {
			JSONObject json = (JSONObject) arr.get(i);
			hash.put(json.getString(id), json);
		}
		// 遍历结果集
		for (int j = 0; j < arr.size(); j++) {
			// 单条记录
			JSONObject aVal = (JSONObject) arr.get(j);
			// 在hash中取出key为单条记录中pid的值
			JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
			// 如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
			if (hashVP != null) {
				// 检查是否有child属性
				if (hashVP.get(child) != null) {
					JSONArray ch = (JSONArray) hashVP.get(child);
					ch.add(aVal);
					hashVP.put(child, ch);
				} else {
					JSONArray ch = new JSONArray();
					ch.add(aVal);
					hashVP.put(child, ch);
				}
			} else {
				r.add(aVal);
			}
		}
		return r;
	}

	/**
	 * javaBean2Map
	 * 
	 * @param javaBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> javaBean2Map(Object javaBean) {
		Map<K, V> ret = new HashMap<>();
		try {
			Method[] methods = javaBean.getClass().getDeclaredMethods();// 获取所有的属性
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					Object value = method.invoke(javaBean, (Object[]) null);// invoke(调用)就是调用Method类代表的方法。它可以让你实现动态调用
					ret.put((K) field, (V) (null == value ? "" : value));
				}
			}
		} catch (Exception e) {
			logger.info("entity转map对象：转换失败");
		}
		return ret;
	}

	/**
	 * 
	 * @Title: randomStr @Description: 获取指定的随机值 @param @param
	 *         minLen @param @param maxLen @param @return 参数 @return String
	 *         返回类型 @throws
	 */
	public static String randomStr(int minLen, int maxLen) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		int length = random.nextInt(maxLen - minLen) + minLen;
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Title: getTime @Description: 转化cron @param @param date @param @param
	 *         foramt @param @return 参数 @return int 返回类型 @throws
	 */
	public static int getTime(Date date, String foramt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if ("y".equals(foramt)) {
			return cal.get(Calendar.YEAR);// 获取年份
		} else if ("M".equals(foramt)) {
			return cal.get(Calendar.MONTH) + 1;// 获取月
		} else if ("d".equals(foramt)) {
			return cal.get(Calendar.DAY_OF_MONTH);// 获取日
		} else if ("H".equals(foramt)) {
			return cal.get(Calendar.HOUR_OF_DAY);// 获取时
		} else if ("m".equals(foramt)) {
			return cal.get(Calendar.MINUTE);// 获取分
		} else if ("s".equals(foramt)) {
			return cal.get(Calendar.SECOND);// 获取秒
		} else {
			return -1;
		}
	}

	/**
	 * 
	 * @Title: getAfDate @Description: 获取指定日期 前/后n天|分钟的日期 @param @param
	 *         date @param @param n @param @param format @param @return
	 *         参数 @return Date 返回类型 @throws
	 */
	public static Date getAfDate(Date date, int n, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if ("d".equals(format)) {
			calendar.add(Calendar.DAY_OF_MONTH, n);// 天
		} else if ("m".equals(format)) {
			calendar.add(Calendar.MINUTE, n);// 分钟
		}
		date = calendar.getTime();
		return date;
	}

	/**
	 * 
	 * @Title: formatDateByPattern @Description: 日期转换cron表达式 e.g:yyyy-MM-dd
	 *         HH:mm:ss @param @param date @param @param
	 *         dateFormat @param @return 参数 @return String 返回类型 @throws
	 */
	public static String formatDateByPattern(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}

	/**
	 * 
	 * @Title: getCron @Description: convert Date to cron ,eg. "0 07 10 15 1 ?
	 *         2016" @param @param date @param @return @param @throws
	 *         ParseException 参数 @return String 返回类型 @throws
	 */
	public static String getCron(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd hh:mm");
		String dateFormat = "ss mm HH dd * ? *";
		return formatDateByPattern(df.parse(date), dateFormat);
	}

	public static String getCrons(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormat = "ss mm HH dd  MM ? yyyy";
		Date parse = df.parse(date);
		return formatDateByPattern(parse, dateFormat);
	}

	public static String getCrons1(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormat = "ss mm HH dd MM ? yyyy";
		Date parse = df.parse(date);
		return formatDateByPattern(parse, dateFormat);
	}

	/**
	 * 
	 * @Title: getMinuteByNow @Description: 获取指定分钟后的时间 @param @return 参数 @return
	 *         String 返回类型 @throws
	 */
	public static String getMinuteByNow(int minute) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minute);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(now.getTimeInMillis());
		return dateStr;
	}

	/**
	 * 
	 * @Title: removeTagFromText @Description: 过滤html内容 @param @param
	 *         htmlStr @param @return 参数 @return String 返回类型 @throws
	 */
	public static String removeTagFromText(String htmlStr) {
		if (htmlStr == null || "".equals(htmlStr))
			return "";
		String textStr = "";
		java.util.regex.Pattern pattern;
		java.util.regex.Matcher matcher;

		try {
			String regEx_remark = "<!--.+?-->";
			// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			// 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			htmlStr = htmlStr.replaceAll("\n", "");
			htmlStr = htmlStr.replaceAll("\t", "");
			htmlStr = htmlStr.replaceAll("\r", "");
			pattern = Pattern.compile(regEx_remark);// 过滤注释标签
			matcher = pattern.matcher(htmlStr);
			htmlStr = matcher.replaceAll("");
			pattern = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(htmlStr);
			htmlStr = matcher.replaceAll(""); // 过滤script标签
			pattern = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(htmlStr);
			htmlStr = matcher.replaceAll(""); // 过滤style标签
			pattern = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(htmlStr);
			htmlStr = matcher.replaceAll(""); // 过滤html标签
			pattern = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(htmlStr);
			htmlStr = matcher.replaceAll(""); // 过滤html标签
			htmlStr = htmlStr.replaceAll("\n[\\s| ]*\r", "");
			htmlStr = htmlStr.replaceAll("<(.*)>(.*)<\\/(.*)>|<(.*)\\/>", "");
			textStr = htmlStr.trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textStr;// 返回文本字符串
	}

	/**
	 * 两个时间之间相差距离多少分
	 * 
	 * @param one
	 *            时间参数 1：
	 * @param two
	 *            时间参数 2：
	 * @return 相差天数
	 */
	public static long getDistanceDays(String str1, String str2) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long min = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			min = diff / (1000 * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return min;
	}

	/**
	 * 
	 * @Title: compare @Description: 时间字符串比较大小 @param @param time1 @param @param
	 *         time2 @param @return @param @throws ParseException 参数 @return
	 *         boolean 返回类型 @throws
	 */
	public static boolean compare(String time1, String time2) throws ParseException {
		// 如果想比较日期则写成"yyyy-MM-dd"就可以了
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// 将字符串形式的时间转化为Date类型的时间
		Date a = sdf.parse(time1);
		Date b = sdf.parse(time2);
		// Date类的一个方法，如果a早于b返回true，否则返回false
		if (a.before(b))
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @Title: compareTime @Description: 时分字符串比较大小 @param @param
	 *         time1 @param @param time2 @param @return @param @throws
	 *         ParseException 参数 @return boolean 返回类型 @throws
	 */
	public static boolean compareTime(String time1, String time2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		// 将字符串形式的时间转化为Date类型的时间
		Date t1 = sdf.parse(time1);
		Date t2 = sdf.parse(time2);
		// Date类的一个方法，如果a早于b返回true，否则返回false
		if (t1.before(t2))
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @Title: compareTime @Description: 时分秒字符串比较大小 @param @param
	 *         time1 @param @param time2 @param @return @param @throws
	 *         ParseException 参数 @return boolean 返回类型 @throws
	 */
	public static boolean compareTimeHMS(String time1, String time2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		// 将字符串形式的时间转化为Date类型的时间
		Date t1 = sdf.parse(time1);
		Date t2 = sdf.parse(time2);
		// Date类的一个方法，如果a早于b返回true，否则返回false
		if (t1.before(t2))
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @Title: getTalkGroupNum @Description: 获取聊天群号 @param @return 参数 @return
	 *         String 返回类型 @throws
	 */
	public static String getTalkGroupNum() {
		String numStr = "";
		String trandStr = String.valueOf((Math.random() * 9 + 1) * 10000);
		String dataStr = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
		numStr = trandStr.toString().substring(0, 5);
		numStr = numStr + dataStr;
		return numStr;
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static long getTimeStampAndToString() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * @Title: getSpecifiedDayMation
	 * @Description: 根据指定时间获取各种时间乐行之后的时间
	 * @param date
	 *            指定时间
	 * @param dateType
	 *            时间格式：yyyy-MM-dd hh:mm:ss
	 * @param beforeOrAfter
	 *            往前计算还是往后计算
	 * @param num
	 *            时间长度
	 * @param remindType
	 *            往前计算或者往后计算是按照天还是小时还是分钟计算
	 * @return
	 */
	public static String getSpecifiedDayMation(String dateStr, String dateType, int beforeOrAfter, int num,
			int remindType) throws Exception {
		Calendar c = Calendar.getInstance();
		Date date = null;
		date = new SimpleDateFormat(dateType).parse(dateStr);
		c.setTime(date);
		if (beforeOrAfter == 0)
			num = -num;
		if (remindType == 1 || remindType == 2 || remindType == 3 || remindType == 4) {// 分钟
			c.add(Calendar.MINUTE, num);
		} else if (remindType == 5 || remindType == 6) {// 小时
			c.add(Calendar.HOUR_OF_DAY, num);
		} else if (remindType == 7 || remindType == 8 || remindType == 9) {// 天
			c.add(Calendar.DAY_OF_MONTH, num);
		} else {
			return null;
		}
		String dayAfter = new SimpleDateFormat(dateType).format(c.getTime());
		return dayAfter;
	}

	/**
	 * 获取视频截图
	 * 
	 * @param videoLocation
	 * @param imageLocation
	 * @return
	 */
	public static boolean take(String videoLocation, String imageLocation, String ffmpegGPath) {
		// 低精度
		List<String> commend = new ArrayList<String>();
		commend.add(ffmpegGPath);// 视频提取工具的位置
		commend.add("-i");
		commend.add(videoLocation);
		commend.add("-y");
		commend.add("-f");
		commend.add("image2");
		commend.add("-ss");
		commend.add("08.010");
		commend.add("-t");
		commend.add("0.001");
		commend.add("-s");
		commend.add("352x240");
		commend.add(imageLocation);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * bytes转M或者KB
	 * 
	 * @param size
	 * @return
	 */
	public static String sizeFormatNum2String(long size) {
		String s = "";
		if (size > 1024 * 1024)
			s = String.format("%.2f", (double) size / 1048576) + "M";
		else
			s = String.format("%.2f", (double) size / (1024)) + "KB";
		return s;
	}

	/**
	 * 两个时间之间相差距离多少时分秒
	 * 
	 * @param one
	 *            时间参数 1：
	 * @param two
	 *            时间参数 2：
	 * @return 相差时分秒数
	 */
	public static String getDistanceHMS(String str1, String str2) throws Exception {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date one;
		Date two;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			long hour = diff / (1000 * 60 * 60);
			long minute = (diff - hour * 60 * 60 * 1000) / (1000 * 60);
			long second = (diff - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
			return hour + ":" + minute + ":" + second;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取四位随机码
	 * 
	 * @return
	 */
	public static String getFourWord() {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder(4);
		for (int i = 0; i < 4; i++) {
			char ch = str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * 将文件父id变换
	 * 
	 * @param folderNew
	 * @param folderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static void FileListParentISEdit(List<Map<String, Object>> folderNew, String folderId) {
		for (Map<String, Object> folder : folderNew) {
			folder.put("newParentId", folderId + ",");
			if (folder.get("children") != null) {
				List<Map<String, Object>> child = (List<Map<String, Object>>) folder.get("children");
				FileListParentISEdit(child, folder.get("newParentId").toString() + folder.get("newId").toString());
			}
		}
	}

	/**
	 * 将树转化为list
	 * 
	 * @param folderNew
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> FileTreeTransList(List<Map<String, Object>> folderNew) {
		List<Map<String, Object>> beans = new ArrayList<>();
		for (Map<String, Object> folder : folderNew) {
			if (folder.get("children") != null) {
				List<Map<String, Object>> child = (List<Map<String, Object>>) folder.get("children");
				beans.addAll(FileTreeTransList(child));
				folder.remove("children");
			}
			beans.add(folder);
		}
		return beans;
	}

	/**
	 * 文件复制
	 * 
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void NIOCopyFile(String source, String target) throws Exception {
		// 1.采用RandomAccessFile双向通道完成，rw表示具有读写权限
		RandomAccessFile fromFile = new RandomAccessFile(source, "rw");
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile(target, "rw");
		FileChannel toChannel = toFile.getChannel();
		long count = fromChannel.size();
		while (count > 0) {
			long transferred = fromChannel.transferTo(fromChannel.position(), count, toChannel);
			count -= transferred;
		}
		if (fromFile != null) {
			fromFile.close();
		}
		if (fromChannel != null) {
			fromChannel.close();
		}
		if (toFile != null) {
			toFile.close();
		}
		if (toChannel != null) {
			toChannel.close();
		}
	}

	/**
	 * 往数组中添加新元素
	 * 
	 * @param arr
	 * @param num
	 * @return
	 */
	public static int[] addElementToArray(int[] arr, int num) {
		int[] result = new int[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}
		result[result.length - 1] = num;
		return result;
	}

	/**
	 * 往数组中添加新元素
	 * 
	 * @param arr
	 * @param num
	 * @return
	 */
	public static String[] addElementToArray(String[] arr, String num) {
		String[] result = new String[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}
		result[result.length - 1] = num;
		return result;
	}

	/**
	 * 文件夹打包
	 * 
	 * @param zipOut
	 * @param beans
	 * @param baseDir
	 * @param fileBath
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public static void recursionZip(ZipOutputStream zipOut, List<Map<String, Object>> beans, String baseDir,
			String fileBath) throws Exception {
		String[] zipFileNames = {};
		int[] zipFileNamesNum = {};
		for (Map<String, Object> bean : beans) {
			String name = bean.get("fileName").toString();// 文件压缩包中的文件名
			if (ToolUtil.useLoop(zipFileNames, name)) {
				zipFileNamesNum[ToolUtil.useLoopIndex(zipFileNames,
						name)] = zipFileNamesNum[ToolUtil.useLoopIndex(zipFileNames, name)] + 1;
				if ("folder".equals(bean.get("fileType").toString())) {// 文件夹
					name = name + "(" + String.valueOf(zipFileNamesNum[ToolUtil.useLoopIndex(zipFileNames, name)])
							+ ")";
				} else {// 文件
					name = name.substring(0, name.lastIndexOf(".")) + "("
							+ String.valueOf(zipFileNamesNum[ToolUtil.useLoopIndex(zipFileNames, name)]) + ")."
							+ bean.get("fileType").toString();
				}
			}
			zipFileNames = ToolUtil.addElementToArray(zipFileNames, name);
			zipFileNamesNum = ToolUtil.addElementToArray(zipFileNamesNum, 0);
			if ("folder".equals(bean.get("fileType").toString())) {// 文件夹
				// 空文件夹的处理
				zipOut.putNextEntry(new ZipEntry(baseDir + name + "/"));
				// 没有文件，不需要文件的copy
				zipOut.closeEntry();
				if (bean.containsKey("children") && !isBlank(bean.get("children").toString())) {
					recursionZip(zipOut, (List<Map<String, Object>>) bean.get("children"), baseDir + name + "/",
							fileBath);
				}
			} else {// 文件
				byte[] buf = new byte[1024];
				InputStream input = new FileInputStream(new File(fileBath + bean.get("fileAddress").toString()));
				zipOut.putNextEntry(new ZipEntry(baseDir + name));
				int len;
				while ((len = input.read(buf)) != -1) {
					zipOut.write(buf, 0, len);
				}
				zipOut.closeEntry();
				input.close();
			}
		}
	}

	/**
	 * 获取倒数第num个后面的内容
	 */
	public static String getSubStr(String str, int num) {
		String result = "";
		int i = 0;
		while (i < num) {
			int lastFirst = str.lastIndexOf('/');
			result = str.substring(lastFirst) + result;
			str = str.substring(0, lastFirst);
			i++;
		}
		return result.substring(1);
	}

	/**
	 * 获取集合中的文件夹
	 */
	public static List<Map<String, Object>> getFolderByList(List<Map<String, Object>> beans) {
		List<Map<String, Object>> items = new ArrayList<>();
		for (Map<String, Object> bean : beans) {
			if ("folder".equals(bean.get("fileExtName").toString())) {
				items.add(bean);
			}
		}
		return items;
	}

	/**
	 * 获取集合中的文件
	 */
	public static List<Map<String, Object>> getFileByList(List<Map<String, Object>> beans) {
		List<Map<String, Object>> items = new ArrayList<>();
		for (Map<String, Object> bean : beans) {
			if (!"folder".equals(bean.get("fileExtName").toString())) {
				items.add(bean);
			}
		}
		return items;
	}

	/**
	 * 发送信息给前端
	 */
	public static String getMessageToPageCom(String message, String code) {
		Map<String, Object> object = new HashMap<>();
		object.put("returnCode", code);
		object.put("error", "0");
		object.put("returnMessage", message);
		object.put("total", 0);
		object.put("bean", "");
		object.put("rows", "");
		String context = JSON.toJSON(object).toString();
		return context;
	}

	/**
	 * 发送信息给前端
	 */
	public static void sendMessageToPageComJson(HttpServletResponse response) {
		Map<String, Object> object = new HashMap<>();
		object.put("returnCode", 0);
		object.put("error", "0");
		object.put("returnMessage", "成功");
		object.put("total", 0);
		object.put("bean", "");
		object.put("rows", "");
		Object context = JSON.toJSON(object);
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();// 获取输入流
			if (out != null) {
				out.print(context);// 将信息发送到前台
				out.flush();// 刷新
			}
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();// 关闭输入流
			}
		}
	}

	/**
	 * 发送信息给前端
	 */
	public static void sendMessageToPageComJson(HttpServletResponse response, String message, String code) {
		Map<String, Object> object = new HashMap<>();
		object.put("returnCode", code);
		object.put("error", "0");
		object.put("returnMessage", message);
		object.put("total", 0);
		object.put("bean", "");
		object.put("rows", "");
		Object context = JSON.toJSON(object);
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();// 获取输入流
			if (out != null) {
				out.print(context);// 将信息发送到前台
				out.flush();// 刷新
			}
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();// 关闭输入流
			}
		}
	}

	/**
	 * 发送信息给前端
	 */
	public static void sendMessageToPageComJson(HttpServletResponse response, Map<String, Object> bean) {
		Map<String, Object> object = new HashMap<>();
		object.put("returnCode", 0);
		object.put("error", "0");
		object.put("returnMessage", "成功");
		object.put("total", 0);
		object.put("bean", bean);
		object.put("rows", "");
		Object context = JSON.toJSON(object);
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();// 获取输入流
			if (out != null) {
				out.print(context);// 将信息发送到前台
				out.flush();// 刷新
			}
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();// 关闭输入流
			}
		}
	}

	/**
	 * 发送信息给前端
	 */
	public static void sendMessageToPageComJson(HttpServletResponse response, Map<String, Object> bean, int total) {
		Map<String, Object> object = new HashMap<>();
		object.put("returnCode", 0);
		object.put("error", "0");
		object.put("returnMessage", "成功");
		object.put("total", total);
		object.put("bean", bean);
		object.put("rows", "");
		Object context = JSON.toJSON(object);
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();// 获取输入流
			if (out != null) {
				out.print(context);// 将信息发送到前台
				out.flush();// 刷新
			}
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();// 关闭输入流
			}
		}
	}

	/**
	 * 发送信息给前端
	 */
	public static void sendMessageToPageComJson(HttpServletResponse response, Map<String, Object> bean,
			List<Map<String, Object>> beans) {
		Map<String, Object> object = new HashMap<>();
		object.put("returnCode", 0);
		object.put("error", "0");
		object.put("returnMessage", "成功");
		object.put("total", 0);
		object.put("bean", bean);
		object.put("rows", beans);
		Object context = JSON.toJSON(object);
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();// 获取输入流
			if (out != null) {
				out.print(context);// 将信息发送到前台
				out.flush();// 刷新
			}
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();// 关闭输入流
			}
		}
	}

	/**
	 * 发送信息给前端
	 */
	public static void sendMessageToPageComJson(HttpServletResponse response, Map<String, Object> bean,
			List<Map<String, Object>> beans, int total) {
		Map<String, Object> object = new HashMap<>();
		object.put("returnCode", 0);
		object.put("error", "0");
		object.put("returnMessage", "成功");
		object.put("total", total);
		object.put("bean", bean);
		object.put("rows", beans);
		Object context = JSON.toJSON(object);
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();// 获取输入流
			if (out != null) {
				out.print(context);// 将信息发送到前台
				out.flush();// 刷新
			}
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();// 关闭输入流
			}
		}
	}

	/**
	 * 发送信息给前端
	 */
	public static void sendMessageToPageComJson(HttpServletResponse response, List<Map<String, Object>> beans) {
		Map<String, Object> object = new HashMap<>();
		object.put("returnCode", 0);
		object.put("error", "0");
		object.put("returnMessage", "成功");
		object.put("total", 0);
		object.put("bean", "");
		object.put("rows", beans);
		Object context = JSON.toJSON(object);
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();// 获取输入流
			if (out != null) {
				out.print(context);// 将信息发送到前台
				out.flush();// 刷新
			}
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();// 关闭输入流
			}
		}
	}

	/**
	 * 发送信息给前端
	 */
	public static void sendMessageToPageComJson(HttpServletResponse response, List<Map<String, Object>> beans,
			int total) {
		Map<String, Object> object = new HashMap<>();
		object.put("returnCode", 0);
		object.put("error", "0");
		object.put("returnMessage", "成功");
		object.put("total", total);
		object.put("bean", "");
		object.put("rows", beans);
		Object context = JSON.toJSON(object);
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();// 获取输入流
			if (out != null) {
				out.print(context);// 将信息发送到前台
				out.flush();// 刷新
			}
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();// 关闭输入流
			}
		}
	}

	/**
	 * 纳秒时间戳和随机数生成唯一的随机数
	 * 
	 * @return
	 */
	public static String getUniqueKey() {
		Random random = new Random();
		Integer number = random.nextInt(900000000) + 100000000;
		return System.currentTimeMillis() + String.valueOf(number);
	}

}
