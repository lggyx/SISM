package com.sism.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录 Servlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        // 移除 admin 属性 + 销毁 session
        if (req.getSession(false) != null) {
            req.getSession().removeAttribute("admin");
            req.getSession().invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}