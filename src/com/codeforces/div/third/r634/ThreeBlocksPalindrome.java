package com.codeforces.div.third.r634;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ThreeBlocksPalindrome {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0) {
            List<Integer>[] arr = new List[201];
            for(int i = 0; i <= 200; i++) {
                arr[i] = new ArrayList<>();
            }
            int n = Integer.parseInt(in.readLine());
            String[] next = in.readLine().split(" ");
            for(int i = 0; i < n; i++) {
                arr[Integer.parseInt(next[i])].add(i);
            }
            int max = 1;
            for(int i = 1; i <= 200; i++) {
                if(arr[i].isEmpty() || arr[i].size() == 1) {
                    continue;
                }
                for(int j = 1; j <= 200; j++) {
                    if(i == j) {
                        max = Math.max(max, arr[i].size());
                        continue;
                    }
                    int temp[] = new int[arr[i].size() + arr[j].size()];
                    int x = 0, y = 0, index = 0;
                    while(x < arr[i].size() && y < arr[j].size()) {
                        if(arr[i].get(x) < arr[j].get(y)) {
                            temp[index++] = 0;
                            x++;
                        } else {
                            temp[index++] = 1;
                            y++;
                        }
                    }
                    while(x < arr[i].size()) {
                        temp[index++] = 0;
                        x++;
                    }
                    while(y < arr[j].size()) {
                        temp[index++] = 1;
                        y++;
                    }
                    max = Math.max(max, findLPS(temp, y));
                }
            }
            out.println(max);
        }
        out.close();
        in.close();
    }

    private static int findLPS(int[] arr, int y) {
        int res = 0;
        int l = 0, r = arr.length-1, x = 0;
        while(l <= r) {
            while(l < r && arr[l] !=0) {
                l++;
                y--;
            }
            while(l < r && arr[r] != 0) {
                r--;
                y--;
            }
            if(l >= r) {
                break;
            }
            res = Math.max(2*(++x) + y, res);
            l++; r--;
        }
        return res;
    }
}
