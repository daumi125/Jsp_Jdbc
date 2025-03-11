package com.test.java;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Random;

public class Ex01 {
    public static void main(String[] args) {
        //구구단 중 랜덤으로 하나 출력 > 웹페이지로
        
        try {
            Random rnd = new Random();
            
            int dan = rnd.nextInt(8)+2;
            
            //webapp 폴더 이하
//            File file = new File(".");
//            System.out.println(file.getAbsolutePath());
            
            FileWriter writer = new FileWriter(".\\src\\main\\webapp\\ex01.html");
            
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<meta charset='UTF-8'>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write(String.format("<h1>%d단</h1>", dan));
            for(int i=1; i<=9; i++) {
                writer.write(String.format("<div>%d x %d = %d</div>", dan, i, dan*i));
            }
            writer.write("</body>");
            writer.write("</html>");
            
            
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
