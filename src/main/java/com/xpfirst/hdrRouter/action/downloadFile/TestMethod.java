package com.xpfirst.hdrRouter.action.downloadFile;

/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2018/4/11 下午4:14
 **/
public class TestMethod {
    public TestMethod() {
        try {
            DownFileInfoBean bean = new DownFileInfoBean(
                    "http://xuexin-web.oss-cn-shanghai.aliyuncs.com/files/program/BJT_online_server_V4.3.0.rar",
                    "/Users/xuexin/Downloads",
                    "BJT_online_server_V4.3.0.rar", 5,true,null);
            /*File file = new File("D:\\dan07.apk");
            DownFileInfoBean bean = new DownFileInfoBean(null, "D:\\temp",
                    "dan07.apk", 3,false,file);*/
            DownFileFetch fileFetch = new DownFileFetch(bean);
            fileFetch.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestMethod();
    }
}