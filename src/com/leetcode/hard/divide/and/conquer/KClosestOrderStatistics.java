package com.leetcode.hard.divide.and.conquer;

import java.util.Deque;
import java.util.LinkedList;

class KClosestOrderStatistics {
    public int[][] kClosest(int[][] points, int K) {
        randomized_select(points, 0, points.length -1, K);
        int[][] result = new int[K][2];
        int index = 0;
        for(int[] point : points) {
            if(K == index) {
                break;
            }
            result[index] = point;
            index++;
        }
        
        return result;
    }
    
    private int randomized_select(int[][]points, int low, int high, int K) {
        
        if(low >= high) {
            return low;
        }
        int pivot = partition(points, low, high);
        int rank = pivot - low + 1;
        
        if(rank == K) return pivot;
        if(rank > K)
            return randomized_select(points, low, pivot - 1, K);
        return randomized_select(points, pivot + 1, high, K-rank);
    }
    
    private int partition(int[][] points, int low, int high) {
        int pivot = low + (int)(Math.random() % (high - low + 1));
        swap(points, pivot, low);
        int i = low;
        
        for(int j = low + 1; j <= high; j++) {
            if(dist(points[j]) <= dist(points[low])) {
                i = i + 1;
                swap(points, i, j);
            }
        }
        swap(points, low, i);
        return i;
    }
    
    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
    private int dist(int[] a) {
        return (a[0]*a[0] + a[1]*a[1]);
    }

    public static void main(String[] args) {
        int arr[][] = {{3,3},{5,-1},{-2,4}};
        int k = 2;
        Deque<Integer> q = new LinkedList<>();

        KClosestOrderStatistics solution = new KClosestOrderStatistics();
        System.out.println(solution.kClosest(arr, k));
    }
}