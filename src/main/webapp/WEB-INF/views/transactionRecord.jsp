<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="errorMessage" value="${errorMsg}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>交易记录</title>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="<c:url value="/resources/laydate/laydate.js"/>" type="text/javascript"></script>
    <style type="text/css">
        .transactionTable {
            border-collapse: collapse;
        }

        .transactionTable, .transactionThead, .transactionTbody, .transactionTd {
            border: 1px solid black;
        }

        .transactionTd {
            width: 100px;
            height: 34px;
            text-align: center
        }

        .operationTd {
            border: 1px solid black;
            width: 150px;
            text-align: center
        }
    </style>
</head>

<body style="font-size: 16px">

<div>
    <span style="font-size: 20px; font-weight: bold">交易记录</span>
    <a href="${pageContext.request.contextPath}/transaction/mode">交易方式</a>
    <a href="${pageContext.request.contextPath}/transaction/field">交易类型</a>
    <a href="${pageContext.request.contextPath}/transaction/analysis">交易分析</a>
    <a href="${pageContext.request.contextPath}/user/logout">退出登录(${username})</a>
</div>

<br/>

<div>
    <form action="${pageContext.request.contextPath}/transaction/record">
        交易查询：&nbsp&nbsp&nbsp&nbsp
        交易编号<input type="text" name="id">&nbsp&nbsp
        交易日期<input type="text" name="date">&nbsp&nbsp
        <input type="submit" value="查询">
        <c:if test="${!empty errorMessage}">
            <span style="color:red">${errorMessage}</span>
        </c:if>
        <br/>
    </form>

    <div>
        <!-- Button trigger modal -->
        <button id="add" type="button">新增</button>
        <br/><br/>
    </div>
</div>



<table class="transactionTable">
    <thead class="transactionThead">
    <tr>
        <td class="transactionTd">编号</td>
        <td class="transactionTd">日期</td>
        <td class="transactionTd">方式</td>
        <td class="transactionTd">数额</td>
        <td class="transactionTd">款项</td>
        <td class="transactionTd">摘要</td>
        <td class="transactionTd">细节</td>
        <td class="operationTd">操作</td>
    </tr>
    </thead>
    <tbody class="transactionTbody">
    <c:forEach items="${transactions}" var="transaction">
        <tr>
            <td class="transactionTd">${transaction.id}</td>
            <td class="transactionTd">${transaction.date}</td>
            <td class="transactionTd">${transaction.mode}</td>
            <td class="transactionTd">${transaction.amount}</td>
            <td class="transactionTd">${transaction.field}</td>
            <td class="transactionTd">${transaction.introduction}</td>
            <td class="transactionTd"><a href="${pageContext.request.contextPath}/transaction/record/detail/${transaction.id}">查看</a></td>
            <td class="operationTd">
                <a href="${pageContext.request.contextPath}/transaction/record/delete/${transaction.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!--新增-->
<div class="modal" id="modal-add">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">新增交易</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form id="form-add" action="${pageContext.request.contextPath}/transaction/record/add">
                        日期：<input id="date" name="date" type="text" onclick="laydate()"><br/><br/>
                        交易方式：
                        <select id="mode" name="mode" style="height: 30px">
                            <c:forEach items="${transaction_modes}" var="mode">
                                <option value="${mode.id}">${mode.name}</option>
                            </c:forEach>
                        </select><br/><br/>
                        数额：<input id="amount" name="amount" type="text/"><br/><br/>
                        款项：
                        <select id="field" name="field" style="height: 30px">
                            <c:forEach items="${transaction_fields}" var="field">
                                <option value="${field.id}">${field.name}</option>
                            </c:forEach>
                        </select><br/><br/>
                        摘要：<input id="introduction" name="introduction" type="text"><br/><br/>
                        详述：<textarea id="detail" cols="50" rows="4" name="detail"></textarea><br/><br/>
                        私密：
                        <select id="secret" name="secret" style="height: 30px">
                            <option value="Yes">是</option>
                            <option value="No" selected="selected">否</option>
                        </select>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="submit" type="button" class="btn btn-primary">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
<script>
    ;!function(){
        laydate({
            elem: '#date'
        })
    }();
</script>
<script>
    $(function () {

        $("#add").click(function(){
            $("#modal-add").modal("toggle");
        });

        $("#submit").click(function () {
            var date = document.getElementById("date").value;
            var amount = document.getElementById("amount").value;
            var introduction = document.getElementById("introduction").value;
            if(date == ""){
                alert("日期不能为空");
                return false;
            }
            if(amount == ""){
                alert("数额不能为空");
                return false;
            }
            if(introduction == ""){
                alert("摘要不能为空");
                return false;
            }
            document.getElementById("form-add").submit();
        });
    });
</script>
</body>

</html>