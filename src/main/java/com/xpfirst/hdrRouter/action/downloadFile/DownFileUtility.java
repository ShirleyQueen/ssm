package com.xpfirst.hdrRouter.action.downloadFile;

/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2018/4/11 下午4:11
 **/
/**
 * 简单的工具类
 */
public class DownFileUtility {
    public DownFileUtility() {
    }

    /**
     * 休眠时长
     * @param nSecond
     */
    public static void sleep(int nSecond) {
        try {
            Thread.sleep(nSecond);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印日志信息
     * @param sMsg
     */
    public static void log(String sMsg) {
        System.err.println(sMsg);
    }

    /**
     * 打印日志信息
     * @param sMsg
     */
    public static void log(int sMsg) {
        System.err.println(sMsg);
    }
}