package com.codeforces.graphs;

import java.util.*;

public class ChristmasTrees {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int arr[]  = new int[m];
        Map<Integer, Integer> map  = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        long res = 0;
        int index = -1;
        for(int i = 0; i < n; i++) {
            int val = s.nextInt();
            map.put(val, 0);
            q.offer(val);
        }
        while(!q.isEmpty()) {
            int curr = q.poll();
            if(!map.containsKey(curr-1)) {
                if(++index == m) {
                    break;
                }
                int val = map.get(curr) + 1;
                res += val;
                map.put(curr-1, val);
                arr[index] = curr-1;
                q.offer(curr-1);
            }
            if(!map.containsKey(curr+1)) {
                if(++index == m) {
                    break;
                }
                int val = map.get(curr) + 1;
                res += val;
                map.put(curr+1, val);
                arr[index] = curr+1;
                q.offer(curr+1);
            }
        }
        System.out.println(res);
        for(int i = 0; i < m; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}