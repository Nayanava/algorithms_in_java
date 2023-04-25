package com.codeforces.div.third.r629;

import java.util.Scanner;
import java.util.TreeMap;

public class MakeKEqual {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int val = s.nextInt();
            treeMap.put(val, treeMap.getOrDefault(val, 0) + 1);
        }
        int[][] prefCount = new int[treeMap.size()][3];
        int[][] suffCount = new int[treeMap.size()][3];
        int index = 0;
        for(int key : treeMap.keySet()) {
            prefCount[index][0] = key;
            if(index == 0) {
                prefCount[index][1] = 0;
                prefCount[index++][2] = treeMap.get(key);
                continue;
            }
            prefCount[index][1] = (treeMap.get(key) - prefCount[index-1][0]) * prefCount[index-1][2];
            prefCount[index][2] = treeMap.get(key) + prefCount[index-1][2];
            index++;
        }
        index = n-1;
        for(int key : treeMap.descendingKeySet()) {
            suffCount[index][0] = key;
            if(index == n-1) {
                suffCount[index][1] = 0;
                suffCount[index++][2] = treeMap.get(key);
                continue;
            }
            suffCount[index][1] = (suffCount[index+1][0] - treeMap.get(key)) * suffCount[index+1][2];
            suffCount[index][2] = treeMap.get(key) + suffCount[index+1][2];
        }
    }
}
