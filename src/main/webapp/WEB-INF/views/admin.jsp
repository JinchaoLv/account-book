<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>管理员</title>
</head>
<body>
    <table>
        <tr>
            <td><a href="${pageContext.request.contextPath}/admin/userList">用户管理</a></td>
            <td><a href="${pageContext.request.contextPath}/admin/stopServer">关闭服务</a></td>
        </tr>
    </table>
</body>
</html>