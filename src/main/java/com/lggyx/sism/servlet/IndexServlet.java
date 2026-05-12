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
import java.util.List;

/**
 * 首页 Servlet - 查询所有学生并转发到列表页
 */
@WebServlet(name = "indexServlet", value = "/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 检查登录状态
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 查询所有学生数据
        StudentDataStore dataStore = StudentDataStore.getInstance();
        List<Student> studentList = dataStore.getAllStudents();

        // 存入 request 域
        request.setAttribute("studentList", studentList);

        // 转发到首页
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
