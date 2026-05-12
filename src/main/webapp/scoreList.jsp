<%@ page import="com.lggyx.sism.model.Score" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成绩管理 - 学生信息管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script>
        function confirmDeleteScore(id, studentName, courseName) {
            if (confirm('确定要删除学生 "' + studentName + '" 的 ' + courseName + ' 成绩吗？')) {
                window.location.href = '${pageContext.request.contextPath}/deleteScore?id=' + id;
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
                <a href="${pageContext.request.contextPath}/changePassword" class="btn btn-warning">修改密码</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">退出登录</a>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="table-container">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px;">
                <h2 style="margin: 0; text-align: left;">成绩列表</h2>
                <div style="display: flex; gap: 10px;">
                    <a href="${pageContext.request.contextPath}/index" class="btn btn-secondary">返回学生列表</a>
                    <a href="${pageContext.request.contextPath}/addScore" class="btn btn-success">+ 添加成绩</a>
                </div>
            </div>
            
            <% 
                List<Score> scoreList = (List<Score>) request.getAttribute("scoreList");
                if (scoreList == null || scoreList.isEmpty()) {
            %>
            <div class="empty-msg">暂无成绩信息，请点击"添加成绩"按钮录入数据。</div>
            <% } else { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>课程名称</th>
                        <th>成绩</th>
                        <th style="width: 160px;">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Score s : scoreList) { %>
                    <tr>
                        <td><%= s.getStudentId() %></td>
                        <td><%= s.getStudentName() != null ? s.getStudentName() : "-" %></td>
                        <td><%= s.getCourseName() %></td>
                        <td><%= String.format("%.1f", s.getScore()) %></td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/editScore?id=<%= s.getId() %>" class="btn btn-warning">修改</a>
                            <button onclick="confirmDeleteScore('<%= s.getId() %>', '<%= s.getStudentName() != null ? s.getStudentName() : s.getStudentId() %>', '<%= s.getCourseName() %>')" class="btn btn-danger">删除</button>
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
