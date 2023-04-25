package com.leetcode.hard.string;

import java.util.Arrays;

class DeriveStringAFromB {
    public boolean isPossible(String a, String b) {
        char[] ch = b.toCharArray();
        int head = 0, tail = ch.length - 1;
        while (Math.abs(head - tail) >= a.length()) {
            if (ch[tail] == 'a') {
                tail += tail > head ? -1 : 1;
            } else {
                int temp = tail;
                temp += (tail > head) ? -1 : 1;
                tail = head;
                head = temp;
            }
        }
        return a.equals(String.valueOf(Arrays.copyOfRange(ch, Math.min(head, tail), Math.max(head, tail)+1)));
    }

    public static void main(String[] args) {
    	String a = "aba", b = "ababab";
    	DeriveStringAFromB aFromB = new DeriveStringAFromB();
    	System.out.println(aFromB.isPossible(a, b));
	}
}
