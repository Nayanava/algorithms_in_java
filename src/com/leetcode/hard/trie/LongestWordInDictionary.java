package com.leetcode.hard.trie;

import java.util.HashMap;
import java.util.Map;

public class LongestWordInDictionary {

    public String longestWord(String[] words) {
        Trie trie = new Trie(words.length);
        int index = 0;
        for (String word : words) {
            trie.insert(word, index++);
        }
        trie.dfs(trie.root);
        return trie.res;
    }

    class Trie {
        String[] words;
        Node root;
        String res = "";
        Trie(int n) {
            words = new String[n];
            root = new Node('0');
        }

        public void insert(String word, int index) {
            Node temp = root;
            for (char c : word.toCharArray()) {
                temp.children.putIfAbsent(c, new Node(c));
                temp = temp.children.get(c);
            }
            temp.wordIndex = index;
            words[index] = word;
            temp.end = true;
        }

        public void dfs(Node root) {
            if (root == null || root.children == null)
                return;
            for (Node child : root.children.values()) {
                if (!child.end) {
                    continue;
                }
                String cand = words[child.wordIndex];
                if (cand.length() > res.length() || (cand.length() == res.length() && cand.compareTo(res) < 0))
                    res = cand;
                dfs(child);
            }
        }
    }

    class Node {
        char c;
        boolean end;
        int wordIndex;
        Map<Character, Node> children = new HashMap<>();
        Node(char c) {
            this.c = c;
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"yo","ew","fc","zrc","yodn","fcm","qm","qmo","fcmz","z","ewq","yod","ewqz","y"};
        LongestWordInDictionary lwd = new LongestWordInDictionary();
        System.out.println(lwd.longestWord(words));
    }
}
