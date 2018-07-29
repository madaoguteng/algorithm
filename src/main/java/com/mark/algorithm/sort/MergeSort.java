package com.mark.algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

/**
 * 归并排序算法
 *
 * @author mark
 * @data 2018/7/29
 */
public class MergeSort extends SortTemplete {
    /**
     * 每段最大的数组元素数量
     */
    private final int MAX_SEG_CAPACITY = 255;

    @Override
    public void sort(Comparable[] a){
        int segNum = (a.length % MAX_SEG_CAPACITY > 0) ? a.length / MAX_SEG_CAPACITY + 1 : a.length / MAX_SEG_CAPACITY;
        InsertSort insertSort = new InsertSort();

        for (int i = 0; i < segNum; i++) {
            int start = i*MAX_SEG_CAPACITY;
            int end = (start + MAX_SEG_CAPACITY) < a.length ? start + MAX_SEG_CAPACITY : a.length-1;
            insertSort.sort(a, start, end);
            if (i>0){
                merge(a, 0, start-1, end);
            }
        }
    }

    private void merge(Comparable[] a, int low, int mid, int hig){
        for (int i = mid+1; i <= hig; i++) {
            if (less(a[i], a[low])){
                //从low到mid向右移动一位,原mid+1位移动到low位
                insertAndRmove(a, low, i);
            }else if (less(a[i], a[i-1])){
                //i位与low->mid位比较，折半查找，找到第一个大于mid+1的，将mid+1插入
                int greater = bisearchFirstGreater(a, low, i-1, a[i]);
                if (greater>=0) {
                    insertAndRmove(a, greater, i);
                }
            }
        }
    }

    /**
     * 从低位到高位前的所有元素右移一位,高位插入到低位位置，
     * @param a
     * @param low
     * @param hig
     */
    private void insertAndRmove(Comparable[] a, int low, int hig){
        Comparable t = a[hig];
        for (int i = hig; i > low; i--) {
            a[i] = a[i-1];
        }
        a[low] = t;
    }

    /**
     * 折半查找第一个大于给定值的数组索引
     * @param a 已排序数组
     * @param start 查找范围起始索引
     * @param end 查找范围结束索引
     * @param v 给定值
     * @return 第一个大于给定值的数组索引
     */
    private int bisearchFirstGreater(Comparable[] a, int start, int end, Comparable v){
       int low = start;
       int hig = end;

       if (lesseq(v, a[start])){
           return start;
       }else if (lesseq(a[end], v)){
           return -1;
       } else {
           while(hig-low > 1){
               int mid = low + (hig-low)/2;
               if(lesseq(v, a[mid])){
                   hig = mid;
               }else if (less(a[mid], v)){
                   low = mid;
               }
           }

           if (lesseq(v, a[low])){
               return low;
           }else if (lesseq(v, a[hig])){
               return hig;
           }else{
               return -1;
           }
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
                new MergeSort().sort(a);
                show(a);
                assertTrue(MergeSort.isSorted(a));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
