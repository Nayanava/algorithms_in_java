package com.codeforces.educational.round1;

import java.util.Scanner;

public class QueriesOnString {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.next();
        int n = s.nextInt();
        while(n-- > 0) {
            int start = s.nextInt()-1, end = s.nextInt();
            String left = start == 0 ? "" : str.substring(0, start);
            String right = str.substring(end);
            String shifted = cycleShift(str.substring(start, end), s.nextInt());
            str = left+shifted+right;
        }
        System.out.println(str);
    }

    private static String cycleShift(String str, int count) {

        char[] chs = str.toCharArray();
        int len = str.length();
        boolean[] swapped = new boolean[chs.length];
        count %= len;
        for (int it = 0; it < count; it++) {
            if(swapped[it]) {
                continue;
            }
            int i = it, j = -1;
            char replace = chs[it];
            while (j != it) {
                swapped[i] = true;
                j = (i + count);
                j = j >= len ? j % len : j;
                char toReplace = chs[j];
                chs[j] = replace;
                replace = toReplace;
                i = j;
            }
        }
        return String.valueOf(chs);
    }
}
