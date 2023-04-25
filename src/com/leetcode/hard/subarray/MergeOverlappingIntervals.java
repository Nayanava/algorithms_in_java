package com.leetcode.hard.subarray;

import java.util.Arrays;

public class MergeOverlappingIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int index = 0;
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[index][1] >= intervals[i][0])
                intervals[index][1] = intervals[i][1];
            else
                index++;
        }
        return Arrays.copyOf(intervals, index+1);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        MergeOverlappingIntervals mergeOverlappingIntervals = new MergeOverlappingIntervals();
        mergeOverlappingIntervals.merge(intervals);
    }
}
