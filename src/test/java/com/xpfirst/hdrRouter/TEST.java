package com.xpfirst.hdrRouter;

import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2017/8/23 上午10:14
 **/
public class TEST {
    private static final Logger log = LoggerFactory.getLogger(TEST.class);

    @Test
    //发送验证码,接口有bug,获取验证码,并不需要使用
    public void getCode() {

        String url = "http://119.80.240.114:8888/ajax/ajaxService";

        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("__type", "bymobile"));

            params.add(new BasicNameValuePair("__xml", "wE2wwRUELalbSkCYUf5Kt+WiVMEVh5sftj9nhLg4cxzSWo2yDhhM86SPZIQTyS7K5Y5EMjg0bNPwBzoocuq7nemTLsKgSYM+3Zux7udBQSX/6ziqf+jqq+rI+uMevQvEg6P7ifkTdFPdxaEiekmRxS33/2WVq5JTKBa+Dx2jDSlZpdBQ/Z+WzBbPcMyO15AtyR+xkK48ZEahSsXFrRODZny1+WKr8mW3zAs8TgTnV/wzf+BQhnrUBX9scVUIqLYe"));
            HttpPost httppost = new HttpPost(url);
            httppost.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0_2 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Mobile/15A421 MicroMessenger/6.5.18 NetType/WIFI Language/zh_CN");

            httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));


            CloseableHttpResponse response = httpClient.execute(httppost);
            //发送Post,并返回一个HttpResponse对象
            String result = EntityUtils.toString(response.getEntity());
            log.info("================= > " + result);

        } catch (Exception e) {
            e.printStackTrace();
            log.info("================= > " + e);
        }finally{

        }
    }

    @Test
    public void checkList(){
        List<String> tmpList = new ArrayList<String>();
        tmpList.add("1");
        tmpList.add("3");
        tmpList.add("2");
        log.info("tmpList=" + tmpList.toString());
        Collections.sort(tmpList);
        log.info("tmpList=" + tmpList.toString());
//        for (String item : tmpList){
//            log.info("item=" + item);
//            tmpList.remove(item);
//        }
    }
    @Test
    public void checkList2(){
        CopyOnWriteArrayList<String> tmpList = new CopyOnWriteArrayList<String>();
        tmpList.add("1");
        tmpList.add("2");
        tmpList.add("3");
        for (String item : tmpList){
            log.info("item=" + item);
            tmpList.remove(item);
        }
        log.info("tmpList=" + tmpList.toString());
    }
}
