package com.lggyx.sism.model;

import java.io.Serializable;

/**
 * 学生实体类
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;   // 学号（6位数字）
    private String name;        // 姓名
    private String gender;      // 性别（男/女）
    private int age;            // 年龄
    private String clazz;       // 班级

    public Student() {
    }

    public Student(String studentId, String name, String gender, int age, String clazz) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.clazz = clazz;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
