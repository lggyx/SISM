<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>添加学生</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="main-container">
    <header class="top-bar">
        <h1>添加学生</h1>
    </header>
    <form action="AddStudentServlet" method="post" class="form-box" onsubmit="return validateForm()">
        <div class="form-row">
            <label>学号 <span class="req">*</span></label>
            <input type="text" id="id" name="id" placeholder="请输入6位数字学号" maxlength="6" pattern="\d{6}" required>
        </div>
        <div class="form-row">
            <label>姓名 <span class="req">*</span></label>
            <input type="text" id="name" name="name" placeholder="请输入姓名" required>
        </div>
        <div class="form-row">
            <label>性别</label>
            <div class="radio-group">
                <label class="radio-label"><input type="radio" name="gender" value="男" checked> 男</label>
                <label class="radio-label"><input type="radio" name="gender" value="女"> 女</label>
            </div>
        </div>
        <div class="form-row">
            <label>年龄 <span class="req">*</span></label>
            <input type="number" id="age" name="age" placeholder="请输入年龄" min="1" max="120" required>
        </div>
        <div class="form-row">
            <label>班级 <span class="req">*</span></label>
            <select id="className" name="className" required>
                <option value="">-- 请选择班级 --</option>
                <option>计算机1班</option>
                <option>计算机2班</option>
                <option>软件工程班</option>
            </select>
        </div>
        <%-- Error from servlet --%>
        <%
            String err = (String) request.getAttribute("error");
            if (err != null && !err.isEmpty()) {
        %>
        <div class="alert alert-error">${error}</div>
        <% } %>
        <div id="valErr" class="alert alert-error" style="display:none;"></div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">提 交</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='IndexServlet'">取 消</button>
        </div>
    </form>
</div>
<script>
function validateForm() {
    var id = document.getElementById('id').value.trim();
    var name = document.getElementById('name').value.trim();
    var age = document.getElementById('age').value.trim();
    var cls = document.getElementById('className').value;
    var err = document.getElementById('valErr');
    if (!/^\d{6}$/.test(id)) {
        err.textContent = '学号必须为 6 位数字';
        err.style.display = 'block'; return false;
    }
    if (!name) { err.textContent = '姓名不能为空'; err.style.display = 'block'; return false; }
    if (!age || isNaN(age) || Number(age) <= 0) {
        err.textContent = '年龄必须为正整数'; err.style.display = 'block'; return false;
    }
    if (!cls) { err.textContent = '请选择班级'; err.style.display = 'block'; return false; }
    err.style.display = 'none'; return true;
}
</script>
</body>
</html>