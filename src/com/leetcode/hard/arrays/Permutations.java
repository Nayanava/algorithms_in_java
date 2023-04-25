package com.leetcode.hard.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {
    List<List<Integer>> res = new ArrayList<>();
    public void permute(int[] nums) {
        permute(nums, 0);
    }

    private void permute(int[] arr, int index) {
        if(index == arr.length) {
            res.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
            return;
        }

        for(int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            permute(arr, index+1);
            swap(arr, index, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        if( i == j)
            return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        Permutations permutations = new Permutations();
        permutations.permute(arr);

        for(List<Integer> integers : permutations.res) {
            for(int num : integers) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

