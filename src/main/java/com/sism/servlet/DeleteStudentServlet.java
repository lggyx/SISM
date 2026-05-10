package com.sism.servlet;

import com.sism.dao.StudentDAO;
import com.sism.util.ValidationUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除学生 Servlet — 由 ConfirmDialog 二次确认后触发
 */
@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        if (req.getSession(false) == null || req.getSession(false).getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String id = req.getParameter("id");
        if (!ValidationUtils.isValidId(id)) {
            req.setAttribute("errorMessage", "非法学号");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        try {
            StudentDAO dao = new StudentDAO();
            dao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/IndexServlet");
        } catch (Exception e) {
            req.setAttribute("errorMessage", "删除失败：" + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}