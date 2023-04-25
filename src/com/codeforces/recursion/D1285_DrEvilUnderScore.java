package com.codeforces.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D1285_DrEvilUnderScore {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(s.nextInt());
        }
        System.out.println(postorderDfs(list, 30));
    }

    private static int postorderDfs(List<Integer> list, int bit) {
        if(list.isEmpty() || bit < 0) {
            return 0;
        }
        List<Integer> set = new ArrayList<>();
        List<Integer> reset = new ArrayList<>();
        for(int i : list) {
            if(((1 << bit) & i) == 0) {
                reset.add(i);
            } else {
                set.add(i);
            }
        }
        if(set.isEmpty())
            return postorderDfs(reset, bit-1);
        if(reset.isEmpty())
            return postorderDfs(set, bit-1);
        return Math.min(postorderDfs(set, bit-1), postorderDfs(reset, bit-1)) + (1 << bit);
    }
}
