package com.mark.algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * 三取样切分快速排序算法.
 * 取得3个元素的子数组的中位数作为切分元素
 */
public class Quick3pSort extends SortTemplete {
    /**
     * 切分
     * @param a
     * @param lo
     * @param hi
     * @return 切分元素的位置
     */
    private int partition(Comparable[] a, int lo, int hi){
        if (hi - lo <3){
            return hi;
        }
        int mi = get3pMiddle(a, lo, hi);
        Comparable v = a[mi];
        int i = lo;
        int j = hi + 1;
        while (true){
            while ((i < hi) && less(a[i++], v));
            while (less(v, a[--j]));
            if (j<=i) break;
            exch(a, i, j);
        }
        exch(a, mi, j);
        return j;
    }

    private int get3pMiddle(Comparable[] a, int lo, int hi){
        Random random = new Random();

        int N = hi - lo + 1;

        int p0 = lo + random.nextInt(N) - 1;
        int p1 = lo + random.nextInt(N) - 1;
        int p2 = lo + random.nextInt(N) - 1;
        int mi = p0;
        if (less(a[p0], a[p1]) && less(a[p1], a[p2])) mi = p1;
        else if (less(a[p0], a[p2]) && less(a[p2], a[p1])) mi = p2;

        return mi;
    }

    private void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
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
                new Quick3pSort().sort(a);
                show(a);
                assertTrue(Quick3pSort.isSorted(a));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
