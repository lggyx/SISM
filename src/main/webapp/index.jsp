<%@ page import="com.lggyx.sism.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页 - 学生信息管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script>
        function confirmDelete(studentId, studentName) {
            if (confirm('确定要删除学生 "' + studentName + '"（学号：' + studentId + '）吗？')) {
                window.location.href = '${pageContext.request.contextPath}/delete?id=' + studentId;
            }
        }
    </script>
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
        <div class="table-container">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px;">
                <h2 style="margin: 0; text-align: left;">学生列表</h2>
                <a href="${pageContext.request.contextPath}/add" class="btn btn-success">+ 添加学生</a>
            </div>
            
            <% 
                List<Student> studentList = (List<Student>) request.getAttribute("studentList");
                if (studentList == null || studentList.isEmpty()) {
            %>
            <div class="empty-msg">暂无学生信息，请点击"添加学生"按钮录入数据。</div>
            <% } else { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>班级</th>
                        <th style="width: 160px;">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Student s : studentList) { %>
                    <tr>
                        <td><%= s.getStudentId() %></td>
                        <td><%= s.getName() %></td>
                        <td><%= s.getGender() %></td>
                        <td><%= s.getAge() %></td>
                        <td><%= s.getClazz() %></td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/edit?id=<%= s.getStudentId() %>" class="btn btn-warning">修改</a>
                            <button onclick="confirmDelete('<%= s.getStudentId() %>', '<%= s.getName() %>')" class="btn btn-danger">删除</button>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>
        </div>
    </div>
</body>
</html>
