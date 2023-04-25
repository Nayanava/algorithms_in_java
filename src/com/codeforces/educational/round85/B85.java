package com.codeforces.educational.round85;

import java.util.Arrays;
import java.util.Scanner;

public class B85 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            double k = s.nextDouble();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = s.nextInt();
            }
            Arrays.sort(arr);
            int mid = -1;
            for(int i = 0; i < n; i++) {
                if(arr[i] >= k) {
                    mid = i;
                    break;
                }
            }
            if(mid == -1) {
                System.out.println(0);
                continue;
            }
            if(mid == n) {
                System.out.println(n);
            }
            int left = mid-1, right = n-1;
            long sum = 0;
            int res = n-mid;
            while(left >= 0 && right >= mid) {
                sum += arr[right];
                while( left >= 0 && ((double)sum + arr[left])/((n-right)+(mid-left)) >= k) {
                    sum += arr[left--];
                    res++;
                }
                right--;
            }
            System.out.println(res);
        }
    }
}
