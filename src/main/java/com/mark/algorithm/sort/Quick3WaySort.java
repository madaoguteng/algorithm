package com.mark.algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * 三向排序算法
 */
public class Quick3WaySort extends SortTemplete {

    private void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i<=gt){
            int comp = a[i].compareTo(v);
            if (comp < 0) exch(a, i++, lt++);
            else if (comp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    private void random(Comparable[] a){
        int N = a.length;

        for (int i = 0; i < N/2; i++) {
            int r = new Random().nextInt(N);
            if (r <= N/2){
                r += N/2;
            }
            exch(a, i, r-1);
        }
    }

    @Override
    public void sort(Comparable[] a) {
        random(a);
        sort(a, 0, a.length-1);
    }

    public static void main(String[] args){
        System.out.println("请输入需要排序的字符串，多个字符串用半角空格分割：");
        //j a v a i s e a s y    e a s y q u e s t i o n
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputs = reader.readLine();
            if (inputs != null && !"".equals(inputs)){
                String[] a = inputs.split(" ");
                new Quick3WaySort().sort(a);
                show(a);
                assertTrue(Quick3WaySort.isSorted(a));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
