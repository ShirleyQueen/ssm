$t.a.DES.encrypt(t,e,{mode:$t.a.mode.ECB,padding:$t.a.pad.Pkcs7}).ciphertext.toString($t.a.enc.Base64)}

var ie="",se="",ae="https://www.fitness-partner.cn/jianshen/ssln/client/";

function ne(t,e,i,s){
    var a,n=localStorage.token?localStorage.token:"",
        o=(new Date).getTime().toString();
    ""==n||null==n||"undefined"==n?
        (ie="",
            a=(a="a="+e+"&params="+i+"&timestamp="+o+"&key=SRp2ED95caZd4adfFL25Q9de0d2E7f0H").
            replace(/[\r\n]/g,"").replace(/"/g,"").replace(/\s/g,""),
            console.log("str======================="+a),

            se=$t.a.MD5(a)):(ie=ee(n).replace(/\+/g,"%2B").replace(/\&/g,"%26"),

        a=(a="a="+e+"&params="+i+"&t="+n+"&timestamp="+o+"&key=SRp2ED95caZd4adfFL25Q9de0d2E7f0H").
        replace(/[\r\n]/g,"").replace(/"/g,"").replace(/\s/g,""),

console.log("str======================="+a),

se=$t.a.MD5(a))