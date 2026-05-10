package com.sism.servlet;

import com.sism.dao.StudentDAO;
import com.sism.model.Student;
import com.sism.util.ValidationUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编辑学生 - GET：根据学号查询学生信息，forward editStudent.jsp
 */
@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {
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
            req.setAttribute("error", "非法学号");
            forwardError(req, resp);
            return;
        }

        try {
            Student student = new StudentDAO().findById(id).orElse(null);
            if (student == null) {
                req.setAttribute("error", "学号 " + id + " 不存在");
                forwardError(req, resp);
                return;
            }
            req.setAttribute("student", student);
            req.getRequestDispatcher("/editStudent.jsp").forward(req, resp);
        } catch (Exception e) {
            handleError(req, resp, "查询学生信息失败：" + e.getMessage());
        }
    }

    private void forwardError(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String msg)
            throws ServletException, IOException {
        req.setAttribute("errorMessage", msg);
        req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }
}