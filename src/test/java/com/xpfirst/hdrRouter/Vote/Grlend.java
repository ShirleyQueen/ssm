package com.xpfirst.hdrRouter.Vote;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2017/11/7 上午11:42
 **/
public class Grlend {
    private static final Logger log = LoggerFactory.getLogger(Leju.class);

    @Test
    public void getCode() {

        String url = "http://xiaofu.grlend.cn/Apppool/URequest/Up";

        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("mid", "37138"));
            params.add(new BasicNameValuePair("id", "55198"));

            HttpPost httppost = new HttpPost(url);
            httppost.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0_3 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Mobile/15A432 MicroMessenger/6.5.18 NetType/WIFI Language/zh_CN");

            httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

            BasicClientCookie cookie = new BasicClientCookie("ASP.NET_SessionId", "i0ogwc4hx5lwazlekf5ozkb0");
            cookie.setVersion(0);
            cookie.setPath("/");
            cookie.setDomain("xiaofu.grlend.cn");   //设置范围
            cookieStore.addCookie(cookie);

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
}
