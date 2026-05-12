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
 * 添加学生 Servlet
 */
@WebServlet(name = "addStudentServlet", value = "/add")
public class AddStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 检查登录状态
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // GET 请求跳转到添加页面
        request.getRequestDispatcher("/addStudent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 检查登录状态
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String ageStr = request.getParameter("age");
        String clazz = request.getParameter("clazz");

        // 表单验证
        String errorMsg = validateStudent(studentId, name, gender, ageStr, clazz, true);
        if (errorMsg != null) {
            request.setAttribute("errorMsg", errorMsg);
            request.setAttribute("studentId", studentId);
            request.setAttribute("name", name);
            request.setAttribute("gender", gender);
            request.setAttribute("age", ageStr);
            request.setAttribute("clazz", clazz);
            request.getRequestDispatcher("/addStudent.jsp").forward(request, response);
            return;
        }

        int age = Integer.parseInt(ageStr);
        Student student = new Student(studentId, name, gender, age, clazz);

        StudentDataStore dataStore = StudentDataStore.getInstance();
        if (!dataStore.addStudent(student)) {
            request.setAttribute("errorMsg", "学号已存在，请更换学号");
            request.setAttribute("studentId", studentId);
            request.setAttribute("name", name);
            request.setAttribute("gender", gender);
            request.setAttribute("age", ageStr);
            request.setAttribute("clazz", clazz);
            request.getRequestDispatcher("/addStudent.jsp").forward(request, response);
            return;
        }

        // 添加成功，重定向到首页
        response.sendRedirect(request.getContextPath() + "/index");
    }

    /**
     * 验证学生信息
     * @param isAdd true 表示添加操作，需要验证学号唯一性
     * @return 错误信息，验证通过返回 null
     */
    public static String validateStudent(String studentId, String name, String gender,
                                          String ageStr, String clazz, boolean isAdd) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return "学号不能为空";
        }
        if (!studentId.matches("^\\d{6}$")) {
            return "学号必须为6位数字";
        }
        if (isAdd && StudentDataStore.getInstance().isStudentIdExists(studentId)) {
            return "学号已存在，请更换学号";
        }
        if (name == null || name.trim().isEmpty()) {
            return "姓名不能为空";
        }
        if (gender == null || gender.trim().isEmpty()) {
            return "性别不能为空";
        }
        if (ageStr == null || ageStr.trim().isEmpty()) {
            return "年龄不能为空";
        }
        try {
            int age = Integer.parseInt(ageStr);
            if (age <= 0) {
                return "年龄必须为正整数";
            }
        } catch (NumberFormatException e) {
            return "年龄必须为正整数";
        }
        if (clazz == null || clazz.trim().isEmpty()) {
            return "班级不能为空";
        }
        return null;
    }
}
