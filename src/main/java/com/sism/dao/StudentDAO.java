package com.sism.dao;

import com.sism.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * StudentDAO — 数据访问层
 * 使用 ArrayList 模拟数据库，实现 CRUD
 */
public class StudentDAO {

    private final List<Student> students = new ArrayList<>();

    public StudentDAO() {
        initData(); // 构造时加载 15 条初始化数据
    }

    /**
     * 初始化测试数据（用于演示和分页测试）
     */
    private void initData() {
        String[] classes = {"计算机1班", "计算机2班", "软件工程班"};
        String[] genders = {"男", "女"};
        for (int i = 1; i <= 15; i++) {
            Student s = new Student();
            s.setId(String.format("%06d", 100000 + i));
            s.setName("学生" + i);
            s.setGender(genders[i % 2]);
            s.setAge(18 + (i % 5));
            s.setClassName(classes[i % 3]);
            students.add(s);
        }
    }

    /**
     * 查询所有学生
     */
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    /**
     * 根据学号查询学生
     */
    public Optional<Student> findById(String id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    /**
     * 检查学号是否已存在
     */
    public boolean existsById(String id) {
        return findById(id).isPresent();
    }

    /**
     * 添加学生（学号必须唯一）
     * @return true 成功/false（学号重复）
     */
    public boolean add(Student student) {
        if (existsById(student.getId())) {
            return false;
        }
        students.add(student);
        return true;
    }

    /**
     * 更新学生信息
     * @return true 成功/false（学号不存在）
     */
    public boolean update(Student student) {
        Optional<Student> opt = findById(student.getId());
        if (opt.isEmpty()) return false;
        Student old = opt.get();
        old.setName(student.getName());
        old.setGender(student.getGender());
        old.setAge(student.getAge());
        old.setClassName(student.getClassName());
        return true;
    }

    /**
     * 根据学号删除学生
     * @return true 成功/false（学号不存在）
     */
    public boolean delete(String id) {
        return students.removeIf(s -> s.getId().equals(id));
    }

    /**
     * 数据总量（分页用）
     */
    public int count() {
        return students.size();
    }

    /**
     * 模糊查询：根据学号或姓名匹配
     */
    public List<Student> findByKeyword(String kw) {
        String lower = kw.toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getId().contains(lower) || s.getName().toLowerCase().contains(lower)) {
                result.add(s);
            }
        }
        return result;
    }
}