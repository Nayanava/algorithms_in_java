package com.codeforces.div.third.r629;

import java.util.*;

public class KthBeautifulString {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long[] arr = new long[100001];
        for(int i = 0; i <= 100000; i++) {
            arr[i] = ((long)i*(i-1))/2;
        }
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt(), k = s.nextInt();
            int index = Arrays.binarySearch(arr, k);
            if(index < 0) {
                index = -(index+1);
            }
            char[] chs = new char[n];
            int first = index - 1, second = index - 2;
            second -= arr[index] - k;
            chs[first] = 'b';
            chs[second] = 'b';
            for (int x = 0; x < n; x++) {
                if (x != first && x != second) {
                    chs[x] = 'a';
                }
            }
            for (int x = n - 1; x >= 0; x--) {
                System.out.print(chs[x]);
            }
            System.out.println();
        }
    }
}
