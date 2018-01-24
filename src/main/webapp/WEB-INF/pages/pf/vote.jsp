<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = (String) request.getContextPath();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<script src="<%=ctx%>/js/angular-0.10.0.js"></script>
<script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=ctx%>/js/cryptoJS/components/core.js"></script>
<script src="<%=ctx%>/js/cryptoJS/components/cipher-core.js"></script>
<script src="<%=ctx%>/js/cryptoJS/components/tripledes.js"></script>
<script src="<%=ctx%>/js/cryptoJS/components/enc-base64.js"></script>
<script src="<%=ctx%>/js/cryptoJS/components/mode-ecb.js"></script>
<script src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.js"></script>
<script>
    //DES 解密 加密
    function encryptByDES(message) {
        var keyHex = CryptoJS.enc.Utf8.parse('Xhr15T8dGd44IU04bd9EfHc9cF782Yt9');
        var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        });
        console.log(encrypted.ciphertext.toString(CryptoJS.enc.Base64));

        return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
    }

    //DES 解密

    function decryptByDES(text) {
        var keyHex = CryptoJS.enc.Utf8.parse('Xhr15T8dGd44IU04bd9EfHc9cF782Yt9');
        var decrypted = CryptoJS.DES.decrypt(
            {ciphertext: CryptoJS.enc.Base64.parse(text)},
            keyHex,
            {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            }
            );
//        console.log(decrypted.toString(CryptoJS.enc.Utf8));
        return decrypted.toString(CryptoJS.enc.Utf8);
    }
</script>
<body>
<div id="list">

</div>
</body>
<script>
    function getToken() {
        var token = '';
        if(localStorage.token){
            token = localStorage.token;

        }else{
            token = '';
        }

//        return token;
        return "f3330e352f6711d2fa6dc4740b402b9f";
    }

    document.cookie="pgv_pvid=9147043142;path=/; domain=.www.fitness-partner.cn";
    document.cookie="__qc_wId=433; path=/;domain=www.fitness-partner.cn";
    document.cookie="Hm_lpvt_a4de117bdc6f723fd866350b7dc24eb2=1516763501; path=/;domain=.fitness-partner.cn";
    document.cookie="Hm_lvt_a4de117bdc6f723fd866350b7dc24eb2=1516755196; path=/;domain=.fitness-partner.cn";

    var token = '';
    var sign = '';
    function getparams(a,params) {

        var tokenStr = getToken();
        var time = new Date().getTime().toString();

        token = encryptByDES(tokenStr).replace(/\+/g,"%2B").replace(/\&/g,"%26");
        var str = 'a='+ a + '&params='+ params+ '&t='+ tokenStr + '&timestamp=' + time +'&key=SRp2ED95caZd4adfFL25Q9de0d2E7f0H';
        str = str.replace(/[\r\n]/g,'').replace(/"/g,'').replace(/\s/g, "");

        sign = md5(str);
//        console.log('sign=='+ sign);

        params = encryptByDES(params).toString().replace(/\+/g,"%2B").replace(/\&/g,"%26");
        a = encryptByDES(a).toString().replace(/\+/g,"%2B").replace(/\&/g,"%26");
        time = encryptByDES(time).replace(/\+/g,"%2B").replace(/\&/g,"%26");

        var result = 'a='+a + '&timestamp='+ time+ '&params='+params+ '&sign='+ sign;
        result += '&t='+ token ;
        return result

    }
    //获取一次报名人员名单
    function getUserList(id,page,rows) {

        var params='{activityId:"'+id+'",userId:"'+localStorage.userId+'",pageNo:"'+page+'",pageSize:"'+rows+'"}';

        var url = "http://www.fitness-partner.cn/jianshen/ssln/client/serviceActivity?"+getparams('getSignList',params);
        console.log("url="+url);
        $.ajax({
            url:url,
            success:function(result){
                console.log(result);
                $("#list").html(result);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status);
                console.log(XMLHttpRequest.readyState);
                console.log(textStatus);

                var responseText = XMLHttpRequest.responseText;
//                console.log(responseText);

                responseText = responseText.replace(/[\r\n]/g,'');
                responseText = decryptByDES(responseText)
                console.log(responseText);
                //解析数据

            },
            complete: function(XMLHttpRequest, textStatus) {
                console.log("完事");
            }
        });
    }

    //给一个人投票
    function voteToUser(id,voteId,voteUserId) {

        var params = '{activityId:"'+id+'",signId:"'+voteId+'",voteUserId:"'+voteUserId+'",voteIp:"193.23.4.56"}';
        console.log("params="+params);
        params = getparams('vote',params);
//        console.log("params="+params);

        var url = "http://www.fitness-partner.cn/jianshen/ssln/client/serviceActivity?"+params;
//        console.log("url = " + url);
        $.ajax({
            url:url,
            success:function(result){
//                console.log(result);
                $("#list").html(result);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
//                console.log(XMLHttpRequest.status);
//                console.log(XMLHttpRequest.readyState);
//                console.log(textStatus);

                var responseText = XMLHttpRequest.responseText;
//                console.log(responseText);

                responseText = responseText.replace(/[\r\n]/g,'');
                responseText = decryptByDES(responseText);
                console.log("responseText = "+responseText);
                //解析数据

            },
            complete: function(XMLHttpRequest, textStatus) {
                console.log("完事");
            }
        });
    }

    //获取此活动的人员列表
//    getUserList("14b98c6caaa24d0aa90012e9f2dd2815",1,50);

    //投票
    //浪花
    var voteId = "15dcfdda6a444773b0f25cd30b862806";
    //投票人
    var voteUserId = 'dbb0cdeb5ce94b78affdd252411d1713';
    voteToUser("4abe6d1f9d6e4b3aaefbdd75b1fb8e33",voteId,voteUserId);

</script>
</html>