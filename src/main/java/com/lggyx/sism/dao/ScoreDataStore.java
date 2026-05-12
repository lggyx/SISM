package com.lggyx.sism.dao;

import com.lggyx.sism.model.Score;
import com.lggyx.sism.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 成绩数据存储类（JDBC 数据库实现）
 */
public class ScoreDataStore {

    private static ScoreDataStore instance;

    private ScoreDataStore() {
    }

    public static synchronized ScoreDataStore getInstance() {
        if (instance == null) {
            instance = new ScoreDataStore();
        }
        return instance;
    }

    /**
     * 获取所有成绩（含学生姓名）
     */
    public List<Score> getAllScores() {
        List<Score> list = new ArrayList<>();
        String sql = "SELECT s.*, st.name as student_name FROM score s " +
                "LEFT JOIN student st ON s.student_id = st.student_id " +
                "ORDER BY s.student_id, s.course_name";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToScore(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 根据学号查询成绩
     */
    public List<Score> getScoresByStudentId(String studentId) {
        List<Score> list = new ArrayList<>();
        String sql = "SELECT s.*, st.name as student_name FROM score s " +
                "LEFT JOIN student st ON s.student_id = st.student_id " +
                "WHERE s.student_id = ? ORDER BY s.course_name";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToScore(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 根据ID查询成绩
     */
    public Score getScoreById(int id) {
        String sql = "SELECT s.*, st.name as student_name FROM score s " +
                "LEFT JOIN student st ON s.student_id = st.student_id " +
                "WHERE s.id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToScore(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return null;
    }

    /**
     * 添加成绩
     */
    public boolean addScore(Score score) {
        String sql = "INSERT INTO score (student_id, course_name, score) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, score.getStudentId());
            pstmt.setString(2, score.getCourseName());
            pstmt.setDouble(3, score.getScore());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return false;
    }

    /**
     * 更新成绩
     */
    public boolean updateScore(Score score) {
        String sql = "UPDATE score SET student_id = ?, course_name = ?, score = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, score.getStudentId());
            pstmt.setString(2, score.getCourseName());
            pstmt.setDouble(3, score.getScore());
            pstmt.setInt(4, score.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return false;
    }

    /**
     * 删除成绩
     */
    public boolean deleteScore(int id) {
        String sql = "DELETE FROM score WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return false;
    }

    private Score mapResultSetToScore(ResultSet rs) throws SQLException {
        Score score = new Score();
        score.setId(rs.getInt("id"));
        score.setStudentId(rs.getString("student_id"));
        score.setCourseName(rs.getString("course_name"));
        score.setScore(rs.getDouble("score"));
        score.setStudentName(rs.getString("student_name"));
        return score;
    }
}
