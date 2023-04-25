package com.leetcode.hard.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UniquePermutations {
    public List<List<Integer>> permuteUnique(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        int n = arr.length;
        while(true) {
            res.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
            int i = n-1;
            while(i -- > 0) {
                if(arr[i] < arr[i+1]) {
                    break;
                }
            }
            if(i < 0) {
                break;
            } else {
                for(int k = n-1; k > i; k--) {
                    if(arr[i] < arr[k]) {
                        swap(arr, i, k);
                        break;
                    }
                }
                reverse(arr, i+1);
            }
        }
        return res;
    }

    public void swap(int[] arr, int i, int j) {
        arr[i] = (arr[i] ^ arr[j]);
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
    public void reverse(int[] arr, int start) {
        for(int end = arr.length-1; end > start; end--, start++) {
            swap(arr, start, end);
        }
    }

}
