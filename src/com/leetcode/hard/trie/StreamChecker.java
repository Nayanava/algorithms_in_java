package com.leetcode.hard.trie;

import java.util.ArrayList;
import java.util.List;

class StreamChecker {

    class Node {
        boolean endFlag;
        Node[] child = new Node[26];
    }

    class TrieNode {
        int maxLength;
        Node node;
        TrieNode() {
            this.node = new Node();
        }

        public void buildTree(char[] arr) {
            Node temp = node;
            for(int i = arr.length-1; i >= 0; i--) {
                char c = arr[i];
                if(temp.child[c-'a'] == null) {
                    temp.child[c-'a'] = new Node();
                }
                temp = temp.child[c-'a'];
            }
            temp.endFlag = true;
            maxLength = Math.max(maxLength, arr.length);
        }
        public boolean search(List<Character> arr) {
            Node temp = node;
            for(char c : arr) {
                if(temp.child[c-'a'] == null)
                    return false;
                temp = temp.child[c-'a'];
                if(temp.endFlag)
                    return true;
            }
            return false;
        }
    }
    TrieNode node;
    List<Character> stream;
    public StreamChecker(String[] words) {
        node = new TrieNode();
        for(String word : words) {
            node.buildTree(word.toCharArray());
        }
        stream = new ArrayList<>(node.maxLength);
    }

    public boolean query(char letter) {
        if(stream.size() == node.maxLength) {
            stream.remove(stream.size() - 1);
        }
        stream.add(0, letter);
        return node.search(stream);
    }

    public static void main(String [] args) {
        String[] words = {"cd","f","kl"};
        StreamChecker sc = new StreamChecker(words);
        char[] arr = {'a','b','c','d','e','f','g','h','i','j','k','l'};
        for(char c : arr) {
            System.out.println(sc.query(c));
        }
    }
}