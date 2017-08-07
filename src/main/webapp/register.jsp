<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.lvjc.po.DetailUser"%>
<c:set var="errorMessage" value="${errorMsg}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
    <script>
        function mycheck(){
            if(document.all("user.password").value != document.all("again").value){
                alert("两次输入的密码不一致，请更正。");
                return false;
            }else
            {
                return true;
            }
        }
    </script>
</head>
<body>
    <h2>用户注册信息：</h2>
<form action="${pageContext.request.contextPath}/user/register" method="post" onsubmit="return mycheck()">
    <c:if test="${!empty errorMessage}">
        <div style="color:red">${errorMessage}</div>
    </c:if>
    <table>
        <tr>
            <td width="20%">用户名</td>
            <td width="80%">
                <input type="text" name="username"/>
            </td>
        </tr>
        <tr>
            <td width="20%">密码</td>
            <td width="80%"><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td width="20%">密码确认</td>
            <td width="80%"><input type="password" name="again"></td>
        </tr>
        <tr>
            <td width="20%">性别</td>
            <td width="80%">
                <input type="radio" value="<%=DetailUser.Sex.MALE.getDescription()%>" name="sex">男
                <input type="radio" value="<%=DetailUser.Sex.FEMALE.getDescription()%>" name="sex">女
                <input type="radio" value="<%=DetailUser.Sex.ELSE.getDescription()%>" name="sex">其它
            </td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input type="text" name="age"></td>
        </tr>
        <tr>
            <td>电话</td>
            <td><input type="text" name="phone"></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>个性签名</td>
            <td><textarea cols="50" rows="4"></textarea></td>
        </tr>
        <tr>
            <td>头像</td>
            <td><input type="file" name="icon"></td>
        </tr>
    </table>
    <input type="submit" value="提交">
</form>
</body>
</html>
