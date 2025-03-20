package com.test.java;

import java.util.Arrays;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        int[] list = {100,200,300};
        
        for(int n : list) { //향상된 for문 > (코드변환) >이터레이터
            System.out.println(n);
        }
        
        Iterator<Integer> iter = Arrays.stream(list).iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
        
    }
}
