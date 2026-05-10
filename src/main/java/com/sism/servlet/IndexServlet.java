package com.sism.servlet;

import com.sism.dao.StudentDAO;
import com.sism.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 首页 Servlet — 查询所有学生并展示
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        // 登录校验
        if (req.getSession(false) == null || req.getSession(false).getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            StudentDAO dao = new StudentDAO();
            String keyword = req.getParameter("keyword"); // 可选：模糊搜索

            List<com.sism.model.Student> students;
            if (keyword != null && !keyword.trim().isEmpty()) {
                students = dao.findByKeyword(keyword.trim());
            } else {
                students = dao.findAll();
            }

            req.setAttribute("students", students);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            handleError(req, resp, "加载学生列表失败：" + e.getMessage());
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String msg)
            throws ServletException, IOException {
        String dev = System.getProperty("sism.dev", "false");
        req.setAttribute("errorMessage", msg);
        if ("true".equals(dev)) {
            req.setAttribute("javax.servlet.error.exception", new RuntimeException(msg));
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}