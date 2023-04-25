package com;

import java.util.List;

public class QuickSort {
    private static void quickSort(List<String> list, int lo, int hi) {
        if(lo > hi) {
            return;
        }
        int pivot = partition(list, lo, hi);
        quickSort(list, lo, pivot-1);
        quickSort(list, pivot+1, hi);
    }

    private static int partition(List<String> list, int lo, int hi) {
        int pivot = lo, i = lo;
        for(int j = lo+1; j <= hi; j++) {
            if((list.get(pivot) + list.get(j)).compareTo(list.get(j) + list.get(pivot)) < 0) {
                swap(list, ++i, j);
            }
        }
        swap(list, pivot, i);
        return i;
    }
    private static void swap(List<String> list, int a, int b) {
        String temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }
}
