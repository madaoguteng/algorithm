package com.mark.algorithm.sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 优先队列实现.
 *
 * @author mark
 * @date 2018/08/02
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] a;

    private int N = 0;

    public MaxPQ(int maxN){
        a = (Key[]) new Comparable[maxN+1];
    }

    private boolean less(int t, int k){
        return a[t].compareTo(a[k]) < 0;
    }

    private int max(int i, int j){
        return less(i, j) ? j : i;
    }

    private void exch(int t, int k){
        Key temp = a[t];
        a[t] = a[k];
        a[k] = temp;
    }

    private void swim(int k){
        while (k>1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink1(int k){
        while (2*k <= N){
            if (2*k < N) {
                int maxC = max(2*k, 2*k+1);
                if (less(k, maxC)) {
                    exch(k, maxC);
                    k = maxC;
                }else{
                    break;
                }
            }else if (less(k, 2*k)){
                exch(k, 2*k);
                k = 2*k;
            }
        }
    }

    private void sink(int k){
        while (2*k <= N){
            int c = 2*k;
            if (c < N && less(c, c+1)){
                c++;
            }
            if (!less(k, c)){
                break;
            }
            exch(k, c);
            k = c;
        }
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void inert(Key v){
        a[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = a[1];
        exch(1, N--);
        a[N+1] = null;
        sink(1);
        return max;
    }

    public static void main(String[] args){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(sdf.parse("2018-08-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE, -1);

        System.out.println(sdf.format(cal.getTime()));
    }
}
