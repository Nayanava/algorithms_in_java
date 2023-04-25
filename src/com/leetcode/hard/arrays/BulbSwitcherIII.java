package com.leetcode.hard.arrays;

public class BulbSwitcherIII {
    public int numTimesAllBlue(int[] light) {
        int res = 0, n = light.length;
        for(int i = 0; i < n; i++) {
            light[i]--;
        }
        for(int i = 0; i < n; i++) {
            int max = i;
            for(int j = light[i]; j != i; j = light[i]) {
                max = Math.max(max, j);
                swap(light, i, j);
            }
            i = max;
            res++;
        }
        return res;
    }
    private void swap(int light[], int i, int j) {
        int temp = light[i];
        light[i] = light[j];
        light[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {3,2,4,1,5};
        System.out.println(new BulbSwitcherIII().numTimesAllBlue(arr));
    }
}
