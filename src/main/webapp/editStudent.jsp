<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sism.model.Student" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>修改学生信息</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="main-container">
    <header class="top-bar">
        <h1>修改学生信息</h1>
    </header>
    <%
        Student s = (Student) request.getAttribute("student");
        if (s == null) {
    %>
    <div class="alert alert-error">未找到学生信息</div>
    <a href="IndexServlet" class="btn btn-secondary">返回列表</a>
    <%  } else { %>
    <form action="UpdateStudentServlet" method="post" class="form-box" onsubmit="return validateForm()">
        <div class="form-row">
            <label>学号 <span class="req">*</span>（不可修改）</label>
            <input type="text" name="id" value="${student.id}" readonly class="readonly-input">
        </div>
        <div class="form-row">
            <label>姓名 <span class="req">*</span></label>
            <input type="text" name="name" value="${student.name}" placeholder="请输入姓名" required>
        </div>
        <div class="form-row">
            <label>性别</label>
            <div class="radio-group">
                <label class="radio-label"><input type="radio" name="gender" value="男"
                    ${student.gender == '男' ? 'checked' : ''}> 男</label>
                <label class="radio-label"><input type="radio" name="gender" value="女"
                    ${student.gender == '女' ? 'checked' : ''}> 女</label>
            </div>
        </div>
        <div class="form-row">
            <label>年龄 <span class="req">*</span></label>
            <input type="number" name="age" value="${student.age}" min="1" max="120" required>
        </div>
        <div class="form-row">
            <label>班级 <span class="req">*</span></label>
            <select name="className" required>
                <option value="">-- 请选择班级 --</option>
                <option ${student.className == '计算机1班' ? 'selected' : ''}>计算机1班</option>
                <option ${student.className == '计算机2班' ? 'selected' : ''}>计算机2班</option>
                <option ${student.className == '软件工程班' ? 'selected' : ''}>软件工程班</option>
            </select>
        </div>
        <%
            String err = (String) request.getAttribute("error");
            if (err != null && !err.isEmpty()) {
        %>
        <div class="alert alert-error">${error}</div>
        <% } %>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">保 存</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='IndexServlet'">取 消</button>
        </div>
    </form>
    <% } %>
</div>
</body>
</html>