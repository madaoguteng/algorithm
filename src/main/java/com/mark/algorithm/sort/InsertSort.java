package com.mark.algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

public class InsertSort extends SortTemplete {
    @Override
    public void sort(Comparable[] a){
        if (a != null && a.length>1){
            for (int i = 1; i < a.length ; i++) {
                for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                    exch(a, j, j-1);
                }
            }
        }
    }

    public void sort(Comparable[] a, int start, int end){
        int N = end - start;
        int h = 1;
        while (h < N/3) h = 3*h + 1;
        while (h>=1){
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j+start], a[j-h+start]); j -= h) {
                    exch(a, j+start, j-h+start);
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
                new SelectionSort().sort(a);
                show(a);
                assertTrue(InsertSort.isSorted(a));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
