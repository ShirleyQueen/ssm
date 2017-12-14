package com.xpfirst.hdrRouter.Vote.PF;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

        getCode(1000);
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
    // 获取用户列表
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
        String pageNo = "1";
        String activityId = "b9c31f70a3cd450eb8174b3a633ce477";

        String url = "http://www.fitness-partner.cn/jianshen/f/client/serviceActivity?a=getSignList&params=%7BactivityId:%22";
        url += activityId;
        url += "%22,userId:%22";
        url += "120305d43229e3789d034ae9+1";//这个数值没有作用,随便修改了一个数据
        url += "%22,signIndustry:%22%22,searchInfo:%22%22,pageNo:%22";
        url += pageNo;
        url += "%22,pageSize:%2250%22%7D";

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httppost = new HttpGet(url);
            httppost.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9B176 MicroMessenger/6.5.15");
            CloseableHttpResponse response = httpclient.execute(httppost);


            //发送Post,并返回一个HttpResponse对象
            String result = EntityUtils.toString(response.getEntity());

            JSONObject jObject = JSON.parseObject(result);
            Integer code = jObject.getInteger("code");
            if (code.equals(0)){
                JSONObject infoObj = jObject.getJSONObject("info");
                JSONObject pageObj = infoObj.getJSONObject("page");
                JSONArray listArr = pageObj.getJSONArray("list");
                for (int i = 0; i < listArr.size(); i++){
                    JSONObject tmpObj = listArr.getJSONObject(i);
                    String signUserId = tmpObj.getString("signUserId");
                    if (signUserId != null && allnum > 0){
                        for (int j = 0; j < 3; j++){
                            log.info("================= > 用户ID: " + signUserId);

                            String tmpcode = voteToUser(signUserId);
                            if (tmpcode.equals("0")){
                                allnum--;
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.info("================= > " + e);
        }
    }
    // 投票给某人
    // voteUserId 投票人的ID
    public String voteToUser(String voteUserId){

        String signId = "";

        //被投票人的ID 李想
//        signId = "d23672fa1c77468e8d8c55344160861c";

//        //被投票人的ID 崔爽
//         signId = "65da1388d29746dfad69e3a1ddc14465";

//        //被投票人的ID 郜金丹
//        signId = "6d01823fbec64919a27cafb6a220ec76";

        // 史倍倍
//        signId = "0bdae03cde7940e7a4b10e7b13f9753c";

        // 孙欣欣
//        signId = "7a663889925f4b469c6af96d95b29704";

        //蓝大姐(朱刘芳)
//        signId = "8852ffecc6414bfa94e2295e3c8618bb";
        //魏莹莹
//        signId = "87697048f1b145498d17d9d553e5cd0a";

        //张文俊
//        signId = "04af7c80246849919a6a3ee5429a1f7d";

//        //陈亮
//        signId = "18a4c17fea204e57ae2a9ab0a12f14b4";

        // 吴美莹
//        signId = "60253d760ae147c785de40ff878c3cd3";
//        // 严辰
//        signId = "ef32cac57c0148cbbab9a2f67fce31e1";
//        // 谢天艺
//        signId = "5dad40ca86024b3589587695f190b3f3";

//        // 蔡锦
        signId = "0ffdff8cf0ca4e788690be371381e2c7";
//        // 李永豪
//        signId = "f8846b8b45b54e5a9cabbe144f75e5b6";

//        // 荆雅
//        signId = "4f026beceb9b4116bb42dc5441a3adcd";

//        // Tangu
//        signId = "1ce4cd17f99c442584266b6dfdaf06c7";

//        // 王琳
//        signId = "9893ae2606474dc4a2fc8d4de499cdb2";





        String url = "http://www.fitness-partner.cn/jianshen/f/client/serviceActivity?a=vote&params=%7BvoteUserId:%22"+voteUserId+"%22,activityId:%227ae3d9a0ea87438fa8e6b5267745ca94%22,signId:%22"+signId+"%22,voteIp:%22210.12.48.132%22%7D";


        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httppost = new HttpGet(url);
            httppost.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9B176 MicroMessenger/6.5.15");
            CloseableHttpResponse response = httpclient.execute(httppost);


            //发送Post,并返回一个HttpResponse对象
            String result = EntityUtils.toString(response.getEntity());

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
