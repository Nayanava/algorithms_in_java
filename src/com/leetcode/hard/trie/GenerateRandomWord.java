package com.leetcode.hard.trie;

import java.util.*;

public class GenerateRandomWord {
    static class Trie {
        List<String> words;
        Node root;

        Trie() {
            root = new Node();
            words = new ArrayList<>();
        }

        public Set<String> getAllWords() {
            return new HashSet<>(words);
        }

        public void addWord(String word) {
            Node temp = root;
            for (char c : word.toCharArray()) {
                if (temp.children[c - 'a'] == null) {
                    temp.children[c - 'a'] = new Node();
                }
                temp.childCount[c - 'a']++;
                temp = temp.children[c - 'a'];
            }
            words.add(word);
            temp.isEndOfWord = true;
            temp.wordIndex = words.size() - 1;
        }

        public String getRandomWord() {
            Random rand = new Random();
            int index = rand.nextInt(words.size());
            System.out.println(index);
            StringBuilder sb = new StringBuilder();
            findWord(root, index, sb);
            return sb.toString();
        }

        public void findWord(Node temp, int index, StringBuilder sb) {
            if (index == 0 && temp.isEndOfWord) {
                return;
            }

            int sum = temp.isEndOfWord ? 1 : 0;
            for (int i = 0; i < 26; i++) {
                if (sum + temp.childCount[i] <= index) {
                    sum += temp.childCount[i];
                } else {
                    temp = temp.children[i];
                    sb.append((char)(i + 'a'));
                    index -= sum;
                    findWord(temp, index, sb);
                    return;
                }
            }
        }
    }

    static class Node {
        boolean isEndOfWord;
        int wordIndex;
        Node[] children;
        int[] childCount;

        Node() {
            children = new Node[26];
            childCount = new int[26];
        }
    }

    public static void main(String[] args) {
    	String[] words = {"able", "all", "are", "ares", "bale", "aress"};
    	Trie trie = new Trie();
        for(String word : words) {
            trie.addWord(word);
        }
        trie.getAllWords().stream().forEach(word -> System.out.print(word + " "));
        System.out.println();
        System.out.println(trie.getRandomWord());
	}
}
