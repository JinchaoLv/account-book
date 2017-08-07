<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>账本系统</title>
</head>
<body>
<table>
    <tr>
        <td><a href="${pageContext.request.contextPath}/transaction/record">交易记录</a></td>
        <td><a href="${pageContext.request.contextPath}/transaction/mode">交易方式</a></td>
        <td><a href="${pageContext.request.contextPath}/transaction/field">交易类型</a></td>
        <td><a href="${pageContext.request.contextPath}/transaction/analysis">交易分析</a></td>
        <td><a href="${pageContext.request.contextPath}/user/logout">退出登录</a></td>
    </tr>
</table>
</body>
</html>