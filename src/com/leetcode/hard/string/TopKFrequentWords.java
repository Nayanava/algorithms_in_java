package com.leetcode.hard.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        Data arr[] = new Data[map.size()];
        int index = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            arr[index++] = new Data(entry.getKey(), entry.getValue());
        }
        int pivot = random_select(arr, 0, index - 1, k);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(arr[i].word);
        }
        return list;
    }

    private int random_select(Data[] arr, int low, int high, int K) {
        int pivot = partition(arr, low, high);
        int rank = pivot - low + 1;
        if (rank == K)
            return pivot;
        if (rank < K) {
            return random_select(arr, pivot + 1, high, K - rank);
        }
        return random_select(arr, low, pivot - 1, K);
    }

    private int partition(Data[] arr, int low, int high) {
        int pivot = low + (int) (Math.random() % (high - low + 1));
        swap(arr, pivot, low);
        pivot = low;
        int i = low;
        for (int j = low + 1; j <= high; j++) {
            if (compare(arr[j], arr[pivot]) < 0) {
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

    private int compare(Data i, Data j) {
        if (i.count != j.count)
            return j.count - i.count;
        return j.word.compareTo(i.word);
    }

    class Data {
        String word;
        int count;

        Data(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}
