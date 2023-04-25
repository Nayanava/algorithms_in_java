package com.leetcode.hard.backtracking;

public class AdditiveNumbers {
    public boolean isAdditiveNumber(String num) {
        if(num.startsWith("0")) {
            return false;
        }
        for(int i = 1; i <= num.length()-2; i++) {
            int first = Integer.valueOf(num.substring(0, i));
            if(dfs(first, -1, num.substring(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int first, int second, String num) {
        if(num.isEmpty()) {
            return true;
        }
        if(second == -1) {
            for(int i = 1; i <= num.length(); i++) {
                if(i > 1 && num.startsWith("0")) {
                    return false;
                }
                second = Integer.valueOf(num.substring(0, i));
                int third = (first+second);
                String thrd = String.valueOf(third);
                if(num.substring(i).length() >= thrd.length() && num.substring(i).startsWith(thrd)) {
                    if(dfs(second, third, num.substring(String.valueOf(second).length() + thrd.length()))) {
                        return true;
                    }
                }
            }
        } else {
            String third = String.valueOf(first+second);
            if(num.length() >= third.length() && num.startsWith(third)) {
                return dfs(second, Integer.valueOf(third), num.substring(third.length()));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String num = "123";
        AdditiveNumbers an = new AdditiveNumbers();
        System.out.println(an.isAdditiveNumber(num));
    }
}
