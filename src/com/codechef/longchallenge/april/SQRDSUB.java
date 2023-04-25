package com.codechef.longchallenge.april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SQRDSUB {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String st = in.readLine();
        int T = Integer.parseInt(st);
        while(T-- > 0) {
            st = in.readLine();
            int n = Integer.parseInt(st);
            int[] arr = new int[n];
            String[] split = in.readLine().split(" ");
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(split[i]);
                if(arr[i] < 0) {
                    arr[i] = -(arr[i]);
                }
            }
            long total = atleastTwoTwos(arr) + zeroTwos(arr);
            out.println(total);
        }
        out.close();
        in.close();
    }

    private static long atleastTwoTwos(int[] arr) {
        int  start = 0, n = arr.length;
        long res = 0;
        int twos = 2;
        for(int i = 0; i < n; i++) {
            twos -= countTwos(arr[i]);
            while(start <= i && arr[i] == 0 || twos <= 0) {
                res += (long)n-i;
                twos += countTwos(arr[start++]);
            }
        }
        return res;
    }

    private static long zeroTwos(int[] arr) {
        int start = 0, n = arr.length;
        long res = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0 || arr[i] % 2 == 0) {
                start = i+1;
            }
            res += (long)i-start+1;
        }
        return res;
    }
    private static int countTwos(int num) {
        int count = 0, a = num;
        while(a > 0 && a % 2 == 0) {
            a /= 2;
            count++;
        }
        return count;
    }
}
//2, 5, 6
//2,5,6
//5,7 => 5, 7, (5,7)
//2, 5, 7, 6
//2, 2, 3, 5 -> 2,2) 2,2,3) 2,2,3,5
//2,4,3,2


