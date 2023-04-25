package com.leetcode.hard.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MedianInSlidingWindow {
    Map<Integer, Data> map;
    TreeSet<Data> maxHeap;
    TreeSet<Data> minHeap;
    public double[] medianSlidingWindow(int[] nums, int k) {
        map = new HashMap<>();
        maxHeap = new TreeSet<>((a, b) -> {
            if (b.value != a.value)
                return b.value - a.value;
            return a.index - b.index;
        });
        minHeap = new TreeSet<>((a, b) -> {
            if(a.value != b.value) {
                return a.value - b.value;
            }
            return a.index - b.index;
        });
        int n = nums.length;
        double[] res = new double[n-k+1];
        for(int i = 0; i < k; i++) {
            insert(nums[i], i);
        }
        int index = 0;
        boolean odd = (k % 2 != 0);
        for(int i = k; i < nums.length; i++) {
            res[index++] = findMedian(odd);
            delete(i-k);
            insert(nums[i], i);
        }
        res[index] = findMedian(odd);
        return res;
    }

    private void delete(int index) {
        Data data = map.get(index);
        map.remove(index);
        if(maxHeap.contains(data)) {
            maxHeap.remove(data);
            if(minHeap.size() - maxHeap.size() >= 2) {
                Data temp = minHeap.first();
                maxHeap.add(temp);
                minHeap.remove(temp);
            }
        } else {
            minHeap.remove(data);
            if(maxHeap.size() - minHeap.size() >= 2) {
                Data temp = maxHeap.first();
                minHeap.add(temp);
                maxHeap.remove(temp);
            }
        }
    }

    private void insert(int val, int index) {
        Data data = new Data(val, index);
        if(!maxHeap.isEmpty() && val <= maxHeap.first().value) {
            maxHeap.add(data);
            if(maxHeap.size() - minHeap.size() >= 2) {
                minHeap.add(maxHeap.first());
                maxHeap.remove(maxHeap.first());
            }
        } else {
            minHeap.add(data);
            if(minHeap.size() - maxHeap.size() >= 2) {
                maxHeap.add(minHeap.first());
                minHeap.remove(minHeap.first());
            }
        }
        map.put(index, data);
    }
    private double findMedian(boolean odd) {
        if(odd) {
            if(maxHeap.size() > minHeap.size()) {
                return maxHeap.first().value;
            }
            return minHeap.first().value;
        }
        return minHeap.first().value + ((double)maxHeap.first().value - minHeap.first().value) / 2;
    }
    class Data {
        int value;
        int index;

        public Data(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        int arr[] = {-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
        int[][] temp = new int[4][5];
        int[][] visited = new int[4][5];
        int k = 3;

        MedianInSlidingWindow misw = new MedianInSlidingWindow();
        Arrays.stream(misw.medianSlidingWindow(arr, k)).forEach(val -> System.out.print(val + " "));
    }
}
