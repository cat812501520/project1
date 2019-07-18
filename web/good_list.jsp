<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 2019/7/15
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function onUpdateUrl(gid) {
            window.location.href = "goodsLoadDateServlet?a=1&&gid="+gid;
        }
        function onViewUrl(gid) {
            window.location.href = "goodsLoadDateServlet?a=2&&gid="+gid;
        }
        function onDeleteUrl(gid) {
            window.location.href = "goodsDeleteServlet?gid="+gid;
        }
    </script>
</head>
<body>
<table border="1">
    <caption>商品信息查询</caption>
    <a href="good_save.jsp">添加商品</a>
    <thead>
    <tr>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>商品图片</th>
        <th>商品价格</th>
        <th>商品简介</th>
        <th>商品库存</th>
    </tr>
    </thead>
    <tbody>
    <!-- 遍历学生集合 -->
    <c:forEach items="${goods}" var="goods" varStatus="g">
        <tr>
            <td>${goods.gid}</td>
            <td>${goods.goodsName}</td>
            <td>
                <img src="upload/${goods.goodsPic}" width="100px" height="100px" />
            </td>
            <td>${goods.goodsPrice}</td>
            <td>${goods.goodsDescription}</td>
            <td>${goods.goodsStock}</td>
            <td>
                <input type="button" value="修改" onclick="onUpdateUrl('${goods.gid}')"  />&nbsp;
                <input type="button" value="删除" onclick="onDeleteUrl('${goods.gid}')"  />&nbsp;
                <input type="button" value="详情" onclick="onViewUrl('${goods.gid}')"  />&nbsp;
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
