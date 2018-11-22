package com.wt.test.netty.math;

/**
 * @author WuTian
 * @date 2018-11-22 16:49
 * @description
 */
public class MathTest {
    public static void main(String[] args) {
        String hex = "ff";
        System.out.println(Integer.parseInt(hex, 16));
        System.out.println(Integer.toHexString(255));
    }
}
