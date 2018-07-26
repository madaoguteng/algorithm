package com.mark.algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

public class ShellSort extends SortTemplete{

    @Override
    public void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3*h + 1;
        while (h>=1){
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h = h/3;
        }
    }

    public static void main(String[] args){
        System.out.println("请输入需要排序的字符串，多个字符串用半角空格分割：");
        //j a v a i s e a s y
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputs = reader.readLine();
            if (inputs != null && !"".equals(inputs)){
                String[] a = inputs.split(" ");
                new ShellSort().sort(a);
                show(a);
                assertTrue(InsertSort.isSorted(a));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
