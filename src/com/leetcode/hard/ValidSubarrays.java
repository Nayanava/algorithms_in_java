package com.leetcode.hard;

import java.util.Stack;

class ValidSubarrays {
    public int validSubarrays(int[] nums) {

        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for( int num : nums) {
            while( !stack.empty() && num < stack.peek()) {
                stack.pop();
            }
            stack.push(num);
            count += stack.size();
        }

        return count;
    }

    public static void main(String[] args) {
        ValidSubarrays validSubarrays = new ValidSubarrays();
        int[] nums = {1, 4, 2, 5, 3};
        System.out.println(validSubarrays.validSubarrays(nums));
    }
}