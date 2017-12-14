<%--
  Created by IntelliJ IDEA.
  User: xuexin
  Date: 2017/12/13
  Time: 上午10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String ctx = (String) request.getContextPath();
%>
<html>
<head>
    <title>${title}</title>
    <script src="<%=ctx%>/js/jquery-3.2.1.min.js"></script>
</head>
<body>

<div class="contain">
    ${text}
    <table>
    <c:if test="${!empty infoList}">
        <tr>
            <td>试卷ID</td>
            <td>题号</td>
            <td>正确答案</td>
        </tr>
        <c:forEach items="${infoList}" var="obj">
        <tr>
            <td>${obj.paperID}</td>
            <td>${obj.paperNum}</td>
            <td>${obj.paperAnswer}</td>
        </tr>
        </c:forEach>
　　 </c:if>

    <c:if test="${!empty recordslist}">
        <tr>
            <td>记录ID</td>
            <td>试卷ID</td>
            <td>学生ID</td>
            <td>题号</td>
            <td>答题结果</td>
            <td>得分</td>
            <td>正确答案</td>
        </tr>
        <c:forEach items="${recordslist}" var="item">

            <c:forEach items="${item.recordsInfo}" var="obj">
            <tr>
                <td>${item.recordsID}</td>
                <td>${item.paperID}</td>
                <td>${item.studentID}</td>
                <td>${obj.paperNum}</td>
                <td>${obj.answer}</td>
                <td>${obj.score}</td>
                <td>${obj.paperAnswer}</td>

            </tr>
            </c:forEach>
        </c:forEach>
    </c:if>

        <c:if test="${!empty scorelist}">
        <tr>
            <td>试卷ID</td>
            <td>学生ID</td>
            <td>总得分</td>
            <td>修改成绩</td>
            <td>删除成绩</td>
        </tr>
        <c:forEach items="${scorelist}" var="obj">
        <tr>
            <td>${obj.paperID}</td>
            <td>${obj.studentID}</td>
            <td>${obj.totalScore}</td>
            <td><a target="_blank" href="scoreedit?recordsID=${obj.recordsID}&totalScore=34">修改为34分</a></td>
            <td><a target="_blank" href="scoredelete?recordsID=${obj.recordsID}">删除</a></td>
        </tr>
        </c:forEach>
        </c:if>
        </teble>
</div>
</body>
</html>
