package com.codeforces.dividenconquer;

import java.io.*;

public class EquivalentStrings {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String a = reader.readLine(), b = reader.readLine();
        out.println(isomorphic(a, b, 0, a.length()-1, 0, b.length()-1) ? "YES" : "NO");
        out.close();
        reader.close();
    }

    public static boolean isomorphic(String a, String b, int alo, int ahi, int blo, int bhi) {
        if(isEquivalent(a, b, alo, ahi, blo, bhi)) {
            return true;
        }
        if((ahi-alo+1) % 2 != 0 || (bhi - blo+1) % 2 != 0) {
            return false;
        }
        int amid = (ahi+alo)/2;
        int bmid = (bhi+blo)/2;
        return ((isomorphic(a, b, alo, amid, bmid+1, bhi) && isomorphic(a, b, amid+1, ahi, blo, bmid)) ||
                (isomorphic(a, b, alo, amid, blo, bmid) && isomorphic(a, b, amid+1, ahi, bmid+1, bhi)));
    }

    private static boolean isEquivalent(String a, String b, int alo, int ahi, int blo, int bhi) {
        int i = alo, j = blo;
        for(; i <= ahi; i++, j++) {
            if(a.charAt(i) != b.charAt(j)) {
                return false;
            }
        }
        return i == ahi+1 && j == bhi+1;
    }
}
