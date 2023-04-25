package com.codeforces.sortings;

import java.util.*;

public class DrazilAndFactorial {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<String> numbers = new ArrayList<>();
        String[] values = new String[10];
        values[2] = "2";
        values[3] = "3";
        values[4] = "322";
        values[5] = "5";
        values[6] = "53";
        values[7] = "7";
        values[8] = "7222";
        values[9] = "7332";
        String str = s.next();
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()) {
            if(c == '0' || c == '1') {
                continue;
            }
            sb.append(values[c-'0']);
        }
        char[] chs = sb.toString().toCharArray();
        Arrays.sort(chs);
        for(int i = chs.length-1; i >= 0; i--) {
            System.out.print(chs[i]);
        }
    }
}
