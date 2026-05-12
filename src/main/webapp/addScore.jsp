<%@ page import="com.lggyx.sism.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加成绩 - 学生信息管理系统</title>
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
            <h2>添加成绩</h2>
            
            <% if (request.getAttribute("errorMsg") != null) { %>
            <div class="error-msg">
                <%= request.getAttribute("errorMsg") %>
            </div>
            <% } %>
            
            <form action="${pageContext.request.contextPath}/addScore" method="post">
                <div class="form-group">
                    <label for="studentId">学生</label>
                    <select id="studentId" name="studentId" required>
                        <option value="">-- 请选择学生 --</option>
                        <% 
                            List<Student> students = (List<Student>) request.getAttribute("students");
                            String selectedId = (String) request.getAttribute("studentId");
                            if (students != null) {
                                for (Student s : students) {
                        %>
                        <option value="<%= s.getStudentId() %>" <%= s.getStudentId().equals(selectedId) ? "selected" : "" %>>
                            <%= s.getStudentId() %> - <%= s.getName() %>（<%= s.getClazz() %>）
                        </option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="courseName">课程名称</label>
                    <input type="text" id="courseName" name="courseName" placeholder="请输入课程名称"
                           value="<%= request.getAttribute("courseName") != null ? request.getAttribute("courseName") : "" %>"
                           required>
                </div>
                <div class="form-group">
                    <label for="score">成绩</label>
                    <input type="number" id="score" name="score" placeholder="请输入成绩（0-100）"
                           value="<%= request.getAttribute("score") != null ? request.getAttribute("score") : "" %>"
                           min="0" max="100" step="0.1" required>
                </div>
                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">提 交</button>
                    <a href="${pageContext.request.contextPath}/scoreList" class="btn btn-secondary">取 消</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
