package com.lggyx.sism.dao;

import com.lggyx.sism.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生数据存储类（单例模式，基于 ArrayList 内存存储）
 */
public class StudentDataStore {

    private static StudentDataStore instance;
    private final List<Student> students;

    private StudentDataStore() {
        students = new ArrayList<>();
        // 预置一些示例数据，方便测试
        students.add(new Student("202301", "张三", "男", 20, "计算机1班"));
        students.add(new Student("202302", "李四", "女", 19, "计算机2班"));
        students.add(new Student("202303", "王五", "男", 21, "软件工程班"));
    }

    public static synchronized StudentDataStore getInstance() {
        if (instance == null) {
            instance = new StudentDataStore();
        }
        return instance;
    }

    /**
     * 获取所有学生列表
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /**
     * 根据学号查找学生
     */
    public Student getStudentById(String studentId) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    /**
     * 添加学生
     */
    public boolean addStudent(Student student) {
        if (getStudentById(student.getStudentId()) != null) {
            return false; // 学号已存在
        }
        students.add(student);
        return true;
    }

    /**
     * 更新学生信息
     */
    public boolean updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(student.getStudentId())) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    /**
     * 根据学号删除学生
     */
    public boolean deleteStudent(String studentId) {
        return students.removeIf(s -> s.getStudentId().equals(studentId));
    }

    /**
     * 检查学号是否已存在
     */
    public boolean isStudentIdExists(String studentId) {
        return getStudentById(studentId) != null;
    }
}
