<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加学生 - 学生信息管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <h1>学生信息管理系统</h1>
            <div class="user-info">
                <span>欢迎管理员</span>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">退出登录</a>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="login-box" style="margin-top: 30px; max-width: 500px;">
            <h2>添加学生</h2>
            
            <% if (request.getAttribute("errorMsg") != null) { %>
            <div class="error-msg">
                <%= request.getAttribute("errorMsg") %>
            </div>
            <% } %>
            
            <form action="${pageContext.request.contextPath}/add" method="post">
                <div class="form-group">
                    <label for="studentId">学号</label>
                    <input type="text" id="studentId" name="studentId" placeholder="请输入6位数字学号"
                           value="<%= request.getAttribute("studentId") != null ? request.getAttribute("studentId") : "" %>"
                           maxlength="6" required>
                </div>
                <div class="form-group">
                    <label for="name">姓名</label>
                    <input type="text" id="name" name="name" placeholder="请输入姓名"
                           value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>"
                           required>
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <div class="radio-group">
                        <label><input type="radio" name="gender" value="男" 
                            <%= "男".equals(request.getAttribute("gender")) ? "checked" : "" %> required> 男</label>
                        <label><input type="radio" name="gender" value="女" 
                            <%= "女".equals(request.getAttribute("gender")) ? "checked" : "" %> required> 女</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="age">年龄</label>
                    <input type="number" id="age" name="age" placeholder="请输入年龄"
                           value="<%= request.getAttribute("age") != null ? request.getAttribute("age") : "" %>"
                           min="1" required>
                </div>
                <div class="form-group">
                    <label for="clazz">班级</label>
                    <select id="clazz" name="clazz" required>
                        <option value="">-- 请选择班级 --</option>
                        <option value="计算机1班" <%= "计算机1班".equals(request.getAttribute("clazz")) ? "selected" : "" %>>计算机1班</option>
                        <option value="计算机2班" <%= "计算机2班".equals(request.getAttribute("clazz")) ? "selected" : "" %>>计算机2班</option>
                        <option value="软件工程班" <%= "软件工程班".equals(request.getAttribute("clazz")) ? "selected" : "" %>>软件工程班</option>
                    </select>
                </div>
                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">提 交</button>
                    <a href="${pageContext.request.contextPath}/index" class="btn btn-secondary">取 消</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
