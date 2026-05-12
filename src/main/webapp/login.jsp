<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录 - 学生信息管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="login-box">
        <h2>学生信息管理系统</h2>
        
        <% if (request.getAttribute("errorMsg") != null) { %>
        <div class="error-msg">
            <%= request.getAttribute("errorMsg") %>
        </div>
        <% } %>
        
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" id="username" name="username" placeholder="请输入用户名" required>
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" id="password" name="password" placeholder="请输入密码" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" style="width: 100%;">登 录</button>
            </div>
        </form>
    </div>
</body>
</html>
