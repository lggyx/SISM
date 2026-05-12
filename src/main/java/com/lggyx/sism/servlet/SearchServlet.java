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
 * 学生查询 Servlet - 根据学号或姓名模糊查询
 */
@WebServlet(name = "searchServlet", value = "/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");

        if (keyword == null || keyword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/index");
            return;
        }

        StudentDataStore dataStore = StudentDataStore.getInstance();
        List<Student> studentList = dataStore.searchStudents(keyword.trim());

        request.setAttribute("studentList", studentList);
        request.setAttribute("keyword", keyword.trim());
        request.setAttribute("isSearchResult", true);
        // 查询结果不分页，直接显示
        request.setAttribute("currentPage", 1);
        request.setAttribute("totalPages", 1);
        request.setAttribute("totalCount", studentList.size());
        request.setAttribute("pageSize", studentList.size());

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
