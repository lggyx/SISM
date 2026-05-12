package com.lggyx.sism.dao;

import com.lggyx.sism.util.DBUtil;

import java.sql.*;

/**
 * 管理员数据存储类（JDBC 数据库实现）
 */
public class AdminDataStore {

    private static AdminDataStore instance;

    private AdminDataStore() {
    }

    public static synchronized AdminDataStore getInstance() {
        if (instance == null) {
            instance = new AdminDataStore();
        }
        return instance;
    }

    /**
     * 验证管理员登录
     */
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT 1 FROM admin WHERE username = ? AND password = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return false;
    }

    /**
     * 修改密码
     */
    public boolean changePassword(String username, String newPassword) {
        String sql = "UPDATE admin SET password = ? WHERE username = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return false;
    }
}
