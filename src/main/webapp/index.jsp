<!DOCTYPE html>
<html lang="en">
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
    String ctx = (String) request.getContextPath();
%>
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
<h2>Hello World!</h2>
<form action="<%=ctx%>/user/login" id="logForm">
    <input type="text" name="username" id="username" value=""><br/><button type="submit" id="submitBtn">登录</button>
</form>

<div><a href="<%=ctx%>/nunjucks/list">nunjucks</a></div>
<div><a href="<%=ctx%>/mongodb/">mongoDB</a></div>
<div><a href="<%=ctx%>/mysql/">mysql</a></div>
<div><a href="<%=ctx%>/pf/vote">pf投票</a></div>
</body>

<script>
    if ('serviceWorker' in navigator) {
        navigator.serviceWorker.register('/js/service-worker.js').then(function(registration) {
            // Registration was successful
            console.log('ServiceWorker registration successful with scope: ',    registration.scope);
        }).catch(function(err) {
            // registration failed :(
            console.log('ServiceWorker registration failed: ', err);
        });
    }
</script>
</html>
