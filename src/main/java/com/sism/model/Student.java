package com.sism.model;

/**
 * Student 实体类
 * 字段：学号(id)、姓名、性别、年龄、班级
 */
public class Student {
    private String id;       // 学号（唯一标识，6位数字）
    private String name;     // 姓名
    private String gender;   // 性别：男 / 女
    private int age;         // 年龄
    private String className;// 班级

    public Student() {}

    public Student(String id, String name, String gender, int age, String className) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.className = className;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', gender='" + gender
                + "', age=" + age + ", className='" + className + "'}";
    }
}