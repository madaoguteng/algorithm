package com.mark.algorithm.sort;

public interface Sort<T> {

    void sort(Comparable<T>[] a);

    /**
     * a是否小于b
     * @param a
     * @param b
     * @return
     */
    boolean less(Comparable<T> a, Comparable<T> b);

    void exch(Comparable<T>[] a, int i1, int i2);
}
