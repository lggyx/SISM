# SISM — 学生信息管理系统开发计划

> **目标**：完成 Student Information Management System，含完整 Servlet+JSP + Word 报告
>
> **架构**：标准 3 层：Servlet 控制器 → Service 业务层 → DAO 层(+ ArrayList)，无数据库
>
> **技术栈**：Java 17, Servlet/JSP (Tomcat 9), HTML/CSS/JS

---

## Phase 1：项目骨架与工具类

### Task 1.1：建立 Maven 项目结构
- 创建 `pom.xml`（含 JUnit、poi-docx、tomcat servlet API）
- 创建 `src/main/java` → `src/main/webapp/WEB-INF/web.xml`

### Task 1.2：实体类 Student
- `Student.java` — 字段：id(String), name, gender, age, className
- 构造方法、getter/setter、toString

### Task 1.3：数据访问层 StudentDAO
- 内部 ArrayList\<Student\> 存储
- 方法：findAll, findById, add, update, delete
- 预置 15+ 条测试数据

---

## Phase 2：Servlet 控制器（核心）

### Task 2.1：登录与退出
- `LoginServlet` — 验证 admin / 123456 → 成功 setAttribute 用户信息 → forward index.jsp
- `LogoutServlet` — removeAttribute + invalidate session → redirect login.jsp

### Task 2.2：首页 IndexServlet
- DAO.findAll() → request.setAttribute("students", list) → forward index.jsp
- 复用前后，温和但有力

### Task 2.3：添加学生 AddStudentServlet
- 表单验证（学号非空/6位数字/唯一/姓名非空/年龄正整数/班级必选）
- 通过 → DAO.add() → redirect "/index"
- 失败 → forward addStudent.jsp + 错误信息

### Task 2.4：修改/编辑 EditStudentServlet + UpdateStudentServlet
- GET /editStudent → DAO.findById() + forward editStudent.jsp（表单预填）
- POST /updateStudent → 同上验证 → DAO.update() → redirect "/index"

### Task 2.5：删除 DeleteStudentServlet
- GET 学号 → DAO.delete() + forward index.jsp
- 确认弹窗用内联 JS，数据结构通过 <c:a/> 隐式完成

---

## Phase 3：JSP 页面与前端美化

### Task 3.1：登录页 login.jsp
- 表单 action="/LoginServlet"，POST
- 表单验证 + 错误提示区域

### Task 3.2：学生列表 index.jsp
- 表格展示所有学生，JSTL 遍历 + EL
- 添加、修改、删除三个按钮 + 退出登录
- 搜索框（仅供参考）
- CSS：表格斑马纹、按钮样式、响应式布局

### Task 3.3：添加/编辑表单页
- addStudent.jsp / editStudent.jsp
- 性别单选、班级下拉，表单预填逻辑
- 错误信息显示区域

### Task 3.4：全局样式 + error.jsp
- style.css（配色方案、间距、布局）
- error.jsp（友好异常页面）

---

## Phase 4：Java Web 报告（Word）

### Task 4.1：Word 报告模板结构
- 封面页 → 系统分析 → 系统设计 → 系统实现 → 测试用例 → 附录
- 宋体 TNR 化简标题层次，小号 عنوان 校正白体
- 每页一份一张实际上撤新建page4

### Task 4.2：填充各章节
- 系统分析含用例图、业务流程图描述（Mermaid/ASCII → 图片）
- 功能设计 = HTML 可视化时钟
- 数据库设计含 E-R 示意 + 表结构

---

## Timeline

| 阶段 | 内容 | 预计时间 |
|------|------|---------|
| Phase 1 | 项目骨架 + DAO + 工具类 | 1h |
| Phase 2 | Servlet 控制器（5个） | 2h |
| Phase 3 | JSP 页面 + CSS 美化 | 1.5h |
| Phase 4 | Word 报告（poi-docx） | 2h |
| **合计** | | **6.5h** |
