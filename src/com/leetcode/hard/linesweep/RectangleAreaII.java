package com.leetcode.hard.linesweep;

import java.util.*;

class RectangleAreaII {
    int mod = (int)1e9+7;
    public int rectangleArea(int[][] rectangles) {
        int n = rectangles.length, index = 0;
        Point[] points = new Point[2*n];
        Map<Integer, Point> idxMapper = new HashMap<>();
        for(int i = 0; i < rectangles.length; i++) {
            Point point = new Point(rectangles[i][0], i, true);
            idxMapper.put(i, point);
            points[index++] = point;
            points[index++] = new Point(rectangles[i][2], i, false);
        }
        Arrays.sort(points, (a, b) -> a.x - b.x);
        List<Point> activeList = new ArrayList<>();
        //start line sweep on x-axis
        int prevX = 0;
        long res = 0;
        for(Point point : points) {
            res += calcArea(rectangles, activeList, point.x - prevX);
            prevX = point.x;
            
            if(point.in) {
                activeList.add(point);
            } else {
                activeList.remove(idxMapper.get(point.index));
            }
        }
        return (int)(res % mod);
    }
    
    private long calcArea(int[][] rectangles, List<Point> activeList, int xLength) {
        if(activeList.isEmpty()) {
            return 0;
        }
        int[][] intervals = new int[activeList.size()][2];
        int index = 0;
        for(Point point : activeList) {
            intervals[index][0] = rectangles[point.index][1];
            intervals[index++][1] = rectangles[point.index][3];
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int currStart = 0, currEnd = 0, length = 0;
        for(int[] interval : intervals) {
            if(currEnd >= interval[0]) {
                currEnd = Math.max(currEnd, interval[1]);
            } else {
                length += currEnd - currStart;
                currStart = interval[0];
                currEnd = interval[1];
            }
        }
        length += currEnd-currStart;
        return (long)xLength*length;
    }
    class Point {
        int x, index;
        boolean in;
        Point(int x, int index, boolean in) {
            this.x= x;
            this.index = index;
            this.in = in;
        }
    }

    public static void main(String[] args) {
        int[][] rectangles = {{22,24,67,34},{23,18,39,41},{10,63,80,98}};
        RectangleAreaII ra = new RectangleAreaII();
        System.out.println(ra.rectangleArea(rectangles));
    }
}