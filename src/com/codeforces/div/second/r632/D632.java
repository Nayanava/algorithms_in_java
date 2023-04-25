package com.codeforces.div.second.r632;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class D632 {
    static int INF = (int) 1e9 + 7;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            int a[] = new int[n];
            boolean cant = false;
            List<Integer>[] indexes = new List[2];
            indexes[0] = new ArrayList<>();
            indexes[1] = new ArrayList<>();
            int minA = 1, minB = INF;
            for(int i = 0; i < n; i++) {
                a[i] = s.nextInt();
                if(a[i] == -1) {
                    indexes[0].add(i);
                } else if(a[i] == 1) {
                    indexes[1].add(i);
                }
            }
            for(int i = 0; i < n; i++) {
                int curr = s.nextInt();
                if(curr == a[i]) continue;
                if(i == 0 && a[i] != curr) {
                    cant = true;
                } else {
                    int index = -1;
                    if(curr < a[i]) {
                        index = Collections.binarySearch(indexes[0], i-1);
                    } else {
                        index = Collections.binarySearch(indexes[1], i-1);
                    }
                    if(index == -1) {
                        cant = true;
                    }
                }
            }
            System.out.println(cant ? "NO" : "YES");
        }
    }
}
