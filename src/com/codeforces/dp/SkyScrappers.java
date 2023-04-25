package com.codeforces.dp;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
 
/*
Прокрастинирую
*/

public class SkyScrappers {

    static FastReader in;
    static PrintWriter out;
    static Random rand = new Random();
    static final int INF = (int) (1e9 + 10);
    static final int MOD = (int) (1e9 + 7);
    static final int N = (int) (4e5 + 5);
    static final int LOGN = 20;

    static void solve() {
        int n = in.nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        long[][] dp = new long[n][2];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.empty() && a[st.peek()] > a[i]) {
                st.pop();
            }
            long cnt = a[i];
            if (!st.empty()) {
                cnt += a[i] * (i - st.peek() - 1);
                cnt += dp[st.peek()][0];
            } else {
                cnt += a[i] * i;
            }
            dp[i][0] = cnt;
            st.add(i);
        }
        st.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.empty() && a[st.peek()] > a[i]) {
                st.pop();
            }
            long cnt = a[i];
            if (!st.empty()) {
                cnt += a[i] * (st.peek() - i - 1);
                cnt += dp[st.peek()][1];
            } else {
                cnt += a[i] * (n - 1 - i);
            }
            dp[i][1] = cnt;
            st.add(i);
        }

        long max = 0;
        int ind = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i][0] + dp[i][1] - a[i] > max) {
                max = dp[i][0] + dp[i][1] - a[i];
                ind = i;
            }
        }

        LinkedList<Long> ans = new LinkedList<>();
        long min = a[ind];
        for (int i = ind; i < n; i++) {
            min = Math.min(min, a[i]);
            ans.addLast(min);
        }
        min = a[ind];
        for (int i = ind - 1; i >= 0; i--) {
            min = Math.min(min, a[i]);
            ans.addFirst(min);
        }

        for (long x : ans) {
            out.print(x + " ");
        }
        out.println();
    }

    public static void main(String[] args) throws FileNotFoundException {
        in = new FastReader(System.in);
//        in = new FastReader(new FileInputStream("input.txt"));
        out = new PrintWriter(System.out);
//        out = new PrintWriter(new FileOutputStream("output.txt"));


        int q = 1;
//        q = in.nextInt();

        while (q-- > 0) {
            solve();
        }

        out.close();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        Long nextLong() {
            return Long.parseLong(next());
        }

        Double nextDouble() {
            return Double.parseDouble(next());
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        String nextLine() {
            String s = "";
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }
    }
}