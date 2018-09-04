<%--
  Created by IntelliJ IDEA.
  User: wumengjie
  Date: 2018/08/31
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>添加回复</title>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
</head>
<body>

<form id="MyForm" name="MyForm" method="post" action="/message">
    <h1>添加回复</h1>
    内容：<textarea name="Mycontent" id="Mycontent" cols="30" rows="10"></textarea>
    昵称：<input name="anthor" type="text"/>
    <input type="hidden" id="id" name="id" value="${id}"/>
    <input type="submit" value="确认"/>
</form>



</body>
</html>
