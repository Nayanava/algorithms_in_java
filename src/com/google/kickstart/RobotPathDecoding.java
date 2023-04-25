package com.google.kickstart;

import java.util.*;

public class RobotPathDecoding {
    static Integer[] dp;
    static int INF = (int) 1e9;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for(int k = 1; k <= T; k++) {
            String str = s.next();
            str = decodeString(str);
            int down = 0;
            int right = 0;
            for(char c : str.toCharArray()) {
                if(c == 'N' || c == 'S') {
                    down += c == 'S' ? 1 : -1;
                    if(Math.abs(down) == INF) {
                        down = 0;
                    }
                }
                else {
                    right += c == 'E' ? 1 : -1;
                    if(Math.abs(right) == INF) {
                        right = 0;
                    }
                }
            }
            if(right < 0) {
                right += INF;
            } if(down < 0) {
                down += INF;
            }
            System.out.println("Case #" + k + ": " + (right+1) + " " + (down+1));
        }
    }

    public static String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '(') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ')') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}
