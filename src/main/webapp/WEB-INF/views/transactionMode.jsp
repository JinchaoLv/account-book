<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="errorMessage" value="${errorMsg}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>交易方式</title>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <style type="text/css">
        .transactionTable {
            border-collapse: collapse;
        }

        .transactionTable, .transactionThead, .transactionTbody, .transactionTd {
            border: 1px solid black;
        }

        .transactionTd {
            width: 200px;
            text-align: center
        }
    </style>
</head>

<body style="font-size: 16px">

<div>
    <a href="${pageContext.request.contextPath}/transaction/record">交易记录</a>
    <span style="font-size: 20px; font-weight: bold">交易方式</span>
    <a href="${pageContext.request.contextPath}/transaction/field">交易类型</a>
    <a href="${pageContext.request.contextPath}/transaction/analysis">交易分析</a>
    <a href="${pageContext.request.contextPath}/user/logout">退出登录(${username})</a>
</div>
<br/>

<div>
    <!-- Button trigger modal -->
    <button id="add" type="button">新增</button>
    <c:if test="${!empty errorMessage}">
        <div style="color:red">${errorMessage}</div>
    </c:if>
    <br/>
</div>
<br/>

<table class="transactionTable">
    <thead class="transactionThead">
    <tr>
        <td class="transactionTd">编号</td>
        <td class="transactionTd">名称</td>
        <td class="transactionTd">操作</td>
    </tr>
    </thead>
    <tbody class="transactionTbody">
    <c:forEach items="${transaction_modes}" var="mode">
        <tr>
            <td class="transactionTd">${mode.id}</td>
            <td class="transactionTd">${mode.name}</td>
            <td class="transactionTd">
                <a href="${pageContext.request.contextPath}/transaction/mode/delete/${mode.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<div class="modal" id="mymodal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">新增交易方式</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form id="form-add" action="${pageContext.request.contextPath}/transaction/mode/add">
                        交易方式：<input id="mode" type="text" name="mode"/>
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
    $(function(){
        $("#add").click(function(){
            $("#mymodal").modal("toggle");
        });
    });
</script>
<script>
    $(function () {
        $("#submit").click(function () {
            var modeName = document.getElementById("mode").valueOf();
            if(modeName == ""){
                alert("交易方式不能为空");
                return false;
            }
            document.getElementById("form-add").submit();
        });
    });
</script>

</body>

</html>