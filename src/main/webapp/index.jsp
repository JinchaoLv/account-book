<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="errorMessage" value="${errorMsg}"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>账本管理系统</title>
        <style type="text/css">
            td{
                text-align:center;
            }
        </style>
    </head>
    <body>
        <h1>欢迎使用账本管理系统</h1>
        <c:if test="${!empty errorMessage}">
            <div style="color:red">${errorMessage}</div>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/user/login">
            <table>
                <tr>
                    <td>用户名：</td>
                    <td>
                        <input type="text" name="username">
                    </td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>
            </table>
            <input type="submit" value="登录">
            <input type="button" value="注册" onclick="window.location.href='${pageContext.request.contextPath}/register.jsp'">
        </form>
    </body>
</html>