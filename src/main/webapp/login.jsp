<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生信息管理系统 — 登录</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login-container">
    <div class="login-card">
        <h1 class="login-title">学生信息管理系统</h1>
        <p class="login-subtitle">SISM · Student Information Management System</p>
        <form action="LoginServlet" method="post" class="login-form" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" id="username" name="username" placeholder="请输入用户名" required>
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" id="password" name="password" placeholder="请输入密码" required>
            </div>
            <%-- Error message from servlet --%>
            <%
                String err = (String) request.getAttribute("error");
                if (err != null && !err.isEmpty()) {
            %>
            <div class="alert alert-error"><%= err %></div>
            <% } %>
            <%-- Validation message --%>
            <div id="valError" class="alert alert-error" style="display:none;"></div>
            <button type="submit" class="btn btn-primary btn-block">登 录</button>
        </form>
        <p class="login-hint">管理员账号：admin / 123456</p>
    </div>
</div>
<script>
function validateForm() {
    var u = document.getElementById('username').value.trim();
    var p = document.getElementById('password').value.trim();
    var err = document.getElementById('valError');
    if (!u || !p) {
        err.textContent = '用户名和密码不能为空';
        err.style.display = 'block';
        return false;
    }
    err.style.display = 'none';
    return true;
}
</script>
</body>
</html>