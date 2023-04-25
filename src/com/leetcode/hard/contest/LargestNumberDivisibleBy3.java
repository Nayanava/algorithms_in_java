package com.leetcode.hard.contest;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class LargestNumberDivisibleBy3 {
    public String largestMultipleOfThree(int[] digits) {
        int[] buckets = new int[10];
        int sum = 0;
        boolean removedNum = false;
        for(int i = 0; i < digits.length; i++) {
            sum += digits[i];
            buckets[digits[i]] ++;
        }
        if(sum % 3 == 0) {
            return largestNumber(buckets);
        }
        if(sum % 3 == 2) {
            for(int i = 2; i < 10; i += 3) {
                if(buckets[i] > 0) {
                    buckets[i]--;
                    removedNum = true;
                    break;
                }
            }
            if(!removedNum) {
                int toRemove = 2;
                for(int i = 1; i <= 9; i+= 3) {
                    if(buckets[i] == toRemove) {
                        buckets[i] -= toRemove;
                        break;
                    } else if(buckets[i] == 1) {
                        buckets[i]--;
                        toRemove--;
                    }
                }
            }
        } else {
            for(int i = 1; i < 10; i+=3) {
                if(buckets[i] > 0) {
                    buckets[i]--;
                    removedNum = true;
                    break;
                }
            }
            if(!removedNum) {
                int toRemove = 2;
                for(int i = 2; i < 10; i += 3) {
                    if(buckets[i] == toRemove) {
                        buckets[i] -= toRemove;
                        break;
                    } else if(buckets[i] == 1) {
                        buckets[i]--;
                        toRemove--;
                    }
                }
            }
        }
        return largestNumber(buckets);
    }

    private String largestNumber(int[] buckets) {
        StringBuilder num = new StringBuilder();
        for (int i = 9; i > 0; i--) {
            for (int j = 0; j < buckets[i]; j++) {
                num.append(i);
            }
        }
        if(num.length() > 0) {
            for(int i = 0; i < buckets[0]; i++) {
                num.append(0);
            }
        }
        if(num.length() == 0) {
            if(buckets[0] > 0)
                return "0";
            return "";
        }
        return num.toString();
    }
    public static void main(String[] args) {
        int[] digits = {5,8};
        System.out.println(new LargestNumberDivisibleBy3().largestMultipleOfThree(digits));
    }
}

