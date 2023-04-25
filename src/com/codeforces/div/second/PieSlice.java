package com.codeforces.div.second;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class PieSlice {

    public int pieSliceMax(int[] arr, int n) {
        if(n <= 0)
            return 0;
        if(n == 1)
            return arr[0];
        if(n == 2) {
            return Math.max(arr[0], arr[1]);
        }
        return Math.max(arr[n-1] + pieSliceMax(arr, n-2), pieSliceMax(arr, n-1));
    }

    public static void main(String[] args) {
        int arr[] = {10, 21, 10, 21, 10};
        System.out.print(new PieSlice().pieSliceMax(arr, arr.length));
    }
}
