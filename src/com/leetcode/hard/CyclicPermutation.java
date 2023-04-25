package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class CyclicPermutation {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i)
            res.add(start ^ i ^ i >> 1);
        return res;
    }

    public static void main(String[] args) {
        CyclicPermutation cp = new CyclicPermutation();
        System.out.println(cp.circularPermutation(3, 2));
    }
}
