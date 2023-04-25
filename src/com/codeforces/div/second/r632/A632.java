package com.codeforces.div.second.r632;

import java.util.Scanner;

public class A632 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int m = s.nextInt();
            int n = s.nextInt();
            char[][] arr = new char[m][n];
            for (int i = 0; i < m; i++) {
                if (i % 2 == 0) {
                    arr[i][0] = 'B';
                } else {
                    arr[i][0] = 'W';
                }
                for (int j = 1; j < n; j++) {
                    arr[i][j] = (arr[i][j - 1] == 'W') ? 'B' : 'W';
                }
            }
            if ((m * n) % 2 == 0) {
                if(m%2 == 1 || n%2 == 1)
                    arr[m - 1][n - 1] = 'B';
                else
                    arr[m-1][n-2] = 'B';
            }
            for (int i = 0; i < m; i++) {
                System.out.println(String.valueOf(arr[i]));
            }
        }
    }
}
