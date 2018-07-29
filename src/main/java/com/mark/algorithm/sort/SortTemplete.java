package com.mark.algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

public class SortTemplete {

    public void sort(Comparable[] a){};

    /**
     * a是否小于b
     * @param a
     * @param b
     * @return
     */
    protected static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    };
    /**
     * a是否小于或等于b
     * @param a
     * @param b
     * @return
     */
    protected static boolean lesseq(Comparable a, Comparable b){
        return a.compareTo(b) < 0 || a.equals(b);
    };

    protected static void exch(Comparable[] a, int i1, int i2){
        Comparable t = a[i1];
        a[i1] = a[i2];
        a[i2] = t;
    }

    protected static void show(Comparable[] a){
        StringBuffer sb = new StringBuffer();
        for (Comparable v:
             a) {
            sb.append(v).append(" ");
        }
        System.out.println(sb.toString());
    }

    protected static boolean isSorted(Comparable[] a){
        for(int i=1; i<a.length; i++){
            if (less(a[i], a[i-1])){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        byte[] in = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputs = reader.readLine();
            if (inputs != null && !"".equals(inputs)){
                String[] a = inputs.split(" ");
                new SortTemplete().sort(a);
                show(a);
                assertTrue(SortTemplete.isSorted(a));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
