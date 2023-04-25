package com.leetcode.hard.subarray;

import java.util.*;

public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int W) {
        if(hand.length % W != 0)
            return false;

        Arrays.sort(hand);
        List<Stack<Integer>> list = new ArrayList<>(W);
        for(int i = 0; i < W; i++) {
            list.add(new Stack<Integer>());
        }
        for(int i = 0; i < hand.length; i++) {
            int j = 0;
            boolean pushed = false;
            for(j = 0; j < list.size(); j++) {
                if(list.get(j).size() >= W)
                    continue;
                if(list.get(j).isEmpty()) {
                    list.get(j).push(hand[i]);
                    pushed = true;
                    break;
                }
                else if(list.get(j).peek() == hand[i] - 1) {
                    list.get(j).push(i);
                    pushed = true;
                    break;
                }
            }
            if(!pushed)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,6,2,3,4,7,8};
        int W = 3;
        Random random = new Random();
        System.out.print(new HandOfStraights().isNStraightHand(arr, W));
    }
}
