package com.sism.dao;

import com.sism.model.Student;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * StudentDAO 单元测试
 */
class StudentDAOTest {

    @Test
    void testInitData() {
        StudentDAO dao = new StudentDAO();
        assertEquals(15, dao.count(), "初始化应为 15 条数据");
    }

    @Test
    void testFindAll() {
        StudentDAO dao = new StudentDAO();
        assertFalse(dao.findAll().isEmpty(), "findAll 不应为空");
        assertEquals(15, dao.findAll().size());
    }

    @Test
    void testFindById() {
        StudentDAO dao = new StudentDAO();
        assertTrue(dao.findById("100001").isPresent(), "应能找到 100001");
        assertFalse(dao.findById("999999").isPresent(), "不应找到 999999");
    }

    @Test
    void testAddDuplicateFails() {
        StudentDAO dao = new StudentDAO();
        Student dup = new Student("100001", "重复", "男", 20, "计算机1班");
        assertFalse(dao.add(dup), "重复学号不应添加成功");
        assertEquals(15, dao.count(), "总数不应增加");
    }

    @Test
    void testAddSuccess() {
        StudentDAO dao = new StudentDAO();
        int before = dao.count();
        Student s = new Student("999888", "新学生", "女", 19, "软件工程班");
        assertTrue(dao.add(s), "新学号应添加成功");
        assertEquals(before + 1, dao.count());
    }

    @Test
    void testUpdate() {
        StudentDAO dao = new StudentDAO();
        Student s = new Student("100001", "改了", "女", 20, "软件工程班");
        assertTrue(dao.update(s), "更新应成功");
        assertEquals("改了", dao.findById("100001").get().getName());
    }

    @Test
    void testUpdateNotFound() {
        StudentDAO dao = new StudentDAO();
        Student s = new Student("009999", "不存在的", "男", 10, "计算机1班");
        assertFalse(dao.update(s), "学号不存在时更新应失败");
    }

    @Test
    void testDelete() {
        StudentDAO dao = new StudentDAO();
        assertTrue(dao.delete("100001"), "删除应成功");
        assertFalse(dao.findById("100001").isPresent(), "删除后应找不到");
    }

    @Test
    void testDeleteNotFound() {
        StudentDAO dao = new StudentDAO();
        assertFalse(dao.delete("009999"), "不存在的学号删除应返回 false");
    }

    @Test
    void testFindByKeyword() {
        StudentDAO dao = new StudentDAO();
        List<Student> result = dao.findByKeyword("学生1");
        assertFalse(result.isEmpty());
        assertTrue(result.size() >= 2, "学生10 学生11 学生12 学生13 学生14 学生15 都应匹配");
    }
}