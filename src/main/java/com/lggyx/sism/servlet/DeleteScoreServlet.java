package com.lggyx.sism.servlet;

import com.lggyx.sism.dao.ScoreDataStore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 删除成绩 Servlet
 */
@WebServlet(name = "deleteScoreServlet", value = "/deleteScore")
public class DeleteScoreServlet extends HttpServlet {

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
        if (scoreStore.deleteScore(id)) {
            response.sendRedirect(request.getContextPath() + "/scoreList");
        } else {
            request.setAttribute("errorMsg", "删除成绩失败");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
