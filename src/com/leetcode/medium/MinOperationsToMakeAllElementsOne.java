package com.leetcode.medium;
/**
 * @author nayanava
 */

import java.io.*;

public class MinOperationsToMakeAllElementsOne {
    public int minOperations(int[] nums) {
        int count = 0, done = 0;
        for(int num : nums) {
            if(num == 1) {
                done++;
            }
        }
        while(done < nums.length) {
            boolean noConversions = true;
            for(int i = 0; i < nums.length-1; i++) {
                if(nums[i] == 1 && nums[i+1] == 1) {
                    continue;
                }
                if(gcd(nums[i], nums[i+1]) == 1) {
                    noConversions = false;
                    count++;
                    done++;
                    if (nums[i + 1] == 1 || (i > 0 && nums[i - 1] != 1)) {
                        nums[i] = 1;
                    } else
                        nums[i + 1] = 1;
                }
            }
            if(noConversions) {
                return -1;
            }
        }
        return count;
    }

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        MinOperationsToMakeAllElementsOne obj = new MinOperationsToMakeAllElementsOne();
        System.out.println(obj.minOperations(new int[] {6, 10, 15}));
        System.out.println(obj.minOperations(new int[] {2,10,6,14}));
    }
}
