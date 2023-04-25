package com.leetcode.hard.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SelfDescriptiveNumbers {
    static Map<Integer, Integer> assigned = new HashMap<>();
    static int[] cnt = new int[10];
    public static void main(String[] args) {
        SelfDescriptiveNumbers sdn = new SelfDescriptiveNumbers();
        for(int i = 1; i < 5; i++) {
            assigned.put(0, i);
            sdn.uselessNumbers(0, i);
        }
    }

    private boolean uselessNumbers(int index, int total) {
        if(total > 10) {
            return false;
        }
        if(index == total - 1) {
            if(validate(cnt, total)) {
                Arrays.stream(cnt).forEach(i -> System.out.print(i + " "));
                return true;
            }
        }
        if(cnt[index] != assigned.getOrDefault(index, 0)) {
            cnt[index] = assigned.get(index) + 1;
            assigned.put(cnt[index], assigned.getOrDefault(cnt[index], 0) + 1);
            total += cnt[index];
            uselessNumbers(index+1, total);
            assigned.remove(cnt[index]);
        }
        return false;
    }

    private boolean validate(int[] cnt, int total) {
        for(int i = 0; i < total; i++) {
            if(cnt[i] != assigned.get(i)) {
                return false;
            }
        }
        return true;
    }
}
