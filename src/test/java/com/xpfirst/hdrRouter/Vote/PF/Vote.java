package com.xpfirst.hdrRouter.Vote.PF;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xpfirst.hdrRouter.util.DesUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
public class Vote {
    private static final Logger log = LoggerFactory.getLogger(Vote.class);

    @Test
    public void phoneFor(){
//        int phone = 1000;
//        for (int i = 0; i < 1000; i++){
//            getCode(phone+i);
//        }

//        getCode(1000);
        voteToUser("123141234252345");
    }
    //发送验证码,接口有bug,获取验证码,并不需要使用
    public void getCode(int num) {

        //获取验证码
        String phone = "1851876" + num;
        String phoneStr = "{phone:'"+phone+"',flag:'1'}"; // java.net class
        try {
            phoneStr = URLEncoder.encode(phoneStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String url = "http://www.fitness-partner.cn/jianshen/a/client/service?a=sendMessage&params=" + phoneStr;


        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httppost = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httppost);


            //发送Post,并返回一个HttpResponse对象
            String result = EntityUtils.toString(response.getEntity());
            log.info("================= > " + result);
            registFP(phone);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("================= > " + e);
        }
    }
    //注册新账号
    public void registFP(String phone) {

        //获取验证码
        String pwd = "5211314";
        String phoneStr = "{phone:'"+phone+"',pwd:'"+pwd+"'}"; // java.net class
        try {
            phoneStr = URLEncoder.encode(phoneStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String url = "http://www.fitness-partner.cn/jianshen/a/client/service?a=register&params=" + phoneStr;


        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httppost = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httppost);


            //发送Post,并返回一个HttpResponse对象
            String result = EntityUtils.toString(response.getEntity());
            log.info("================= > 注册成功 " + result);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("================= > " + e);
        }
    }
    @Test
    //登录
//    public void loginTo(String phone, String pwd)
    public void loginTo()
    {
        String phone = "nanjizhiyin";
        //获取验证码
        String pwd = "5211314";
        String phoneStr = "{phone:'"+phone+"',pwd:'"+pwd+"',loginIp:'120.134.2.3'}"; // java.net class
        try {
            phoneStr = URLEncoder.encode(phoneStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String url = "http://www.fitness-partner.cn/jianshen/a/client/service?a=login&params=" + phoneStr;


        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httppost = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httppost);


            //发送Post,并返回一个HttpResponse对象
            String result = EntityUtils.toString(response.getEntity());

            JSONObject jObject = JSON.parseObject(result);
            Integer code = jObject.getInteger("code");
            if (code.equals(0)){
                JSONObject infoObj = jObject.getJSONObject("info");
                String infoid = infoObj.getString("id");
                log.info("================= > 登录成功 " + infoid);
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.info("================= > " + e);
        }
    }
    @Test
    // 获取用户列表并投票
    public void getUserList(){

        //设置一个总票数
        int allnum = 20;

        //页码 河北队guyan 448人 1,2,8,9
//        String pageNo = "8";
//        String activityId = "9fe25f1066254e6eb9a8c16f6bb855c1";


        //河北队lulong 43人
//        String pageNo = "1";
//        String activityId = "7181b67a30ac42049ed2ecadd0a21cf9";


        //天律队 guyuan 63人
//        String pageNo = "2";
//        String activityId = "ef78232a1c424ee9b1cdf40b3e3004f4";

        //天律队 lulong 26人
//        String pageNo = "1";
//        String activityId = "71976120305d43229e37898fe8e1b51c";


        // 北京 lulong 36人
//        String pageNo = "1";
//        String activityId = "a9cf08d46e724d8ea24d81d50cde0efd";

        // 北京 guyuan 90人
//        String pageNo = "1";
//        String activityId = "b9c31f70a3cd450eb8174b3a633ce477";

        String url = "http://www.fitness-partner.cn/jianshen/ssln/client/serviceActivity?a=LvjMuqPBJRSBpRtOQNzMCw==&t=vHPVBZyKbmSHLkpgSvNxq9ss86lR5PTl7hJLb2VoNn6gwmuSqkle/w==&params=sfE3XwfDk4lBwp0N3EyOx3FYMaRJOD8V8kjUGZBqt6iPEH4x8CE0P7K2FsxEs4Zfr2N1HUma21pARinNHLOfmqT09fL49c98wob1rH2pglayrlCXJA7/KP/TdEVPuJBbnIz2gpgF5gDGIRNhngk/9ah1JqO97osfczvtdz8dWiPCZZ1/UTVN0muoT1nZ%2Bb74oMJrkqpJXv8=";
        url += "&sign=b00dba92a7e609788ab4d8300835de08";
        url += "&timestamp=fwOavDNuf7c2GpDt1NTBTg==";
        try {

            CookieStore cookieStore = new BasicCookieStore();
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

            HttpGet httppost = new HttpGet(url);
            httppost.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9B176 MicroMessenger/6.5.15");

            BasicClientCookie cookie = new BasicClientCookie("pgv_pvid",  "9147043142"
            );
            cookie.setVersion(0);
            cookie.setPath("/");
            cookie.setDomain(".www.fitness-partner.cn");   //设置范围
            cookieStore.addCookie(cookie);

            BasicClientCookie cookie2 = new BasicClientCookie("__qc_wId", "433");
            cookie2.setVersion(0);
            cookie2.setPath("/");
            cookie2.setDomain("www.fitness-partner.cn");   //设置范围
            cookieStore.addCookie(cookie2);

            BasicClientCookie cookie3 = new BasicClientCookie("Hm_lpvt_a4de117bdc6f723fd866350b7dc24eb2", "1516763501");
            cookie3.setVersion(0);
            cookie3.setPath("/");
            cookie3.setDomain(".fitness-partner.cn");   //设置范围
            cookieStore.addCookie(cookie3);

            BasicClientCookie cookie4 = new BasicClientCookie("Hm_lvt_a4de117bdc6f723fd866350b7dc24eb2", "1516755196");
            cookie4.setVersion(0);
            cookie4.setPath("/");
            cookie4.setDomain(".fitness-partner.cn");   //设置范围
            cookieStore.addCookie(cookie4);

            CloseableHttpResponse response = httpclient.execute(httppost);


            //发送Post,并返回一个HttpResponse对象
            String result = EntityUtils.toString(response.getEntity());
            log.info("================= > result: " + result);
            result = DesUtils.decode(result);
            log.info("================= > result: " + result);

            JSONObject jObject = JSON.parseObject(result);
            Integer code = jObject.getInteger("code");
            if (code.equals(0)){
                JSONObject infoObj = jObject.getJSONObject("info");
                JSONObject pageObj = infoObj.getJSONObject("page");
                JSONArray listArr = pageObj.getJSONArray("list");
                for (int i = 0; i < listArr.size(); i++){
                    JSONObject tmpObj = listArr.getJSONObject(i);
                    String signUserId = tmpObj.getString("id");
                    if (signUserId != null && allnum > 0){
                        for (int j = 0; j < 3; j++){
                            log.info("================= > 用户ID: " + signUserId);

//                            String tmpcode = voteToUser(signUserId);
//                            if (tmpcode.equals("0")){
//                                allnum--;
//                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.info("================= > " + e);
        } catch (Exception e) {
            log.info("================= E > " + e);
            e.printStackTrace();
        }
    }
    // 投票给某人
    // voteUserId 投票人的ID
    public String voteToUser(String voteUserId){
        String signId = "";

        //被投票人的ID 李想
//        signId = "d23672fa1c77468e8d8c55344160861c";

        String url = "http://www.fitness-partner.cn/jianshen/ssln/client/serviceActivity?a=Bc6uYgfZr5Y=&t=vHPVBZyKbmSHLkpgSvNxq9ss86lR5PTl7hJLb2VoNn6gwmuSqkle/w==&timestamp=v8yavab/utcqzwm/eNK9sQ==&params=sfE3XwfDk4kJTgit3A9FQSvvWBSSgr3bbytRbvyYS%2BHTuJd9u%2B/w/rfTQ5ZxMNQmmQOynJ/weOulSlrtU14S9P2BU6NnuSAdnZSpKhPOWfkbPV54ZQDzZrnKr024WKcTNpMtlSzHEja5Tg%2BSNBz8XTy3FuV1uwhGd39Lqftj7pJTS2bHthEPpoeARPBboWD5pMmuFPpnbt/ARQthajBI3w==&sign=81fccb63035aa85158abebac214fda04";


        try {
            CookieStore cookieStore = new BasicCookieStore();
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

            HttpGet httppost = new HttpGet(url);
            httppost.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9B176 MicroMessenger/6.5.15");

            BasicClientCookie cookie = new BasicClientCookie("pgv_pvid",  "9147043142"
            );
            cookie.setVersion(0);
            cookie.setPath("/");
            cookie.setDomain(".www.fitness-partner.cn");   //设置范围
            cookieStore.addCookie(cookie);

            BasicClientCookie cookie2 = new BasicClientCookie("__qc_wId", "433");
            cookie2.setVersion(0);
            cookie2.setPath("/");
            cookie2.setDomain("www.fitness-partner.cn");   //设置范围
            cookieStore.addCookie(cookie2);

            BasicClientCookie cookie3 = new BasicClientCookie("Hm_lpvt_a4de117bdc6f723fd866350b7dc24eb2", "1516763501");
            cookie3.setVersion(0);
            cookie3.setPath("/");
            cookie3.setDomain(".fitness-partner.cn");   //设置范围
            cookieStore.addCookie(cookie3);

            BasicClientCookie cookie4 = new BasicClientCookie("Hm_lvt_a4de117bdc6f723fd866350b7dc24eb2", "1516755196");
            cookie4.setVersion(0);
            cookie4.setPath("/");
            cookie4.setDomain(".fitness-partner.cn");   //设置范围
            cookieStore.addCookie(cookie4);



            CloseableHttpResponse response = httpclient.execute(httppost);


            //发送Post,并返回一个HttpResponse对象
            String result = EntityUtils.toString(response.getEntity());
            log.info("================= > result: " + result);
            result = DesUtils.decode(result);
            log.info("================= > result: " + result);

            JSONObject jObject = JSON.parseObject(result);
            log.info("================= > 结果: " + jObject.toJSONString());
            String code = jObject.get("code").toString();
            if (code.equals("0")){
                // 刷访问量
                freshNum(signId);
                return "0";
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.info("================= > " + e);
        } catch (Exception e) {
            log.info("================= >3333 " + e);
            e.printStackTrace();
        }
        return "1";

    }


//    @Test
    // 刷访问量
    public void freshNum(String signId){

        String url = "http://www.fitness-partner.cn/jianshen/f/client/serviceActivity?a=getSignDetail&params=%7Bid:%22"+signId+"%22,userId:%22ergjoiqehtgoqerhgqotr%22%7D";

        // 刷访问量
        for (int j = 0; j < 1; j++){
            try {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpGet httppost = new HttpGet(url);
                httppost.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9B176 MicroMessenger/6.5.15");
                CloseableHttpResponse response = httpclient.execute(httppost);
                log.info("================= > 刷访问量: " + j);

//            //发送Post,并返回一个HttpResponse对象
//            String result = EntityUtils.toString(response.getEntity());
//
//            log.info("================= > 刷访问量: " + result);

            } catch (IOException e) {
                e.printStackTrace();
                log.info("================= > " + e);
            }
        }


    }
}
