<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 2019/7/15
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function onCheck() {
            var username = document.getElementById("username");
            var password = document.getElementById("password")
            if(!username.value){
                alert("用户名不能为空");
                return false;
            }
            if(!password.value){
                alert("密码不能为空");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<!-- onsubmit：在提交前会调用这个事件里配置的方法，如果方法返回true则提交，返回false不提交 -->
<form action="loginServlet" method="post" onsubmit="return onCheck();">
    用户名：<input type="text" name="username" id="username" /><br/>
    密码：<input type="password" name="password" id="password" /><br/>
    <input type="submit" value="登录" />
    <a href="register.jsp" >还没有帐号？点击注册</a>
</form>
</body>
</html>
