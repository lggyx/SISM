<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.sism.model.Student" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/taglibs/standard-1.2.5/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生信息管理系统</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="main-container">
    <header class="top-bar">
        <h1>学生信息管理系统</h1>
        <span class="welcome">欢迎管理员</span>
    </header>

    <div class="toolbar">
        <form action="IndexServlet" method="get" class="search-form">
            <input type="text" name="keyword" class="search-input" placeholder="按学号或姓名模糊搜索...">
            <button type="submit" class="btn btn-secondary">搜索</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='IndexServlet'">重置</button>
        </form>
        <div class="toolbar-btns">
            <a href="addStudent.jsp" class="btn btn-primary">+ 添加学生</a>
            <form action="LogoutServlet" method="post" style="display:inline">
                <button type="submit" class="btn btn-danger">退出登录</button>
            </form>
        </div>
    </div>

    <div class="table-wrapper">
        <table class="student-table">
            <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>班级</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty students}">
                    <c:forEach var="s" items="${students}">
                        <tr>
                            <td>${s.id}</td>
                            <td>${s.name}</td>
                            <td>${s.gender}</td>
                            <td>${s.age}</td>
                            <td>${s.className}</td>
                            <td class="action-cell">
                                <a href="EditStudentServlet?id=${s.id}" class="btn btn-sm btn-edit">修改</a>
                                <a href="DeleteStudentServlet?id=${s.id}"
                                   class="btn btn-sm btn-delete"
                                   onclick="return confirmDelete('${s.name}')">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="6" class="empty-row">暂无学生数据</td></tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
    <div class="table-footer">
        <span>共 <strong>${students.size()}</strong> 条记录</span>
    </div>
</div>

<script>
function confirmDelete(name) {
    return confirm('确定要删除学生【' + name + '】的信息吗？');
}
</script>
</body>
</html>