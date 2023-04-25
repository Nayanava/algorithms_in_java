package com.codeforces.educational.round84;

import java.util.Scanner;

public class GameWithChips {
    private static int INF = (int)1e9 + 9;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int m = s.nextInt(), n = s.nextInt(), k = s.nextInt();
        int totalOperations = m * n + m + n - 3;
        char[] chs = new char[totalOperations];
        int index = 0;
        for (int i = m - 1; i > 0; i--) {
            chs[index++] = 'U';
        }
        for (int j = n - 1; j > 0; j--) {
            chs[index++] = 'L';
        }
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                chs[index++] = (j % 2 == 0) ? 'R' : 'L';
            }
            if( j != m-1) {
                chs[index++] = 'D';
            }
        }
        System.out.println(totalOperations);
        System.out.print(String.valueOf(chs));
    }
}

//how many points have their destination in the path
//if the x co-ordinates are same then movement happens along the columns only
//if the y co-ordinates are same then movement happens along rows only.
// else if both are different then,
//since we move in the x-direction first, hence if the rows are same only then we check if the col is less than the max column reachable.
