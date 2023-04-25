package com.codeforces.practice.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BerSUBall {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int res = 0;
        int[] boys = new int[m];
        for(int i = 0; i < m; i++) {
            boys[i] = s.nextInt();
        }
        int n = s.nextInt();
        int[] girls = new int[n];
        for(int i = 0; i < n; i++) {
            girls[i] = s.nextInt();
        }
        if(m > n) {
            res = findMax(girls, boys);
        } else
            res = findMax(boys, girls);
        System.out.println(res);
    }

    private static int findMax(int[] arr1, int arr2[]) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], map.getOrDefault(arr2[i], 0) + 1);
        }
        Arrays.sort(arr1);
        for(int i = 0; i < arr1.length; i++) {
            int val = arr1[i];
            for(int j = val-1; j <= val+1; j++) {
                if(map.containsKey(j)) {
                    res ++;
                    map.put(j, map.get(j)-1);
                    if(map.get(j) == 0) {
                        map.remove(j);
                    }
                    break;
                }
            }
        }
        return res;
    }
}
