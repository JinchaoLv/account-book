<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="errorMessage" value="${errorMsg}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>交易详细查询</title>
    <script src="<c:url value="/resources/laydate/laydate.js"/>" type="text/javascript"></script>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <style type="text/css">
        td {
            width: 200px;
            text-align: center
        }
    </style>
</head>

<body>

<div><span style="font-size: 20px; font-weight: bold">交易细节</span>（${transaction.id}）</div><br/><br/>
<c:if test="${!empty errorMessage}">
    <span style="color:red">${errorMessage}</span>
</c:if>

<!--主体部分-->
<div>
    <form id="form" action="${pageContext.request.contextPath}/transaction/record/update">
        <input name="id" type="hidden" value="${transaction.id}">
        日期：<input id="date" name="date" type="text" value="${transaction.date}" onclick="laydate()"><br/><br/>
        交易方式：
        <select id="mode" name="mode" style="height: 30px">
            <c:forEach items="${transaction_modes}" var="mode">
                <c:if test="${mode.name == transaction.mode}">
                    <option value="${mode.id}" selected="selected">${mode.name}</option>
                </c:if>
                <c:if test="${mode.name != transaction.mode}">
                    <option value="${mode.id}">${mode.name}</option>
                </c:if>
            </c:forEach>
        </select><br/><br/>
        数额：<input id="amount" name="amount" type="text" value="${transaction.amount}"><br/><br/>
        款项：
        <select id="field" name="field" style="height: 30px">
            <c:forEach items="${transaction_fields}" var="field">
                <c:if test="${field.name == transaction.field}">
                    <option value="${field.id}" selected="selected">${field.name}</option>
                </c:if>
                <c:if test="${field.name != transaction.field}">
                    <option value="${field.id}">${field.name}</option>
                </c:if>
            </c:forEach>
        </select><br/><br/>
        摘要：<input id="introduction" name="introduction" type="text" value="${transaction.introduction}"><br/><br/>
        详述：<textarea id="detail" cols="50" rows="4" name="detail">${detail}</textarea>
        <br/><br/>
        私密：
        <select id="secret" name="secret" style="height: 30px">
            <c:if test="${transaction.secret == 'Yes'}">
                <option value="Yes" selected="selected">是</option>
                <option value="No">否</option>
            </c:if>
            <c:if test="${transaction.secret == 'No'}">
                <option value="Yes">是</option>
                <option value="No" selected="selected">否</option>
            </c:if>
        </select>
        <input id="password" name="password" type="hidden"/>
    </form>
    <button id="btn-update" type="button">修改</button>
</div>


<!--密码模态框-->
<div class="modal" id="modal-password">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div>
                    请输入密码：<input id="input-password" name="password" type="password"/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="modal-submit" type="button" class="btn btn-primary">确定</button>
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

        $("#btn-update").click(function () {
            var v = ${transaction.secret == "Yes"};
            var date = document.getElementById("date").value;
            var amount = document.getElementById("amount").value;
            var introduction = document.getElementById("introduction").value;
            var secret = document.getElementById("secret").value;
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
            if(v && secret == "No"){
                $("#modal-password").modal("toggle");
            } else{
                document.getElementById("form").submit();
            }

        });

        $("#modal-submit").click(function () {
            var password = document.getElementById("input-password").value;
            document.getElementById("password").value = password;
            document.getElementById("form").submit();
        });
    });
</script>
</body>