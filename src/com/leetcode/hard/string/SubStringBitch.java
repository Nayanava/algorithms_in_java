package com.leetcode.hard.string;

import java.util.LinkedList;
import java.util.Queue;

public class SubStringBitch {
    int mod =(int)(1e9+7);
    public int uniqueLetterString(String S) {
        int n = S.length();
        Queue<int[]> q = new LinkedList<>();
        int index = 0;
        for(char c : S.toCharArray()) {
            int mask = 1 << (c-'a');
            q.offer(new int[]{mask, index++});
        }
        int level = 0;
        int res = 0;
        while(!q.isEmpty()) {
            level += 1;
            int size = q.size();
            while(size -- > 0) {
                int[] out = q.poll();
                res += Integer.bitCount(out[0]);
                if(out[1] + level < n) {
                    q.offer(new int[]{out[0] | 1 << S.charAt(out[1] + level), out[1] + level});
                }
            }
        }
        return res;
    }
}
