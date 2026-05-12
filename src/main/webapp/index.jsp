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
                <a href="${pageContext.request.contextPath}/scoreList" class="btn btn-success">成绩管理</a>
                <a href="${pageContext.request.contextPath}/changePassword" class="btn btn-warning">修改密码</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">退出登录</a>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="table-container">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px; flex-wrap: wrap; gap: 10px;">
                <h2 style="margin: 0; text-align: left;">学生列表</h2>
                <div style="display: flex; gap: 10px; align-items: center;">
                    <!-- 搜索表单 -->
                    <form action="${pageContext.request.contextPath}/search" method="get" style="display: flex; gap: 5px;">
                        <input type="text" name="keyword" placeholder="学号或姓名查询" 
                               value="<%= request.getAttribute("keyword") != null ? request.getAttribute("keyword") : "" %>"
                               style="padding: 8px 12px; border: 1px solid #ddd; border-radius: 4px; width: 180px;">
                        <button type="submit" class="btn btn-primary" style="padding: 8px 16px;">查询</button>
                    </form>
                    <a href="${pageContext.request.contextPath}/add" class="btn btn-success">+ 添加学生</a>
                </div>
            </div>
            
            <% Boolean isSearch = (Boolean) request.getAttribute("isSearchResult"); %>
            <% if (Boolean.TRUE.equals(isSearch)) { %>
            <div style="margin-bottom: 15px;">
                <span style="color: #666;">查询结果：关键字 "<%= request.getAttribute("keyword") %>"，共 <%= request.getAttribute("totalCount") %> 条</span>
                <a href="${pageContext.request.contextPath}/index" class="btn btn-secondary" style="margin-left: 10px; padding: 4px 12px; font-size: 12px;">返回全部列表</a>
            </div>
            <% } %>
            
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
            
            <%-- 分页导航（仅非搜索模式下显示） --%>
            <% if (!Boolean.TRUE.equals(isSearch)) { %>
            <% 
                int currentPage = (Integer) request.getAttribute("currentPage");
                int totalPages = (Integer) request.getAttribute("totalPages");
                int totalCount = (Integer) request.getAttribute("totalCount");
                int pageSize = (Integer) request.getAttribute("pageSize");
            %>
            <div class="pagination">
                <span class="page-info">共 <%= totalCount %> 条，第 <%= currentPage %> / <%= totalPages %> 页</span>
                <div class="page-buttons">
                    <% if (currentPage > 1) { %>
                        <a href="${pageContext.request.contextPath}/index?page=<%= currentPage - 1 %>" class="btn btn-secondary">上一页</a>
                    <% } else { %>
                        <button class="btn btn-secondary" disabled>上一页</button>
                    <% } %>
                    
                    <% for (int i = 1; i <= totalPages; i++) { %>
                        <% if (i == currentPage) { %>
                            <span class="page-current"><%= i %></span>
                        <% } else { %>
                            <a href="${pageContext.request.contextPath}/index?page=<%= i %>" class="page-link"><%= i %></a>
                        <% } %>
                    <% } %>
                    
                    <% if (currentPage < totalPages) { %>
                        <a href="${pageContext.request.contextPath}/index?page=<%= currentPage + 1 %>" class="btn btn-secondary">下一页</a>
                    <% } else { %>
                        <button class="btn btn-secondary" disabled>下一页</button>
                    <% } %>
                </div>
            </div>
            <% } %>
            <% } %>
        </div>
    </div>
</body>
</html>
