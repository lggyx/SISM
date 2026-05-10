<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>系统错误</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="main-container">
    <div class="error-box">
        <div class="error-icon">⚠️</div>
        <h1>出错了</h1>
        <p>抱歉，系统在处理您的请求时发生了错误。</p>
        <div class="error-detail">
            <strong>错误信息：</strong>
            <%
                // 从 request 获取错误信息，否则显示内部异常信息
                String msg = (String) request.getAttribute("errorMessage");
                if (msg == null || msg.isEmpty()) {
                    Throwable ex = exception;
                    if (ex == null) ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
                    if (ex != null) msg = ex.getMessage();
                }
                if (msg == null || msg.isEmpty()) { msg = "未知错误，请联系管理员。"; }
                out.print(msg);
            %>
        </div>
        <%
            // Stack trace in dev mode
            String showTrace = System.getProperty("sism.dev", "false");
            if ("true".equals(showTrace)) {
                out.println("<pre class='stacktrace'>");
                exception.printStackTrace(new java.io.PrintWriter(out));
                out.println("</pre>");
            }
        %>
        <a href="IndexServlet" class="btn btn-primary">返回首页</a>
    </div>
</div>
</body>
</html>