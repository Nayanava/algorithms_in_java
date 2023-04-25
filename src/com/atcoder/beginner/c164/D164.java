package com.atcoder.beginner.c164;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class D164 {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        char[] arr = in.readLine().toCharArray();
        List<Integer> list = new LinkedList<>();
        int res = 0;
        for(int i = 0; i < arr.length; i++) {
            for(int index = 0; index < list.size(); index++) {
                int num = list.get(index);
                num = num * 10 + arr[i] - '0';
                num %= 2019;
                if(num == 0) {
                    res++;
                    list.remove(index);
                } else {
                    list.set(index, num);
                }
            }
            list.add(arr[i]-'0');
        }
        out.println(res);
        out.close();
        in.close();
    }
}
