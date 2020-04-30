package com.zhang.openApi.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author: zhangfeng
 * @Description: 获取资源文件工具类
 * @Date: Created in 下午9:31 2018/4/13
 * @Modified
 */
public class PropertiesFileUtil {

    //当打开多个资源文件时，缓存资源文件
    private static HashMap<String,PropertiesFileUtil> configMap = new HashMap<String,PropertiesFileUtil>();
    //打开文件时间，判断是否超时
    private Date loadTime = null;
    //资源文件
    private ResourceBundle resourceBundle = null;
    //默认资源文件名称
    private static final String NAME = "config";
    //缓存时间
    private static final Integer TIME_OUT = 60 * 1000;

    private PropertiesFileUtil(String name) {
        this.loadTime = new Date();
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    //创建单例
    public static synchronized PropertiesFileUtil getInstance() {
        return getInstance(NAME);
    }

    public static synchronized PropertiesFileUtil getInstance(String name) {
        PropertiesFileUtil conf = configMap.get(name);
        if (null == conf) {
            conf = new PropertiesFileUtil(name);
            configMap.put(name,conf);
        }
        //判断是否打开的资源文件是否超时1分钟
        if ((System.currentTimeMillis() - conf.getLoadTime().getTime()) > TIME_OUT) {
            conf = new PropertiesFileUtil(name);
            configMap.put(name,conf);
        }
        return conf;
    }

    /**
     * @author: zhangfeng
     * @Description: 根据key读取value
     * @Date: Created in 下午9:46 2018/4/13
     * @param
     */
    public String get(String key) {
        try {
            String value = resourceBundle.getString(key);
            return value;
        }catch (MissingResourceException e) {
            return "";
        }
    }

    /**
     * @Author: zhangfeng
     * @Description: 根据key读取value(int)
     * @Date: Created in 下午9:50 2018/4/13
     * @param
     */
    public Integer getInt(String key) {
        try {
            String value =  resourceBundle.getString(key);
            return Integer.parseInt(value);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * @Author: zhangfeng
     * @Description: 根据key读取value(布尔)
     * @Date: Created in 下午9:50 2018/4/13
     * @param
     */
    public boolean getBool(String key) {
        try {
            String value = resourceBundle.getString(key);
            if ("true".equals(value)) {
                return true;
            }
            return false;
        } catch (MissingResourceException e) {
            return false;
        }
    }

    public Date getLoadTime() {
        return loadTime;
    }
}
