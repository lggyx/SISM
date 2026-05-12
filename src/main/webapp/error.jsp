<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误 - 学生信息管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="error-box">
        <h2>⚠ 操作失败</h2>
        <p>
            <%= request.getAttribute("errorMsg") != null 
                ? request.getAttribute("errorMsg") 
                : "系统发生未知错误，请稍后重试。" %>
        </p>
        <a href="${pageContext.request.contextPath}/index" class="btn btn-primary">返回首页</a>
        <a href="${pageContext.request.contextPath}/login" class="btn btn-secondary">返回登录</a>
    </div>
</body>
</html>
