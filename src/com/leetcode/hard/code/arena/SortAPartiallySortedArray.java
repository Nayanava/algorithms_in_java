package com.leetcode.hard.code.arena;

public class SortAPartiallySortedArray {
    public void sortArray(int arr[]) {
        int lsb = (1 << 4) - 1;
        int msb = Integer.MAX_VALUE - lsb;
        int prevGrp = arr[0] & msb, start = 0, n = arr.length;
        for (int i = 1; i < n; i++) {
            int newGrp = (arr[i] & msb);
            if (newGrp != prevGrp) {
                sortArray(arr, start, i - 1, prevGrp, lsb);
                start = i;
                prevGrp = newGrp;
            }
        }
        sortArray(arr, start, n-1, prevGrp, lsb);
    }
    private void sortArray(int[] arr, int start, int end, int grpHead, int lsb) {
        boolean[] temp = new boolean[16];
        for(int i = start; i <= end; i++) {
            temp[arr[i] & lsb] = true;
        }
        int index = start;
        for(int i = 0; i < 16; i++) {
            if(temp[i]) {
                arr[index++] = grpHead + i;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {0, 15, 12, 17, 18, 19, 33, 32};
        SortAPartiallySortedArray sortAPartiallySortedArray = new SortAPartiallySortedArray();
        sortAPartiallySortedArray.sortArray(arr);
        for(int i : arr) {
            System.out.print(i + " ");
        }
    }
}
