package com.lggyx.sism.util;

import java.sql.*;

/**
 * 数据库连接工具类
 */
public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/sism?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL 驱动加载失败", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    /**
     * 初始化数据库表结构
     */
    public static void initDatabase() {
        String createStudentTable = "CREATE TABLE IF NOT EXISTS student (" +
                "student_id VARCHAR(6) PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "gender VARCHAR(4) NOT NULL," +
                "age INT NOT NULL," +
                "clazz VARCHAR(50) NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";

        String createAdminTable = "CREATE TABLE IF NOT EXISTS admin (" +
                "username VARCHAR(50) PRIMARY KEY," +
                "password VARCHAR(50) NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";

        String createScoreTable = "CREATE TABLE IF NOT EXISTS score (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "student_id VARCHAR(6) NOT NULL," +
                "course_name VARCHAR(50) NOT NULL," +
                "score DECIMAL(5,2) NOT NULL," +
                "FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";

        String insertDefaultAdmin = "INSERT IGNORE INTO admin (username, password) VALUES ('admin', '123456')";

        String insertSampleStudents = "INSERT IGNORE INTO student (student_id, name, gender, age, clazz) VALUES " +
                "('202301', '张三', '男', 20, '计算机1班')," +
                "('202302', '李四', '女', 19, '计算机2班')," +
                "('202303', '王五', '男', 21, '软件工程班')," +
                "('202304', '赵六', '女', 20, '计算机1班')," +
                "('202305', '孙七', '男', 22, '计算机2班')," +
                "('202306', '周八', '女', 19, '软件工程班')," +
                "('202307', '吴九', '男', 21, '计算机1班')," +
                "('202308', '郑十', '女', 20, '计算机2班')," +
                "('202309', '钱十一', '男', 22, '软件工程班')," +
                "('202310', '孔十二', '女', 19, '计算机1班')," +
                "('202311', '孟十三', '男', 20, '计算机2班')," +
                "('202312', '季十四', '女', 21, '软件工程班')";

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.execute(createStudentTable);
            stmt.execute(createAdminTable);
            stmt.execute(createScoreTable);
            stmt.execute(insertDefaultAdmin);
            stmt.execute(insertSampleStudents);
        } catch (SQLException e) {
            throw new RuntimeException("数据库初始化失败", e);
        } finally {
            close(conn, stmt);
        }
    }
}
