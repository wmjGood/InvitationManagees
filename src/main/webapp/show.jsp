<%--
  Created by IntelliJ IDEA.
  User: wumengjie
  Date: 2018/08/31
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新闻主页</title>
</head>
<body>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">

    jQuery.noConflict();
    jQuery(function () {
        var title='';
        jQuery("#search").click(function () {
             title=jQuery("#title").val();
            loadData(1);
        });
        loadData(1);
/*        jQuery("#del").click(function () {
            var id = jQuery("#Myid").val();
            alert(id);
            var choose = confirm("确认要删除此贴以及相关信息吗？");
            if (choose == true) {
                location.href = "/del?id=" + id;
            } else {
            }
        });*/

        function loadData(pageIndex) {
            jQuery.ajax({
                url: "/show",
                type: "POST",
                data: {
                    "pageIndex": pageIndex,
                    "title":title
                },
                dataType: "json",
                success: function (data) {
                    jQuery("#mytr").html("");
                    datas(data);
                    jQuery("#pagination").pagination(data.total, {
                        current_page: data.pageNum-1, //当前页面
                        items_per_page: data.pageSize, //每页显示的条目数
                        prev_text: "上一页",
                        next_text: "下一页",
                        callback: function(index){
                            loadData(index+1)
                        }

                    });

                }
            });
        };

        function datas(data) {
            jQuery.each(data.list, function (i, dom) {
                jQuery("#mytr").append("  <tr>\n" +
                    "            <td>" + dom.title + "</td>\n" +
                    "            <td>" + dom.summary + "</td>\n" +
                    "            <td>" + dom.author + "</td>\n" +
                    "            <td>" + dom.createdate + "</td>\n" +
                    "            <td><a href=\"/messageList?id=" + dom.id + "\">查看回复</a> |\n" +
                    "                <a href=\"/del?id="+dom.id+"\">删除</a>\n" +
                    "               <input type=\"hidden\" id=\"Myid\" value=\"" + dom.id + "\"/>\n" +
                    "            </td>\n" +
                    "        </tr>");
            });
        };

    });

</script>


<h1>帖子列表</h1>
帖子标题：<input type="text" name="title" id="title"><input type="button" id="search" value="搜索"/>
<a href="/message">添加回复</a>
<table border="1" id="mytr">
    <tr>
        <th>标题</th>
        <th>内容摘要</th>
        <th>作者</th>
        <th>发布时间</th>
        <th>操作</th>
    </tr>
</table>
<div class="pagination"  id="pagination"></div>

</body>
</html>
