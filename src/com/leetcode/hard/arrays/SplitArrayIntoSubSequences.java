package com.leetcode.hard.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SplitArrayIntoSubSequences {
    public boolean isPossible(int[] arr) {
        List<Stack<Integer>> list = new ArrayList<>();
        int high = 0;
        Stack<Integer> st = new Stack();
        st.push(arr[0]);
        list.add(st);
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == arr[i-1]) {
                if(high < 0) {
                    high = appendToList(list, arr[i]);
                }
                else if(list.get(high).peek() == arr[i] - 1) {
                    list.get(high--).add(arr[i]);
                }
                else {
                    high = appendToList(list, arr[i]);
                }
            }
            else {
                high = list.size() - 1;
                if(list.get(high).peek() == arr[i] - 1) {
                    list.get(high--).add(arr[i]);
                }else {
                    high = appendToList(list, arr[i]);
                }
            }
        }

        for(Stack<Integer> s : list) {
            if(s.size() < 3) {
                return false;
            }
        }

        return true;
    }

    private int appendToList(List<Stack<Integer>> list, int i) {
        Stack <Integer> st = new Stack<>();
        st.push(i);
        list.add(st);
        return list.size() - 1;
    }
}
