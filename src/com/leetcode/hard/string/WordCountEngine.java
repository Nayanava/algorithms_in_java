package com.leetcode.hard.string;

import java.util.*;
import java.util.stream.Collectors;

public class WordCountEngine {
    static String[][] wordCountEngine(String document) {
        String[] tokenisedArray = document.split(" ");
        PriorityQueue<WordMeta> q = new PriorityQueue<>((a, b) -> {
            if(a.count != b.count) {
                return b.count - a.count;
            }
            return a.index - b.index;
        });

        List<String> words = trimWords(tokenisedArray);
        Map<String, int[]> map = new HashMap<>();

        int index = 0;
        for (String word : words) {
            if(word.isEmpty())
                continue;
            map.putIfAbsent(word, new int[]{0, index});
            map.get(word)[0]++;
            index++;
        }
        String[][] result = new String[map.size()][2];
        index = 0;
        for (Map.Entry<String, int[]> entry : map.entrySet()) {
            q.offer(new WordMeta(entry.getKey(), entry.getValue()[0], entry.getValue()[1]));
        }
        while(!q.isEmpty()) {
            WordMeta word = q.poll();
            result[index][0] = word.word;
            result[index++][1] = String.valueOf(word.count);
        }
        return result;
    }

    static List<String> trimWords(String[] words) {
        List<String> newWords = new ArrayList<>();
        for (String word : words) {
            char arr[] = word.toLowerCase().toCharArray();
            StringBuilder temp = new StringBuilder();
            for (char c : arr) {
                if (c >= 'a' && c <= 'z') {
                    temp.append(c);
                }
            }
            newWords.add(temp.toString());
        }
        return newWords;
    }

    public static void main(String[] args) {
        String document = "Every book is a quotation; and every house is a quotation out of all forests, and mines, and stone quarries; and every man is a quotation from all his ancestors. ";
        String[][] words = wordCountEngine(document);
        for(String[] word: words) {
            System.out.print(word[0] + " " + word[1] + "\n");
        }
    }

    static class WordMeta {
        String word;
        int count, index;
        WordMeta(String word, int count, int index){
            this.count = count;
            this.word = word;
            this.index = index;
        }
    }
}
