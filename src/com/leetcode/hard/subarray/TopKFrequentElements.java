package com.leetcode.hard.subarray;

import java.util.*;

class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        Data arr[] = new Data[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr[index++] = new Data(entry.getKey(), entry.getValue());
        }

        int pivot = random_select(arr, k, 0, index-1);
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i <= pivot; i++) {
        	list.add(arr[i].val);
		}
        return list;
    }

    private int random_select(Data[] arr, int K, int low, int high) {
        int pivot = partition(arr, low, high);
        int rank = pivot - low + 1;
        if (rank == K)
            return pivot;
        if (rank > K) {
            return random_select(arr, K, low, pivot - 1);
        }
        return random_select(arr, K - rank, pivot + 1, high);
    }

    private int partition(Data[] arr, int low, int high) {
        int pivot = low + (int)(Math.random() % (high - low + 1));
        swap(arr, pivot, low);
        pivot = low;
        int i = low;
        for (int j = low + 1; j <= high; j++) {
            if (arr[j].count > arr[pivot].count) {
                i = i + 1;
                swap(arr, i, j);
            }
        }
        swap(arr, pivot, i);
        return i;
    }

    private void swap(Data[] arr, int i, int j) {
        Data temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    class Data {
        int count = 0;
        int val = 0;

        Data(int count, int val) {
            this.count = count;
            this.val = val;
        }
    }

}
