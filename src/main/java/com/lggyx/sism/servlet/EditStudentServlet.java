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
 * 编辑学生页面 Servlet - 根据学号查询学生信息并转发到编辑页
 */
@WebServlet(name = "editStudentServlet", value = "/edit")
public class EditStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 检查登录状态
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String studentId = request.getParameter("id");
        if (studentId == null || studentId.trim().isEmpty()) {
            request.setAttribute("errorMsg", "学号参数不能为空");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        StudentDataStore dataStore = StudentDataStore.getInstance();
        Student student = dataStore.getStudentById(studentId);

        if (student == null) {
            request.setAttribute("errorMsg", "未找到学号为 " + studentId + " 的学生");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("student", student);
        request.getRequestDispatcher("/editStudent.jsp").forward(request, response);
    }
}
