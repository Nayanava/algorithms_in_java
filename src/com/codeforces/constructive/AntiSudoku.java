package com.codeforces.constructive;

import java.io.*;

public class AntiSudoku {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int T = Integer.parseInt(in.readLine());
        char[][] arr = new char[9][9];
        while (T-- > 0) {
            for (int i = 0; i < 9; i++) {
                arr[i] = in.readLine().toCharArray();
            }
            for (int i = 0, j = 0; i < 9; i += 3, j = (j + 1) % 3) {
                for (int x = i; x < i + 3; x++, j += 3) {
                    arr[x][j] = arr[x][j] == '1' ? '2' : '1';
                }
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    out.print(arr[i][j]);
                }
                out.println();
            }
        }
        out.close();
        in.close();
    }
}