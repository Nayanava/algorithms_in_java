package com.leetcode.hard.tree;

public class KthGrammar {
    public int kthGrammar(int N, int K) {
        System.out.println(Integer.numberOfTrailingZeros(Integer.highestOneBit(K)) - Integer.bitCount(K) + 1);
        return Integer.bitCount(K);
    }

    public static void main(String[] args) {
        int N = 3, k = 4;
        KthGrammar kthGrammar = new KthGrammar();
        System.out.println(kthGrammar.kthGrammar(N, k));
    }
}
