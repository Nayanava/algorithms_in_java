package com.leetcode.hard.graph.paths;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OpenTheLock {
    int count = 0;
    Set<String> blocks;
    char[][] map;
    public int openLock(String[] deadends, String target) {
        Set<String> src = new HashSet<>();
        Set<String> dest = new HashSet<>();
        blocks = new HashSet<>(Arrays.asList(deadends));
        src.add("0000");
        map = new char[10][2];
        for(char i = '1'; i <= '8'; i++) {
            map[i-'0'][0] = (char) (i-1);
            map[i-'0'][1] = (char) (i+1);
        }
        map[0][0] = '9'; map[0][1] = '1';
        map[9][0] = '8'; map[9][1] = '1';
        dest.add(target);
        if(blocks.contains(target) || blocks.contains("0000")) {
            return -1;
        }
        int res =  bfs(src, dest);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    int bfs(Set<String> src, Set<String> dest) {
        if(src.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        if(src.size() > dest.size()) {
            return bfs(dest, src);
        }
        Set<String> temp = new HashSet<>();
        for(String str : src) {
            char[] chs = str.toCharArray();
            for(int i = 0; i < 4; i++) {
                char old = chs[i];
                for(char ch : map[old - '0']) {
                    chs[i] = ch;
                    String next = String.valueOf(chs);
                    if(blocks.contains(next)) {
                        continue;
                    }
                    if(dest.contains(next)) {
                        return 1;
                    }
                    temp.add(next);
                }
                chs[i] = old;
            }
        }
        int res = bfs(temp, dest);
        return res == Integer.MAX_VALUE ? res : res + 1;
    }

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        OpenTheLock otl = new OpenTheLock();
        System.out.println(otl.openLock(deadends, target));
    }
}
