package com.zhang.openApi.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析json数据工具类
 * @author: zhang
 * @Date: Created in 上午11:01 2018/4/24
 * @param
 */
public class JsonUtil {
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	private static JsonObject jsonObj;
	private static JsonParser parse;
	private static Gson gson;

	static {
		if (jsonObj == null) {
			jsonObj = new JsonObject();
		}
		if (parse == null) {
			parse = new JsonParser();
		}
		if (gson == null) {
			gson = new Gson();
		}
	}

	/**
	 * 根据node节点，解析json字符串为Object对象
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param node
	 *            json字符串节点
	 * @param cls
	 *            对象Class
	 * @return Object Bean对象
	 * 
	 * @author zhang
	 */
	public static Object jsonToBean(String jsonStr, String node, Class<?> cls) {
		Object bean = null;
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStr).getAsJsonObject();
		if (gson != null) {
			bean = gson.fromJson(jsonObj.get(node), cls);
		}
		return bean;
	}

	/**
	 * 根据node节点，解析json字符串为List对象
	 * 
	 * @author ErweiCui
	 * @date 下午5:28:20
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param node
	 *            json字符串节点
	 * @param typeOfT
	 * @return
	 */
	public static <T> List<T> jsonToListBean(String jsonStr, String node, Type typeOfT) {
		List<T> beanList = new ArrayList<T>();
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStr).getAsJsonObject();
		if (gson != null) {
			beanList = gson.fromJson(jsonObj.get(node), typeOfT);
		}
		return beanList;
	}

	/**
	 * json转map
	 *
	 * @author zhang
	 * @date 2017年1月4日 下午11:50:44
	 *
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		gson = new GsonBuilder().create();
		if (gson != null) {
			map = gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {
			}.getType());
		}
		return map;
	}

	/**
	 * 根据node节点，获取node节点内json
	 * 
	 * @param jsonStrSrc
	 *            json字符串
	 * @param node
	 *            json字符串节点
	 * @return
	 * @Author ErweiCui
	 * @Date 2014-6-18 下午01:29:12
	 * @Version V1.0
	 * 
	 */
	public static String getJsonStrByNode(String jsonStrSrc, String node) {
		String jsonStr = null;
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStrSrc).getAsJsonObject();
		if (gson != null) {
			jsonStr = jsonObj.get(node).toString();
		}
		return jsonStr;
	}

	/**
	 * 解析json字符串为Object对象
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param cls
	 *            对象Class
	 * @return Object Bean对象
	 * 
	 * @author zhang
	 */
	public static Object jsonToBean(String jsonStr, Class<?> cls) {
		Object bean = null;
		gson = new GsonBuilder().create();
		if (gson != null) {
			bean = gson.fromJson(jsonStr, cls);
		}
		return bean;
	}

	/**
	 * 根据node节点，解析json字符串为List对象
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param typeOfT
	 *            json字符串节点
	 * @return Object Bean对象
	 * 
	 * @author zhang
	 */
	public static <T> List<T> jsonToListBean(String jsonStr, Type typeOfT) {
		List<T> beanList = new ArrayList<T>();
		gson = new GsonBuilder().create();
		if (gson != null) {
			beanList = gson.fromJson(jsonStr, typeOfT);
		}
		return beanList;
	}

	/**
	 * 对象转json
	 * 
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean) {
		try {
			Gson gson = new GsonBuilder().create();
			return gson.toJson(bean);
		} catch (Exception e) {
			logger.error("beanToJson错误{}", e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据node节点，删除node节点内json
	 * 
	 * @param jsonStrSrc
	 *            json字符串
	 * @param nodes
	 *            json字符串节点
	 * @return
	 * @Author ErweiCui
	 * @Date 2014-6-18 下午01:29:12
	 * @Version V1.0
	 * 
	 */
	public static String removeJsonStrByNodes(String jsonStrSrc, String... nodes) {
		String jsonStr = null;
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStrSrc).getAsJsonObject();
		if (gson != null) {
			for (String node : nodes) {
				jsonObj.remove(node);
			}
			jsonStr = jsonObj.toString();
		}
		return jsonStr;
	}

	/**
	 * 根据node节点，添加node节点内json
	 * 
	 * @param jsonStrSrc
	 *            json字符串
	 * @param node
	 *            json字符串节点
	 * @return
	 * @Author ErweiCui
	 * @Date 2014-6-18 下午01:29:12
	 * @Version V1.0
	 * 
	 */
	public static String addJsonStrByNode(String jsonStrSrc, String jsonAddValueStr, String node) {
		String jsonStr = null;
		JsonObject jsonAddValueObj;
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStrSrc).getAsJsonObject();
		jsonAddValueObj = parse.parse(jsonAddValueStr).getAsJsonObject();
		if (gson != null) {
			jsonObj.add(node, jsonAddValueObj);
			jsonStr = jsonObj.toString();
		}
		return jsonStr;
	}

}
