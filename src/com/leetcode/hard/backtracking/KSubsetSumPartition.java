package com.leetcode.hard.backtracking;

import java.util.Arrays;

public class KSubsetSumPartition {
    int N;
    int partitionSum;
    public boolean canPartitionKSubsets(int[] arr, int K) {
        Arrays.sort(arr);
        this.N = arr.length;
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += arr[i];
        }
        this.partitionSum = sum / K;
        boolean[] visited = new boolean[N];
        return sum % K == 0 && canPartition(arr, K, visited, 0, 0);
    }

    private boolean canPartition(int[] arr, int K, boolean visited[], int count, int sum) {
        if(K == 0 && count == N) {
            return true;
        }
        if(K == 0 || count == N) {
            return false;
        }
        for(int i = 0; i < N; i++) {
            if(!visited[i] && sum + arr[i] > partitionSum) {
                break;
            }
            if(!visited[i] && sum + arr[i] <= partitionSum) {
                visited[i] = true;
                if(canPartition(arr, sum + arr[i] == partitionSum ? K-1 : K, visited, count+1, sum + arr[i] == partitionSum ? 0 : sum + arr[i])) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {129,17,74,57,1421,99,92,285,1276,218,1588,215,369,117,153,22};
        int K = 3;
        KSubsetSumPartition partition = new KSubsetSumPartition();
        System.out.println(partition.canPartitionKSubsets(arr, K));
    }
}
