package com.leetcode.hard.linesweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class SkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length;
        int index = 0;
        BuildingPoint[] points = new BuildingPoint[n*2];
        for(int i = 0; i < n; i++) {
            points[index++] = new BuildingPoint(buildings[i][0], buildings[i][2], true);
            points[index++] = new BuildingPoint(buildings[i][1], buildings[i][2], false);
        }
        Arrays.sort(points, (a, b) -> {
            if(a.x != b.x)
                return a.x-b.x;
            if(a.isStart && b.isStart) {
                return b.y - a.y;
            } else if (!a.isStart && !b.isStart) {
                return a.y - b.y;
            } else {
                if (a.isStart) {
                    return a.y;
                }
                return b.y;
            }
        });
        
        TreeMap<Integer, Integer> q = new TreeMap<>();
        List<List<Integer>> res = new ArrayList<>();
        q.put(0, 1);
        int prevMax = 0, currentMax = 0;
        for(int i = 0; i < 2*n; i++) {
            if(points[i].isStart) {
                q.put(points[i].y, q.getOrDefault(points[i].y, 0) + 1);
            } else {
                q.put(points[i].y, q.get(points[i].y)-1);
                if(q.get(points[i].y) == 0)
                    q.remove(points[i].y);
            }
            
            currentMax = q.lastKey();
            if(currentMax != prevMax) {
                res.add(Arrays.asList(new Integer[]{points[i].x, currentMax}));
                prevMax = currentMax;
            }
        }
        return res;
    }
    
    class BuildingPoint {
        int x, y;
        boolean isStart;
        
        BuildingPoint(int x, int y, boolean isStart) {
            this.x = x;
            this.y = y;
            this.isStart = isStart;
        }
    }
}