<%--
  Created by IntelliJ IDEA.
  User: wumengjie
  Date: 2018/08/31
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
   $(function () {
       $("#addMessage").click(function () {
           var idd=$("#idd").val();
           alert(idd)
          location.href="/message?id="+idd;
       });
   });

</script>
<head>
    <title>回复信息列表</title>
</head>
<body>

<h1>回复信息列表</h1>
<a href="javascript:void(0)" id="addMessage">添加回复</a>
<a href="#">返回帖子列表</a>
<input type="hidden" id="idd" value="${id}">
<table border="1">
    <tr>
        <th>回复内容</th>
        <th>回复昵称</th>
        <th>发布时间</th>
    </tr>
    <c:forEach items="${messageList}" var="item">
        <tr>
            <td>${item.content}</td>
            <td>${item.author}</td>
            <td>${item.createdate}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
