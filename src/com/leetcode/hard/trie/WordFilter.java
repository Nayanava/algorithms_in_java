package com.leetcode.hard.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WordFilter {

    Trie t;
    public WordFilter(String[] words) {
        t = new Trie(words);
    }

    public int f(String prefix, String suffix) {
        return t.search(prefix, suffix);
    }

    class Trie {
        Node prefRoot;
        Node suffRoot;
        Trie(String[] words) {
            prefRoot = new Node('0');
            suffRoot = new Node('0');
            int index = 0;
            for(String word : words) {
                buildTrie(word, index, prefRoot);
                String reverse = reversed(word);
                buildTrie(reverse, index, suffRoot);
                index++;
            }
        }
        public void buildTrie(String word, int index, Node root) {
            Node temp = root;
            for(char c : word.toCharArray()) {
                if(!temp.children.containsKey(c)) {
                    Node newNode = new Node(c);
                    temp.children.put(c, newNode);
                }
                temp = temp.children.get(c);
                temp.indices.add(index);
            }
            temp.isEOW = true;
        }

        public int search(String prefix, String suffix) {
            if(prefix.equals("") && suffix.equals("")) {
                return 0;
            }
            List<Integer> prefList = searchWord(prefRoot, prefix);
            if(suffix.isEmpty()) {
                return prefList.isEmpty() ? -1 : prefList.get(prefList.size()-1);
            }
            suffix = reversed(suffix);
            List<Integer> suffList = searchWord(suffRoot, suffix);
            if(prefix.isEmpty()) {
                return suffList.isEmpty() ? -1 : suffList.get(suffList.size()-1);
            }
            int i = prefList.size()-1, j = suffList.size()-1;
            if(prefList.isEmpty() && suffList.isEmpty()) {
                return -1;
            }
            while( i >= 0 && j >= 0) {
                if(prefList.get(i) == suffList.get(j)) {
                    return prefList.get(i);
                } if(prefList.get(i) < suffList.get(j)) {
                    j--;
                } else {
                    i--;
                }
            }
            return -1;
        }

        private String reversed(String word) {
            char[] chs = word.toCharArray();
            for(int start = 0, end = chs.length-1; start < end; start++, end--) {
                char temp = chs[start];
                chs[start] = chs[end];
                chs[end] = temp;
            }
            return String.valueOf(chs);
        }

        private List<Integer> searchWord(Node root, String word) {
            Node temp = root;
            for(char c : word.toCharArray()) {
                if(!temp.children.containsKey(c)) {
                    return new ArrayList<>();
                }
                temp = temp.children.get(c);
            }
            return temp.indices;
        }
    }
    class Node {
        char c;
        Map<Character, Node> children;
        List<Integer> indices;
        boolean isEOW;

        Node(char c) {
            this.c = c;
            children = new HashMap<>();
            indices = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        String[] words = {"abbbababbb","baaabbabbb","abababbaaa","abbbbbbbba","bbbaabbbaa","ababbaabaa","baaaaabbbb","babbabbabb","ababaababb","bbabbababa"};
        WordFilter wf = new WordFilter(words);
        System.out.println(wf.f("","abaa"));
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */