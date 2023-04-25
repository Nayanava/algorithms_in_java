package com.leetcode.hard.graph;

import java.util.*;

class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<String> seen = new HashSet<>();
        Set<String> ends = new HashSet<>(Arrays.asList(deadends));
        String src = "0000";
        if(ends.contains(src) || ends.contains(target)) {
            return -1;
        }
        Queue<String> q = new LinkedList<>();
        Map<Character, List<Character>> map = new HashMap<>();
        for(char c = '1'; c < '9'; c++) {
            map.put(c, Arrays.asList(new Character[]{(char)(c - 1), (char)(c +1)}));
        }
        map.put('0', Arrays.asList('1', '9'));
        map.put('9', Arrays.asList('0', '8'));
        int level = 0;
        q.offer(src);
        while(!q.isEmpty()) {
            int size = q.size();
            level++;
            while(size-- > 0) {
                String curr = q.poll();
                char[] chs = curr.toCharArray();
                for(int i = 0; i < 4; i++) {
                    char c = chs[i];
                    for(char n : map.get(chs[i])) {
                        chs[i] = n;
                        String next = String.valueOf(chs);
                        if(next.equals(target)) {
                            return level;
                        }
                        if(seen.contains(next) || ends.contains(next)) {
                            continue;
                        }
                        seen.add(next);
                        q.offer(next);
                    }
                    chs[i] = c;
                }
            }
        }
        return -1;
    }
}