package com.codeforces.graphs;

public class ThreeSum {
    public static void main(String[] args) {
        int a = 1, b = 1, c = 1;
        for(int i = 0; i <= 9; i++) {
            for(int j = 0; j <= 9; j++) {
                for(int k = 0; k <= 9; k++) {
                    if(i != j && j != k && 3*(100*i + 10*j + k) == 111*k) {
                        System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
    }
}
