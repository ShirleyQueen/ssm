<%--
  Created by IntelliJ IDEA.
  User: xuexin
  Date: 2017/12/12
  Time: 下午3:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div><a target="_blank" href="paper">添加试卷</a>--------------------<a href="paperinfo">试卷信息</a></div>
    <div><a target="_blank" href="student">添加学生</a></div>
    <div><a target="_blank" href="records">添加考试记录</a>-----------------------<a href="recordslist">查询考试记录</a></div>
    <div><a target="_blank" href="scorelist">查询成绩查</a></div>
    <div><a target="_blank" href="scorelist?studentName=郜2">按姓名查询成绩查</a></div>
    <div><a target="_blank" href="scorelist?startScore=23&endScore=30">按分数段查询成绩查</a></div>
</div>
<div>
    <br/>
    <br/>
    <br/>
    需求:
    本周没有研发任务的同学，需要完成mongodb的应用学习，开发一个基于mongoDB的扫描阅卷DEMO

    主要涉及4个实体：学生、学生答题记录、试卷试题、学生成绩。（如果不够可自行添加）
    要求：
    自行安装相关的环境软件，根据业务自己设计数据结构，实现下列场景
    <br/><br/>
    1，查询答卷记录（3-5个学生的答题数据，自己做数据）<br/>
    2，查询正确答案<br/>
    3，判卷保存成绩单和每道题的判卷结果<br/>
    4，查询成绩单级联判卷明细（查询条件：完整列表，姓名、分数段）<br/>
    5，修改学生的成绩<br/>
    6，删除学生的成绩<br/>
    <br/>
    3天完成，不要求界面，可在main函数中执行，在控制台输出结果，周五我们每个人演示各自的程序。

    具体人员为：张静思、马勇、刘红娇、邵任升、郜金丹

    胡义祥因有智能教辅1.2.2的开发任务，所以不做要求，有时间的话也尽快学习熟悉相关应用技术。
</div>


<div style="margin-top: 40px">
    <br/>
    <br/>
    <br/>
    数据表<br/>
    <br/>
    1.试卷(xsl_paper):<br/>
    试卷ID(paperID),<br/>
    题号(paperNum),<br/>
    正确答案(paperAnswer),<br/>
    <br/>
    2.学生(xsl_student):<br/>
    学生ID(studentID),<br/>
    姓名(name),<br/>
    <br/>
    3.考试答题记录(xsl_records):<br/>
    记录ID(recordsID),<br/>
    试卷ID(paperID),<br/>
    学生ID(studentID),<br/>
    总得分(totalScore),<br/>
    记录信息(recordsInfo [{<br/>
    -----------------题号(paperNum),<br/>
    -----------------答题结果(answer),<br/>
    -----------------得分(score)<br/>
    -----------------}])<br/>
    <br/>
</div>
<br/>
<br/>
<div>
    总结:
    1.不能读取单个字段的值,只能是全部字段加上别名的值.
    2.xsl_records中的recordsInfo(数组)不适合被查询,如果是map适合查询
</div>


</body>
</html>
