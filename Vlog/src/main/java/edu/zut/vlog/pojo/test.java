package edu.zut.vlog.pojo;

import java.io.File;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/16
 * Time: 21:35
 **/
public class test {
    public static void main(String[] args) {
        File file = new File("D:\\JAVA\\IDEA\\IDEAProjects\\MyVlog\\Vlog\\src\\main\\java\\edu\\zut\\vlog\\pojo\\test.txt");
        System.out.println(new File("D:\\JAVA\\IDEA\\IDEAProjects\\MyVlog\\Vlog\\src\\main\\java\\edu\\zut\\vlog\\pojo\\test.txt").length());
        System.out.println(file.getPath());
        System.out.println();
    }
}
