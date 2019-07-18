<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 2019/7/15
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="goodsInsertServlet" method="post" enctype="multipart/form-data">
    <table border="1">
        <tr>
            <td>商品名字</td>
            <td><input type="text" name="goodsName" value="${goods.goodsName}" /></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td><input type="file" name="goodsPic" value="${goods.goodsPic}" /></td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="goodsPrice" value="${goods.goodsPrice}" /></td>
        </tr>
        <tr>
            <td>商品简介</td>
            <td><input type="text" name="goodsDescription" value="${goods.goodsDescription}" /></td>
        </tr>
        <tr>
            <td>商品库存</td>
            <td><input type="text" name="goodsStock" value="${goods.goodsStock}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="保存" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
