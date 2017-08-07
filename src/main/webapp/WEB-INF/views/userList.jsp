<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户管理</title>
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        table, thead, tbody, td {
            border: 1px solid black;
        }

        td {
            text-align: center
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td><a href="${pageContext.request.contextPath}/admin/userList">用户管理</a></td>
    </tr>
</table>
<br/><br/>
<table>
    <thead>
    <tr>
        <td>用户ID</td>
        <td>用户名</td>
        <td>性别</td>
        <td>年龄</td>
        <td>电话</td>
        <td>邮箱</td>
        <td>状态</td>
        <td>更多</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.sex}</td>
            <td>${user.age}</td>
            <td>${user.phone}</td>
            <td>${user.email}</td>
            <td>
                <c:if test="${user.state == '在线'}"><span style="color: green">在线</span></c:if>
                <c:if test="${user.state == '离线'}"><span style="color: grey">离线</span></c:if>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/detail">详细信息</a>&nbsp
                <a href="${pageContext.request.contextPath}/admin/delete/${user.id}">删除用户</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>