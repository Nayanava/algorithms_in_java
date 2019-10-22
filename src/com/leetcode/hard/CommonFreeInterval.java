package com.leetcode.hard;


import com.leetcode.hard.utils.Interval;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

class CommonFreeInterval {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> flattenedInterval = schedule.stream().flatMap(interval -> interval.stream()).collect(Collectors.toList());

        Collections.sort(flattenedInterval, Comparator.comparing(interval -> interval.start));

        List<Interval> response = new ArrayList<>();
        Interval mergeWithInterval = flattenedInterval.get(0);
        for(int i = 1; i < flattenedInterval.size(); i++) {
            if(flattenedInterval.get(i).start <= mergeWithInterval.end) {
                mergeWithInterval.end = Math.max(flattenedInterval.get(i).end, mergeWithInterval.end);
            }
            else {
                response.add(new Interval(mergeWithInterval.end, flattenedInterval.get(i).start));
                mergeWithInterval = flattenedInterval.get(i);
            }
        }

        return response;
    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(new Interval(1,3),new Interval(6,7));
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(intervals);
        intervals = Arrays.asList(new Interval(2,4));
        schedule.add(intervals);
        intervals = Arrays.asList(new Interval(2,5), new Interval(9,12));
        schedule.add(intervals);

        CommonFreeInterval freeInterval = new CommonFreeInterval();

        List<Interval> response = freeInterval.employeeFreeTime(schedule);
        response.forEach(interval -> System.out.print(interval.start + ", " + interval.end + " "));
    }
}