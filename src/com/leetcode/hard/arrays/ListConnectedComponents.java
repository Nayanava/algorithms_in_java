package com.leetcode.hard.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ListConnectedComponents {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>(
                Arrays.stream(G).boxed().collect(Collectors.toList()));
        ListNode temp = head;
        boolean connected = false;
        int count = 0;
        while (temp != null) {
            if (set.contains(temp.val)) {
                connected = true;
                while (temp != null && temp.next != null) {
                    temp = temp.next;
                    if (!set.contains(temp.val)) {
                        count += 1;
                        connected = false;
                        break;
                    }
                }
            }
            temp = temp.next;
        }
        if (temp == null && connected) {
            count += 1;
        }
        return count;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
