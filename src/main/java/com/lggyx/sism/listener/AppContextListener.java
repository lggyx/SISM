package com.lggyx.sism.listener;

import com.lggyx.sism.util.DBUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * 应用上下文监听器 - 在应用启动时初始化数据库
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("===== 学生信息管理系统启动中... =====");
        try {
            DBUtil.initDatabase();
            System.out.println("===== 数据库初始化成功 =====");
        } catch (Exception e) {
            System.err.println("===== 数据库初始化失败 =====");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("===== 学生信息管理系统已关闭 =====");
    }
}
