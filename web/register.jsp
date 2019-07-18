<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 2019/7/15
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function onCheck() {
            var flag = true;
            flag = flag && onValidateUsername();
            flag = flag && onValidatePassword();
            flag = flag && onValidateConfPassword();
            flag = flag && onValidateEmail();
            flag = flag && onValidatePhone();
            flag = flag && onValidateAddress();
            flag = flag && onValidateSex();
            return flag;
        }
        function onValidateUsername() {
            var usernameObj = document.getElementById("username");
            if(usernameObj.value){
                return true;
            }else{
                alert("用户名不能为空");
                return false;
            }
        }
        function onValidateConfPassword() {
            var passwordObj = document.getElementById("password");
            var confPwd = document.getElementById("confPwd");
            if(passwordObj.value!=confPwd.value){
                alert("密码和确认密码必须一致");
                return false;
            }
            return true;
        }
        function onValidatePassword() {
            var passwordObj = document.getElementById("password");
            if(passwordObj.value){
                return true;
            }else{
                alert("密码不能为空");
                return false;
            }
        }
        function onValidateEmail() {
            var emailObj = document.getElementById("email");
            if(emailObj.value){
                // 判断是否包含@和.符号
                var index1 = emailObj.value.indexOf("@");
                var index2 = emailObj.value.indexOf(".");
                if(index1==-1 || index2==-1){
                    alert("邮箱格式非法：必须包含@和.符号");
                    return false;
                }
                // @和.符号都不能在首尾字符
                if(index1==0 || index2==0
                    || index1==emailObj.value.length-1
                    || index2==emailObj.value.length-1){
                    alert("邮箱格式非法：@和.符号都不能在首尾字符");
                    return false;
                }
                // @不能在.符号之后
                if(index1>index2){
                    alert("邮箱格式非法：@不能在.符号之后");
                    return false;
                }
                // @和.符号不能相邻
                if(index1==index2-1){
                    alert("邮箱格式非法：@和.符号不能相邻");
                    return false;
                }
                return true;
            }else{
                return true;
            }
        }
        function onValidatePhone() {
            var phoneObj = document.getElementById("phone");
            if(phoneObj.value){
                if(phoneObj.value.length!=11){
                    alert("手机号码格式不正确");
                    return false;
                }
                return true;
            }else{
                return true;
            }
        }
        function onValidateAddress() {
            var addressObj = document.getElementById("address");
            if(!addressObj.value){
                alert("地址必须填写");
                return false;
            }else{
                return true;
            }
        }
        function onValidateSex() {
            var sexObj = document.getElementById("sex");
            if(sexObj.value){
                return true;
            }else{
                alert("性别必须填写");
                return false;
            }
        }

    </script>
</head>
<body>
<form action="registerServlet" method="post" onsubmit="return onCheck();">
    <table border="1">
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" id="username" /></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" id="password" /></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input type="password" id="confPwd" /></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <select id="sex" name="sex">
                    <option value="">--请选择--</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>兴趣爱好：</td>
            <td>
                <%--<input type="text" name="hobbys" id="hobbys" />--%>
                <input type="checkbox" name="hobbys" value="篮球" />篮球
                <input type="checkbox" name="hobbys" value="足球" />足球
                <input type="checkbox" name="hobbys" value="乒乓球" />乒乓球
                <input type="checkbox" name="hobbys" value="羽毛球" />羽毛球
            </td>
        </tr>
        <tr>
            <td>手机号：</td>
            <td><input type="text" name="phone" id="phone" /></td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td><input type="text" name="email" id="email" /></td>
        </tr>
        <tr>
            <td>地址：</td>
            <td><input type="text" name="address" id="address" /></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="注册"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
