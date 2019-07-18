<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 2019/7/17
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function onBack() {
            window.location.href = "goodsQueryServlet";
        }
    </script>
</head>
<body>
<table border="1">
    <tr>
        <td>商品名字</td>
        <td>${goods.goodsName}</td>
    </tr>
    <tr>
        <td>商品图片</td>
        <td>
            <img src="upload/${goods.goodsPic}" width="150px" height="150px" />
        </td>
    </tr>
    <tr>
        <td>商品价格</td>
        <td>${goods.goodsPrice}</td>
    </tr>
    <tr>
        <td>商品简介</td>
        <td>${goods.goodsDescription}</td>
    </tr>
    <tr>
        <td>商品库存</td>
        <td>${goods.goodsStock}</td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" value="返回" onclick="onBack()"/>
        </td>
    </tr>
</table>

</body>
</html>
