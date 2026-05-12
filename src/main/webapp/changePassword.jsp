<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码 - 学生信息管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>学生信息管理系统</h1>
            <div class="user-info">
                <span>欢迎管理员</span>
                <a href="${pageContext.request.contextPath}/index" class="btn btn-secondary">返回首页</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">退出登录</a>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="login-box" style="margin-top: 30px; max-width: 500px;">
            <h2>修改密码</h2>
            
            <% if (request.getAttribute("errorMsg") != null) { %>
            <div class="error-msg">
                <%= request.getAttribute("errorMsg") %>
            </div>
            <% } %>
            
            <% if (request.getAttribute("successMsg") != null) { %>
            <div style="background-color: #d4edda; color: #155724; padding: 10px 15px; border-radius: 4px; margin-bottom: 20px; border-left: 4px solid #28a745;">
                <%= request.getAttribute("successMsg") %>
            </div>
            <% } %>
            
            <form action="${pageContext.request.contextPath}/changePassword" method="post">
                <div class="form-group">
                    <label for="oldPassword">原密码</label>
                    <input type="password" id="oldPassword" name="oldPassword" placeholder="请输入原密码" required>
                </div>
                <div class="form-group">
                    <label for="newPassword">新密码</label>
                    <input type="password" id="newPassword" name="newPassword" placeholder="请输入新密码（至少6位）" required minlength="6">
                </div>
                <div class="form-group">
                    <label for="confirmPassword">确认新密码</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="请再次输入新密码" required minlength="6">
                </div>
                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">确 认 修 改</button>
                    <a href="${pageContext.request.contextPath}/index" class="btn btn-secondary">取 消</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
