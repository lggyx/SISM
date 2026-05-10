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
 * 添加学生 Servlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        if (req.getSession(false) == null || req.getSession(false).getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // 客户端验证 + 服务端双重验证
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String ageStr = req.getParameter("age");
        String className = req.getParameter("className");

        // 服务端验证
        StringBuilder err = new StringBuilder();
        if (!ValidationUtils.isValidId(id)) err.append("学号必须为6位数字；");
        if (!ValidationUtils.isNonEmpty(name)) err.append("姓名不能为空；");
        if (!ValidationUtils.isValidAge(ageStr)) err.append("年龄必须为正整数；");
        if (!ValidationUtils.isNonEmpty(className)) err.append("请选择班级；");

        if (err.length() > 0) {
            req.setAttribute("error", err.toString());
            req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
            return;
        }

        int age = Integer.parseInt(ageStr);
        StudentDAO dao = new StudentDAO();

        // 唯一性校验
        if (!dao.add(new Student(id, name, gender, age, className))) {
            req.setAttribute("error", "学号 " + id + " 已存在，请更换");
            req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
            return;
        }

        // 添加成功 → redirect 防重复提交
        resp.sendRedirect(req.getContextPath() + "/IndexServlet");
    }
}