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
 * 首页 Servlet - 分页查询学生并转发到列表页
 */
@WebServlet(name = "indexServlet", value = "/index")
public class IndexServlet extends HttpServlet {

    private static final int PAGE_SIZE = 5; // 每页显示5条

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 获取当前页码
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                page = Integer.parseInt(pageStr);
                if (page < 1) page = 1;
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        StudentDataStore dataStore = StudentDataStore.getInstance();
        int totalCount = dataStore.getStudentCount();
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);
        if (totalPages == 0) totalPages = 1;
        if (page > totalPages) page = totalPages;

        List<Student> studentList = dataStore.getStudentsByPage(page, PAGE_SIZE);

        request.setAttribute("studentList", studentList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("pageSize", PAGE_SIZE);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
