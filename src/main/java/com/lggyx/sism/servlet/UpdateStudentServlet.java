package com.lggyx.sism.servlet;

import com.lggyx.sism.dao.StudentDataStore;
import com.lggyx.sism.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 更新学生信息 Servlet
 */
@WebServlet(name = "updateStudentServlet", value = "/update")
public class UpdateStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 检查登录状态
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String ageStr = request.getParameter("age");
        String clazz = request.getParameter("clazz");

        // 表单验证（更新时不验证学号唯一性，因为学号不变）
        String errorMsg = AddStudentServlet.validateStudent(studentId, name, gender, ageStr, clazz, false);
        if (errorMsg != null) {
            request.setAttribute("errorMsg", errorMsg);
            Student student = new Student(studentId, name, gender,
                    ageStr != null && !ageStr.isEmpty() ? Integer.parseInt(ageStr) : 0, clazz);
            request.setAttribute("student", student);
            request.getRequestDispatcher("/editStudent.jsp").forward(request, response);
            return;
        }

        int age = Integer.parseInt(ageStr);
        Student student = new Student(studentId, name, gender, age, clazz);

        StudentDataStore dataStore = StudentDataStore.getInstance();
        if (!dataStore.updateStudent(student)) {
            request.setAttribute("errorMsg", "更新失败，学生不存在");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // 更新成功，重定向到首页
        response.sendRedirect(request.getContextPath() + "/index");
    }
}
