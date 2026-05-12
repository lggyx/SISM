package com.lggyx.sism.servlet;

import com.lggyx.sism.dao.ScoreDataStore;
import com.lggyx.sism.model.Score;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 更新成绩 Servlet
 */
@WebServlet(name = "updateScoreServlet", value = "/updateScore")
public class UpdateScoreServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        String studentId = request.getParameter("studentId");
        String courseName = request.getParameter("courseName");
        String scoreStr = request.getParameter("score");

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "成绩ID格式错误");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        String errorMsg = AddScoreServlet.validateScore(studentId, courseName, scoreStr);
        if (errorMsg != null) {
            request.setAttribute("errorMsg", errorMsg);
            Score score = new Score(id, studentId, courseName, 0);
            request.setAttribute("score", score);
            new EditScoreServlet().doGet(request, response);
            return;
        }

        double scoreValue = Double.parseDouble(scoreStr);
        Score score = new Score(id, studentId, courseName, scoreValue);

        ScoreDataStore scoreStore = ScoreDataStore.getInstance();
        if (scoreStore.updateScore(score)) {
            response.sendRedirect(request.getContextPath() + "/scoreList");
        } else {
            request.setAttribute("errorMsg", "更新成绩失败");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
