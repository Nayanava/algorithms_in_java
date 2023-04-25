package com.leetcode.hard.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupIdenticalWords {
    int p = 31;
    int mod = (int) 1e9 + 7;

    private long pow(int length) {
        long p_pow = 1;
        for (int i = 1; i < length; i++) {
            p_pow = (p_pow * p) % mod;
        }
        return p_pow;
    }

    private long hash(String s, int len) {
        long hash = 0;
        for (int i = 0; i < len; i++) {
            hash = (hash * p + (s.charAt(i) - 'a' + 1)) % mod;
        }
        return hash;
    }

    private List<List<String>> groupIdentical(String[] words) {
        List<int[]> list = new ArrayList<>();
        int index = 0;
        for (String word : words) {
            long hash = hash(word, word.length());
            list.add(new int[]{(int) hash, index++});
        }
        Collections.sort(list, (a, b) -> a[0] - b[0]);
        List<List<String>> identicalWords = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0 || list.get(i)[0] != list.get(i - 1)[0]) {
                identicalWords.add(new ArrayList<>());
            }
            identicalWords.get(identicalWords.size() - 1).add(words[list.get(i)[1]]);
        }
        return identicalWords;
    }

    public static void main(String[] args) {
        String[] words = {"test", "test", "test", "test", "testa", "testb", "testb"};
        GroupIdenticalWords giw = new GroupIdenticalWords();
        List<List<String>> identicalWords = giw.groupIdentical(words);
        for (List<String> words_ : identicalWords) {
            for (String word : words_) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }
}
