package com.mark.algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

public class SelectionSort extends SortTemplete {

    @Override
    public void sort(Comparable[] a){
        if (a != null && a.length>1){
            for (int i = 0; i < a.length ; i++) {
                int minIndex = i;
                for (int j = i+1; j < a.length; j++) {
                    if (less(a[j], a[minIndex])){
                        minIndex = j;
                    }
                }
                if (i != minIndex) {
                    exch(a, i, minIndex);
                }
            }
        }
    }

    public static void main(String[] args){
        System.out.println("请输入需要排序的字符串，多个字符串用半角空格分割：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputs = reader.readLine();
            if (inputs != null && !"".equals(inputs)){
                String[] a = inputs.split(" ");
                new SelectionSort().sort(a);
                show(a);
                assertTrue(SortTemplete.isSorted(a));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
