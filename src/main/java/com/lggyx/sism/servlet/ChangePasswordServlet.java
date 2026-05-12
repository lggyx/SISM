package com.lggyx.sism.servlet;

import com.lggyx.sism.dao.AdminDataStore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 密码修改 Servlet
 */
@WebServlet(name = "changePasswordServlet", value = "/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String username = (String) session.getAttribute("user");

        // 验证
        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            request.setAttribute("errorMsg", "原密码不能为空");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            request.setAttribute("errorMsg", "新密码不能为空");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }
        if (newPassword.length() < 6) {
            request.setAttribute("errorMsg", "新密码长度不能少于6位");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMsg", "两次输入的新密码不一致");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }

        AdminDataStore adminStore = AdminDataStore.getInstance();
        // 验证原密码
        if (!adminStore.validateLogin(username, oldPassword)) {
            request.setAttribute("errorMsg", "原密码错误");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }

        // 修改密码
        if (adminStore.changePassword(username, newPassword)) {
            request.setAttribute("successMsg", "密码修改成功，请使用新密码重新登录");
            session.invalidate(); // 修改成功后要求重新登录
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMsg", "密码修改失败，请重试");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
        }
    }
}
