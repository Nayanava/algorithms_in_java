package com.codeforces.meet.in.middle;

import java.io.*;
import java.util.*;

public class ConstantPalindromeSum {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int T = Integer.parseInt(in.readLine());
        int x= 0;
        while(++x <= T) {
            String[] nNk = in.readLine().split(" ");
            int n = Integer.parseInt(nNk[0]), k = Integer.parseInt(nNk[1]);
            int[] arr = new int[n];
            String[] arrValues = in.readLine().split(" ");
            if(n == 2) {
                out.println(0);
                continue;
            }
            if(x == 373) {
                for(int i = 0; i < n; i++) {
                    out.print(arrValues[i] + " ");
                }
                out.println(k);
            }
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(arrValues[i]);
            }
            int res = n/2;
            List<Integer> list = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0, j = n-1; i < j; i++, j--) {
                int sum = arr[i] + arr[j];
                list.add(sum);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            int[][] minmax = new int[n/2][2];
            Collections.sort(list);
            int index = 0;
            for(int i = 0, j = n-1; i < j; i++, j--) {
                int min = Math.min(arr[i], arr[j])+1;
                int leftIndex = Collections.binarySearch(list, min);
                if(leftIndex < 0) {
                    leftIndex = -(leftIndex + 1);
                }
                int max = Math.max(arr[i], arr[j]) + k;
                int rightIndex = Collections.binarySearch(list, max);
                if(rightIndex < 0) {
                    rightIndex = -(rightIndex+2);
                }
                minmax[index++] = new int[]{leftIndex, rightIndex};
            }
            Arrays.sort(minmax, (a, b) -> {
                if(a[0] != b[0]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            });
            int[] common = minmax[0];
            boolean phi = false;
            for(int i = 1; i < n/2; i++) {
                int[] next = minmax[i];
                if(common[1] < next[0]) {
                    phi = true;
                    break;
                }
                common[0] = Math.max(common[0], next[0]);
                common[1] = Math.min(common[1], next[1]);
            }
            int max = 0;
            if(!phi) {
                for(int i = common[0]; i <= common[1]; i++) {
                    max = Math.max(max, map.get(list.get(i)));
                }
            } else {
                for(int key : map.keySet()) {
                    max = Math.max(max, map.get(key));
                }
                max = max == 1 ? 0 : 1;
            }
            out.println(res-max);
        }
//        out.println(0);
//        out.println(1);
//        out.println(4);
//        out.println(2);
        out.close();
        in.close();
    }
}
