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
import java.util.List;

/**
 * 成绩列表 Servlet
 */
@WebServlet(name = "scoreListServlet", value = "/scoreList")
public class ScoreListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        ScoreDataStore scoreStore = ScoreDataStore.getInstance();
        List<Score> scoreList = scoreStore.getAllScores();

        request.setAttribute("scoreList", scoreList);
        request.getRequestDispatcher("/scoreList.jsp").forward(request, response);
    }
}
