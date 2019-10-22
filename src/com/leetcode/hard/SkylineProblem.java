package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        if(buildings.length == 0) {
            return new ArrayList<>();
        }
        Strip[] strips = new Strip[buildings.length];

        List<List<Integer>> pairList = new ArrayList<>();
        for( int i = 0; i < buildings.length; i++) {
            strips[i] =  new Strip(buildings[i][0], buildings[i][1], buildings[i][2]);
        }

        List<Integer> pair;

        int j = 0, comparison = 0;
        for( int i = 1; i < strips.length; i++) {
            if(enveloped(strips[j], strips[i])) {
                continue;
            }
            else if(enveloped(strips[i], strips[j])) {
                j = i;
                continue;
            }
            if(comparison == 0) {
                pair = new ArrayList<>(Arrays.asList(new Integer[]{strips[j].x, strips[j].height}));
                pairList.add(pair);
            }
            pairList.addAll(compare(strips[j], strips[i]));
            comparison++;
            j++;
        }

        if(comparison > 0) {
            pair = new ArrayList<>(Arrays.asList(new Integer[]{strips[strips.length - 1].y, 0}));
            pairList.add(pair);
        }

        if(comparison == 0) {
            pair = new ArrayList<>(Arrays.asList(new Integer[]{strips[j].x, strips[j].height}));
            pairList.add(pair);
            pair = new ArrayList<>(Arrays.asList(new Integer[]{strips[j].y, 0}));
            pairList.add(pair);
        }

        return pairList;
    }

    private boolean enveloped(Strip strip, Strip strip1) {
        return strip.height >= strip1.height && strip.x <= strip1.x & strip.y >= strip1.y;
    }

    private List<List<Integer>> compare(Strip strip, Strip strip1) {

        List<List<Integer>> pairList = new ArrayList<>();
        List<Integer> pair;
        if(strip1.x > strip.y) {
             pair = new ArrayList<>();
            pair.add(strip.y);
            pair.add(0);
            pairList.add(pair);
            pair = new ArrayList<>();
            pair.add(strip1.x);
            pair.add(strip1.height);
            pairList.add(pair);

        }
        else if(strip1.height < strip.height) {
            if(strip1.y < strip.y) {
                pair = new ArrayList<>();
                pair.add(strip.y);
                pair.add(0);

                pairList.add(pair);
            }
            else if(strip1.y > strip.y) {
                pair = new ArrayList<>();
                pair.add(strip.y);
                pair.add(strip1.height);

                pairList.add(pair);
            }
        }

        else if (strip1.height > strip.height) {
            pair = new ArrayList<>();
            pair.add(strip1.x);
            pair.add(strip1.height);

            pairList.add(pair);
        }

        return pairList;
    }

    public static void main(String args[]) {
        int [][] buildings = new int[][]{{2,4,7},{2,4,5},{2,4,6}};
        SkylineProblem problem = new SkylineProblem();
        List<List<Integer>>  pairList = problem.getSkyline(buildings);

        pairList.stream().forEach(pair -> System.out.println(pair.get(0)+", "+pair.get(1) + "\n"));
    }


    public class Strip {

        public int x;
        public int y;
        public int height;

        public Strip(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

}
