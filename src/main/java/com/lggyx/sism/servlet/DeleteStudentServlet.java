package com.lggyx.sism.servlet;

import com.lggyx.sism.dao.StudentDataStore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 删除学生 Servlet
 */
@WebServlet(name = "deleteStudentServlet", value = "/delete")
public class DeleteStudentServlet extends HttpServlet {

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
        if (!dataStore.deleteStudent(studentId)) {
            request.setAttribute("errorMsg", "删除失败，学生不存在");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // 删除成功，重定向到首页
        response.sendRedirect(request.getContextPath() + "/index");
    }
}
