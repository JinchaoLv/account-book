<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="errorMessage" value="${errorMsg}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>交易分析</title>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        table, thead, tbody, td {
            border: 1px solid black;
        }

        td {
            width: 100px;
            height: 34px;
            text-align: center
        }
    </style>
</head>

<body style="font-size: 16px">

<div>
    <a href="${pageContext.request.contextPath}/transaction/record">交易记录</a>
    <a href="${pageContext.request.contextPath}/transaction/mode">交易方式</a>
    <a href="${pageContext.request.contextPath}/transaction/field">交易类型</a>
    <span style="font-size: 20px; font-weight: bold">交易分析</span>
    <a href="${pageContext.request.contextPath}/user/logout">退出登录(${username})</a>
</div>

<br/>

<div>
    <form action="${pageContext.request.contextPath}/transaction/analysis">
        分析内容：
        <select id="analysis-item" name="analysis-item" style="height: 30px">
            <c:forEach items="${analysisList}" var="analysis">
                <c:if test="${analysis.name == analysis_param.analysisName}">
                    <option value="${analysis.name}" selected="selected">${analysis.name}</option>
                </c:if>
                <c:if test="${analysis.name != analysis_param.analysisName}">
                    <option value="${analysis.name}">${analysis.name}</option>
                </c:if>
            </c:forEach>
        </select><br/><br/>
        目标账单：&nbsp&nbsp
        年：<input id="year" name="year" type="text" value="${analysis_param.year}" style="width: 60px">
        月：
        <select name="month" style="height: 30px">
            <c:forEach items="${analysis_month}" var="month">
                <c:if test="${month.value == analysis_param.month}">
                    <option value="${month.value}" selected="selected">${month.value}</option>
                </c:if>
                <c:if test="${month.value != analysis_param.month}">
                    <option value=${month.value}>${month.value}</option>
                </c:if>
            </c:forEach>
        </select><br/><br/>
        <input value="分析" type="submit"><br/><br/>
        <c:if test="${!empty errorMessage}">
            <span style="color:red">${errorMessage}</span>
        </c:if>
        <br/>
    </form>
</div><br/><br/>

<div>
<table>
    <caption>收入结构</caption>
    <thead>
    <tr>
        <td>项目</td><td>收入</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${analysis_data.incomeItems}" var="incomeItem">
        <tr>
            <td>${incomeItem.fieldName}</td><td>${incomeItem.amount}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div><br/><br/>

<div>
    <table>
        <caption>支出结构</caption>
        <thead>
        <tr>
            <td>项目</td><td>支出</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${analysis_data.expenditureItems}" var="expenditureItem">
            <tr>
                <td>${expenditureItem.fieldName}</td><td>${expenditureItem.amount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
</body>
</html>