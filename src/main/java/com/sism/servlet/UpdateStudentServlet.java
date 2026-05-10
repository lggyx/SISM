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
 * 更新学生信息 Servlet
 */
@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        if (req.getSession(false) == null || req.getSession(false).getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String ageStr = req.getParameter("age");
        String className = req.getParameter("className");

        // 服务端验证
        StringBuilder err = new StringBuilder();
        if (!ValidationUtils.isValidId(id)) err.append("非法学号；");
        if (!ValidationUtils.isNonEmpty(name)) err.append("姓名不能为空；");
        if (!ValidationUtils.isValidAge(ageStr)) err.append("年龄必须为正整数；");
        if (!ValidationUtils.isNonEmpty(className)) err.append("请选择班级；");

        if (err.length() > 0) {
            req.setAttribute("error", err.toString());
            // 回填表单
            Student s = new Student();
            s.setId(id); s.setName(name);
            s.setGender(gender); s.setClassName(className);
            try { s.setAge(Integer.parseInt(ageStr)); } catch(NumberFormatException ignored) {}
            req.setAttribute("student", s);
            req.getRequestDispatcher("/editStudent.jsp").forward(req, resp);
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            StudentDAO dao = new StudentDAO();
            boolean ok = dao.update(new Student(id, name, gender, age, className));
            if (!ok) {
                req.setAttribute("error", "学号 " + id + " 不存在，无法更新");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/IndexServlet");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "年龄必须是整数");
            req.getRequestDispatcher("/editStudent.jsp").forward(req, resp);
        }
    }
}