# 学生信息管理系统（SISM）

> Java Web 课程设计大作业 —— 基于 Servlet + JSP + MySQL 的学生信息管理系统

---

## 项目简介

本项目是一个基于 B/S 架构的 Web 应用，管理员可通过浏览器完成学生信息和成绩的增删改查操作。系统采用经典的 MVC 分层架构，使用 Java Servlet 和 JSP 技术实现，数据存储采用 MySQL 数据库进行持久化。

---

## 功能特性

### 核心功能

- [x] **用户登录** — 预设管理员账号，Session 维护登录状态
- [x] **学生列表** — 分页展示所有学生信息
- [x] **添加学生** — 录入学号、姓名、性别、年龄、班级，验证学号唯一性
- [x] **修改学生** — 根据学号修改学生信息
- [x] **删除学生** — 删除前 JavaScript 确认提示
- [x] **退出登录** — 销毁 Session，重定向到登录页

### 加分功能

- [x] **模糊查询** — 支持按学号或姓名模糊搜索学生
- [x] **分页展示** — 每页 5 条记录，含页码导航
- [x] **密码修改** — 管理员可修改登录密码（存入数据库）
- [x] **前端美化** — 统一的 CSS 样式，卡片式布局
- [x] **数据库持久化** — MySQL 存储，JDBC 操作
- [x] **成绩管理** — 学生各科成绩的增删改查

---

## 技术栈

| 层级 | 技术 |
|------|------|
| 前端 | HTML + CSS + JSP |
| 后端 | Jakarta Servlet (Tomcat 9.0+) |
| JDK | JDK 17+ |
| 数据库 | MySQL 8.0 |
| 构建工具 | Maven 3.8+ |
| 架构模式 | MVC |

---

## 项目结构

```
SISM/
├── pom.xml                              # Maven 配置文件
├── README.md                            # 项目说明文档
├── docs/
│   └── 学生信息管理系统-完整文档.md      # 系统设计文档
└── src/main/
    ├── java/com/lggyx/sism/
    │   ├── listener/
    │   │   └── AppContextListener.java    # 应用启动监听器（自动初始化数据库）
    │   ├── model/
    │   │   ├── Student.java               # 学生实体类
    │   │   └── Score.java                 # 成绩实体类
    │   ├── dao/
    │   │   ├── StudentDataStore.java      # 学生数据访问对象（JDBC）
    │   │   ├── ScoreDataStore.java        # 成绩数据访问对象（JDBC）
    │   │   └── AdminDataStore.java        # 管理员数据访问对象（JDBC）
    │   ├── servlet/
    │   │   ├── LoginServlet.java          # 登录验证
    │   │   ├── LogoutServlet.java         # 退出登录
    │   │   ├── IndexServlet.java          # 首页（分页查询）
    │   │   ├── SearchServlet.java         # 模糊查询
    │   │   ├── AddStudentServlet.java     # 添加学生
    │   │   ├── EditStudentServlet.java    # 编辑学生（数据准备）
    │   │   ├── UpdateStudentServlet.java  # 更新学生
    │   │   ├── DeleteStudentServlet.java  # 删除学生
    │   │   ├── ChangePasswordServlet.java # 密码修改
    │   │   ├── ScoreListServlet.java      # 成绩列表
    │   │   ├── AddScoreServlet.java       # 添加成绩
    │   │   ├── EditScoreServlet.java      # 编辑成绩（数据准备）
    │   │   ├── UpdateScoreServlet.java    # 更新成绩
    │   │   └── DeleteScoreServlet.java    # 删除成绩
    │   └── util/
    │       └── DBUtil.java                # 数据库连接工具类
    └── webapp/
        ├── WEB-INF/web.xml                # Web 应用配置
        ├── css/style.css                  # 全局样式
        ├── login.jsp                      # 登录页面
        ├── index.jsp                      # 学生列表首页（含查询/分页）
        ├── addStudent.jsp                 # 添加学生表单
        ├── editStudent.jsp                # 修改学生表单
        ├── scoreList.jsp                  # 成绩列表页
        ├── addScore.jsp                   # 添加成绩表单
        ├── editScore.jsp                  # 修改成绩表单
        ├── changePassword.jsp             # 密码修改页面
        └── error.jsp                      # 错误提示页面
```

---

## 部署说明

### 环境要求

- JDK 17+
- Maven 3.8+
- Tomcat 9.0+
- MySQL 8.0+

### 数据库准备

1. 启动 MySQL 服务
2. 创建数据库：

```sql
CREATE DATABASE sism CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

> 无需手动建表，系统首次启动时会自动创建表并插入初始数据。

### 部署运行

#### 方式一：IDE 中运行（推荐）

1. 使用 IntelliJ IDEA 或 Eclipse 导入项目
2. 配置 Tomcat 9.0+ 服务器
3. 将 MySQL Connector/J 添加到运行类路径（或 Maven 自动导入）
4. 启动 Tomcat，访问 `http://localhost:8080/SISM-1.0-SNAPSHOT/login`

#### 方式二：WAR 包部署

```bash
# 编译打包
mvn clean package

# 将 target/SISM-1.0-SNAPSHOT.war 复制到 Tomcat 的 webapps 目录
# 启动 Tomcat，自动解压部署
```

### 默认登录账号

| 用户名 | 密码 |
|--------|------|
| admin | 123456 |

---

## 使用说明

### 学生管理

1. 登录后进入首页，查看学生列表（分页展示，每页 5 条）
2. 点击"+ 添加学生"录入新学生信息
3. 在搜索框输入学号或姓名进行模糊查询
4. 点击"修改"按钮编辑学生信息（学号不可修改）
5. 点击"删除"按钮，确认后删除学生

### 成绩管理

1. 点击顶部导航栏的"成绩管理"进入成绩列表
2. 点击"+ 添加成绩"为学生录入课程成绩（0-100 分）
3. 支持修改和删除成绩记录

### 密码修改

1. 点击顶部导航栏的"修改密码"
2. 输入原密码和新密码（不少于 6 位）
3. 修改成功后需使用新密码重新登录

---

## 数据验证规则

| 字段 | 规则 |
|------|------|
| 学号 | 6 位数字，全局唯一 |
| 姓名 | 非空 |
| 性别 | 男/女 单选 |
| 年龄 | 正整数 |
| 班级 | 下拉选择（计算机1班/计算机2班/软件工程班）|
| 成绩 | 0-100 之间的数字 |

---

## 注意事项

- 数据库连接配置位于 `src/main/java/com/lggyx/sism/util/DBUtil.java`，默认连接 `localhost:3306/sism`，用户名 `root`，密码 `123456`
- 如需修改数据库配置，请编辑 `DBUtil.java` 中的连接参数
- 系统首次启动时会自动初始化数据库表结构和示例数据（12 名学生）
- 删除学生时，关联的成绩记录会级联删除

---

## 文档说明

- `docs/学生信息管理系统-完整文档.md` — 完整的系统分析、设计、实现文档，包含 Mermaid 图表

---

## 作者

- Java Web 课程设计大作业
