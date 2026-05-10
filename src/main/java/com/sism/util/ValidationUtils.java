package com.sism.util;

/**
 * 表单验证工具类
 */
public class ValidationUtils {

    private ValidationUtils() {}

    /**
     * 学号：6位数字
     */
    public static boolean isValidId(String id) {
        return id != null && id.matches("\\d{6}");
    }

    /**
     * 非空判断
     */
    public static boolean isNonEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    /**
     * 年龄：正整数
     */
    public static boolean isValidAge(String ageStr) {
        if (ageStr == null || ageStr.trim().isEmpty()) return false;
        try {
            int age = Integer.parseInt(ageStr);
            return age > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}