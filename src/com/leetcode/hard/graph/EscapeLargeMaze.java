package com.leetcode.hard.graph;

import java.util.HashSet;
import java.util.Set;

class EscapeLargeMaze {
    int[] row = {0, 0, -1, 1};
    int[] col = {-1, 1, 0, 0};
    
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> blocks = new HashSet<>();
        Set<String> seen = new HashSet<>();
        Set<String> src = new HashSet<>();
        Set<String> dest = new HashSet<>();
        for(int[] block : blocked) {
            blocks.add(block[0] + "_" + block[1]);
        }
        String srcNode = source[0] + "_" + source[1];
        String destNode = target[0] + "_" + target[1];
        
        if(blocks.contains(srcNode) || blocks.contains(destNode)) {
            return false;
        }
        src.add(srcNode);
        dest.add(destNode);
        if(bfs(src, dest, blocks, seen)) {
            src = new HashSet<>();
            src.add(srcNode);
            dest = new HashSet<>();
            dest.add(destNode);
            return bfs(dest, src, blocks, new HashSet<>());
        }
        return false;
    }
    
    boolean bfs(Set<String> src, Set<String> target, Set<String> blocked, Set<String> seen) {
        Set<String> temp = new HashSet<>();
        for(String curr : src) {
            String[] idx = curr.split("_");
            int[] val = new int[]{Integer.valueOf(idx[0]), Integer.valueOf(idx[1])};
            for(int i = 0; i < 4; i++) {
                int nextRow = val[0] + row[i];
                int nextCol = val[1] + col[i];
                String next = nextRow + "_" + nextCol;
                if(isSafe(nextRow, nextCol, next, seen, blocked)) {
                    if(target.contains(next)) {
                        return true;
                    }
                    seen.add(next);
                    temp.add(next);
                }
            }
        }
        for(String next : temp) {
            System.out.print(next + " ");
        }
        System.out.println();
        if(temp.size() >= blocked.size() * 2)
            return true;
        
        return !temp.isEmpty() && bfs(temp, target, blocked, seen);
    }
    boolean isSafe(int x, int y, String next, Set<String> seen, Set<String> blocked) {
        return x >= 0 && y >= 0 && !seen.contains(next) && !blocked.contains(next);
    }

    public static void main(String[] args) {
        int[][] blocked =  {{0,3},{1,0},{1,1},{1,2},{1,3}};
        int [] src = {0,0};
        int[] dest = {0,2};

        EscapeLargeMaze elm = new EscapeLargeMaze();
        System.out.println(elm.isEscapePossible(blocked, src, dest));
    }
}