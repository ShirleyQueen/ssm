package com.xpfirst.hdrRouter.Vote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by xuexin on 2017/8/8.
 */
public class Hrbxyy {
    private static final Logger log = LoggerFactory.getLogger(Hrbxyy.class);

    @Test
    public void getCode() {

        String url = "http://wx1.hrbxyy.com/app/index.php?i=3&c=entry&rid=125&id=3397&do=vote&m=tyzm_diamondvote&u=344195";

        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        try {

            HttpPost post = new HttpPost(url);
            post.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0_2 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Mobile/15A421 MicroMessenger/6.5.18 NetType/WIFI Language/zh_CN");


            BasicClientCookie cookie = new BasicClientCookie("4f99_cuserinfo",  "eyJvcGVuaWQiOiJvUk5DcDBsUHl3RkhXQVVjM0NTeTctSElTcW93Iiwibmlja25hbWUiOiJcdTUzNTdcdTY3ODEiLCJzZXgiOjEsImxhbmd1YWdlIjoiemhfQ04iLCJjaXR5IjoiXHU2ZDc3XHU2ZGMwIiwicHJvdmluY2UiOiJcdTUzMTdcdTRlYWMiLCJjb3VudHJ5IjoiXHU0ZTJkXHU1NmZkIiwiaGVhZGltZ3VybCI6Imh0dHA6XC9cL3d4LnFsb2dvLmNuXC9tbW9wZW5cL3ZpXzMyXC9EWUFJT2dxODNlcW9JWGpTWDlOZDFpYlZuRWR4cTMzZXhQUUtXYmlhSVVTaWFhblFNTUc2MlNjWXE3d2xTTDNGSXlaMHBoQUVwelBpY1ZJaFk3cjFCWE9LTFFcLzEzMiIsInByaXZpbGVnZSI6W10sImF2YXRhciI6Imh0dHA6XC9cL3d4LnFsb2dvLmNuXC9tbW9wZW5cL3ZpXzMyXC9EWUFJT2dxODNlcW9JWGpTWDlOZDFpYlZuRWR4cTMzZXhQUUtXYmlhSVVTaWFhblFNTUc2MlNjWXE3d2xTTDNGSXlaMHBoQUVwelBpY1ZJaFk3cjFCWE9LTFFcLzEzMiJ9"
            );
            cookie.setVersion(0);
            cookie.setPath("/");
            cookie.setDomain("wx1.hrbxyy.com");   //设置范围
            cookieStore.addCookie(cookie);

            BasicClientCookie cookie2 = new BasicClientCookie("PHPSESSION", "a3316724f4fc3b289cb61e2f60a64783");
            cookie2.setVersion(0);
            cookie2.setPath("/");
            cookie2.setDomain("wx1.hrbxyy.com");   //设置范围
            cookieStore.addCookie(cookie2);

            CloseableHttpResponse response = httpClient.execute(post);
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
