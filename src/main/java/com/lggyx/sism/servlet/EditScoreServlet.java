package com.lggyx.sism.servlet;

import com.lggyx.sism.dao.ScoreDataStore;
import com.lggyx.sism.dao.StudentDataStore;
import com.lggyx.sism.model.Score;
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
 * 编辑成绩页面 Servlet
 */
@WebServlet(name = "editScoreServlet", value = "/editScore")
public class EditScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            request.setAttribute("errorMsg", "成绩ID不能为空");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "成绩ID格式错误");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        ScoreDataStore scoreStore = ScoreDataStore.getInstance();
        Score score = scoreStore.getScoreById(id);

        if (score == null) {
            request.setAttribute("errorMsg", "未找到该成绩记录");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        StudentDataStore studentStore = StudentDataStore.getInstance();
        List<Student> students = studentStore.getAllStudents();

        request.setAttribute("score", score);
        request.setAttribute("students", students);
        request.getRequestDispatcher("/editScore.jsp").forward(request, response);
    }
}
