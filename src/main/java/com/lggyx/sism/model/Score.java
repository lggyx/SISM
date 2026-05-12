package com.lggyx.sism.model;

import java.io.Serializable;

/**
 * 成绩实体类
 */
public class Score implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;             // 成绩ID
    private String studentId;   // 学号
    private String courseName;  // 课程名称
    private double score;       // 分数

    // 关联的学生姓名（查询时填充）
    private String studentName;

    public Score() {
    }

    public Score(int id, String studentId, String courseName, double score) {
        this.id = id;
        this.studentId = studentId;
        this.courseName = courseName;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", score=" + score +
                '}';
    }
}
