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
        function onResetImg(_obj) {
            var td= _obj.parentElement;
            var content='<input type="file" name="goodsPic" />';
            content+='<input type="button" value="取消上传" onclick="onCancelImg(this)">';
            td.innerHTML = content;
        }
        function onCancelImg(_obj) {
            var td= _obj.parentElement;
            var content='<img src="upload/${goods.goodsPic}" width="100px" height="50px" />';
            content+='<input type="button" value="重新上传" onclick="onResetImg(this)">';
            content+='<input type="hidden" name="goodsPic" value="${goods.goodsPic}">';
            td.innerHTML = content;
        }
    </script>
</head>
<body>
<form action="goodUpdateServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="gid" value="${goods.gid}" />
    <table border="1">
        <th>
            <td colspan="2">
                 商品编号：${goods.gid}
            </td>
        </th>
        <tr>
            <td>商品名字</td>
            <td><input type="text" name="goodsName" value="${goods.goodsName}" /></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <c:choose>
                    <c:when test="${goods.goodsPic!=null}">
                        <img src="upload/${goods.goodsPic}" width="100px" height="50px" />
                        <input type="button" value="重新上传" onclick="onResetImg(this)">
                        <input type="hidden" name="goodsPic" value="${goods.goodsPic}">
                    </c:when>
                    <c:otherwise>
                        <!-- 上传控件是不能通过脚本去设置value值 -->
                        <input type="file" name="goodsPic" />
                    </c:otherwise>
                </c:choose>
            </td>
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