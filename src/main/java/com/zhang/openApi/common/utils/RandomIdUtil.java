package com.zhang.openApi.common.utils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成随机ID
 * @author: zhangfeng
 * @Date: Created in 上午10:59 2018/4/24
 * @param
 */
public class RandomIdUtil {
	private static AtomicInteger counter = new AtomicInteger(0);
	
	public static String getRandomCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String getRandomNumber() {
        if (counter.get() > 999999) {
            counter.set(1);
        }
        long time = System.currentTimeMillis();
        long returnValue = time * 100 + counter.incrementAndGet();
        return String.valueOf(returnValue);
    }

    public static String getRandomNumber (int length) {
		String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] rands = new char[length];
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < rands.length; i++)
		{
			int rand = (int) (Math.random() * a.length());
			stringBuilder.append(a.charAt(rand));
		}
		return stringBuilder.toString();
	}
    
	public static void main(String[] args) {
		System.out.println(getRandomNumber(6));
//		System.out.println(getRandomNumber());
	}
}