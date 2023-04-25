package com.leetcode.hard.trie;

import java.util.HashMap;
import java.util.Map;

class MagicDictionary {

    Trie t;
    /** Initialize your data structure here. */
    public MagicDictionary() {
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        t = new Trie(dict);
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        char[] arr = word.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            for(char c = 'a'; c <= 'z'; c++) {
                if(arr[i] == c)
                    continue;
                char store = arr[i];
                arr[i] = c;
                if(t.search(t.root, new String(arr)))
                    return true;
                arr[i] = store;
            }
        }
        return false;
    }
    
    class Trie {
        Node root;
        Trie(String[] words) {
            root = new Node('0');
            for(String word : words) {
                buildTree(word);
            }
        }
        public void buildTree(String word) {
            Node temp = root;
            for(char c : word.toCharArray()) {
                temp.children.putIfAbsent(c, new Node(c));
                temp = temp.children.get(c);
            }
            temp.isEnd = true;
        }
        
        public boolean search(Node root, String word) {
            Node temp = root;
            for(char c : word.toCharArray()) {
                if(!temp.children.containsKey(c))
                    return false;
                temp = temp.children.get(c);
            }
            return temp.isEnd;
        }

    }
    class Node {
        char c;
        boolean isEnd;
        Map<Character, Node> children;
        Node(char c) {
            this.c = c;
            children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        String[] dict = {"hello","leetcode"};
        MagicDictionary md = new MagicDictionary();
        md.buildDict(dict);
        System.out.print(md.search("hello"));
        System.out.print(md.search("hhllo"));
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */