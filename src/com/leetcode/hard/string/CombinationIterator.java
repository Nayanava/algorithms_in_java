package com.leetcode.hard.string;

import java.util.LinkedList;
import java.util.Queue;

class CombinationIterator {
    private String word;
    int length;
    Queue<String> queue = new LinkedList<>();

    public CombinationIterator(String characters, int combinationLength) {
        word = characters;
        length = combinationLength;
        int n = characters.length();
        if(n == 0)
            return;
        populateQueue(word, length, "", 0);
    }

    private void populateQueue(String word, int length, String res, int index) {
        if(length == 0) {
            queue.offer(res);
            return;
        }
        for (int i = index; i < word.length(); i++) {
            populateQueue(word, length-1, res + word.charAt(i), i+1);
        }
    }

    public String next() {
        return queue.poll();
    }
    
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public static void main(String[] args) {
        CombinationIterator ci = new CombinationIterator("abcequx",4);
        System.out.println(ci.hasNext());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.hasNext());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());




    }
}