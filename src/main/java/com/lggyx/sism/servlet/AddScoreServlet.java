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
 * 添加成绩 Servlet
 */
@WebServlet(name = "addScoreServlet", value = "/addScore")
public class AddScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        StudentDataStore studentStore = StudentDataStore.getInstance();
        List<Student> students = studentStore.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/addScore.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String studentId = request.getParameter("studentId");
        String courseName = request.getParameter("courseName");
        String scoreStr = request.getParameter("score");

        // 验证
        String errorMsg = validateScore(studentId, courseName, scoreStr);
        if (errorMsg != null) {
            request.setAttribute("errorMsg", errorMsg);
            request.setAttribute("studentId", studentId);
            request.setAttribute("courseName", courseName);
            request.setAttribute("score", scoreStr);
            doGet(request, response);
            return;
        }

        double scoreValue = Double.parseDouble(scoreStr);
        Score score = new Score();
        score.setStudentId(studentId);
        score.setCourseName(courseName);
        score.setScore(scoreValue);

        ScoreDataStore scoreStore = ScoreDataStore.getInstance();
        if (scoreStore.addScore(score)) {
            response.sendRedirect(request.getContextPath() + "/scoreList");
        } else {
            request.setAttribute("errorMsg", "添加成绩失败");
            request.setAttribute("studentId", studentId);
            request.setAttribute("courseName", courseName);
            request.setAttribute("score", scoreStr);
            doGet(request, response);
        }
    }

    public static String validateScore(String studentId, String courseName, String scoreStr) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return "请选择学生";
        }
        if (courseName == null || courseName.trim().isEmpty()) {
            return "课程名称不能为空";
        }
        if (scoreStr == null || scoreStr.trim().isEmpty()) {
            return "成绩不能为空";
        }
        try {
            double score = Double.parseDouble(scoreStr);
            if (score < 0 || score > 100) {
                return "成绩必须在0-100之间";
            }
        } catch (NumberFormatException e) {
            return "成绩必须为数字";
        }
        return null;
    }
}
