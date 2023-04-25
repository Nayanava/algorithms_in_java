//package com.leetcode.hard.computational.geometry;
//
//import javafx.util.Pair;
//
//import java.util.*;
//
//public class MinAreaRectangleII {
//    private static final int MAX_Y = 40001;
//    //1. same diagonals
//    //2. same center
//
//    private double center(Pair<Integer, Integer> pair1, Pair<Integer, Integer> pair2) {
//        return (pair1.getKey() + pair2.getKey() + (pair1.getValue() + pair2.getValue()) * MAX_Y);
//    }
//
//    private double len(int x1, int x2, int y1, int y2) {
//        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
//    }
//    public double minAreaFreeRect(int[][] points) {
//        int n = points.length;
//        if(n == 0)
//            return 0;
//        Map<String, List<Pair[]>> map = new HashMap<>();
//        for(int i = 0; i < n-1; i++) {
//            Pair<Integer, Integer> pairI = new Pair<>(points[i][0], points[i][1]);
//            for (int j = i + 1; j < n; j++) {
//                Pair<Integer, Integer> pairJ = new Pair<>(points[j][0], points[j][1]);
//                double len = len(pairI.getKey(), pairJ.getKey(), pairI.getValue(), pairJ.getValue());
//                double center = center(pairI, pairJ);
//                String key = len+"_"+center;
//                map.putIfAbsent(key, new ArrayList<>());
//                map.get(key).add(new Pair[]{pairI, pairJ});
//            }
//        }
//
//        double res = Double.MAX_VALUE;
//        for(String key : map.keySet()) {
//            List<Pair[]> pairsList = map.get(key);
//            for(int i = 0; i < pairsList.size()-1; i++) {
//                Pair<Integer, Integer>[] pairI = pairsList.get(i);
//                for(int j = i+1; j < pairsList.size(); j++) {
//                    Pair<Integer, Integer>[] pairJ = pairsList.get(j);
//                    double area = len(pairI[0].getKey(), pairJ[0].getKey(), pairI[0].getValue(), pairJ[0].getValue()) *
//                            len(pairI[1].getKey(), pairJ[0].getKey(), pairI[1].getValue(), pairJ[0].getValue());
//                    res = Math.min(res, area);
//                }
//            }
//        }
//        return res == Double.MAX_VALUE ? 0 : Math.sqrt(res);
//    }
//
//    public static void main(String[] args){
//        int[][] points = {{24420,16685},{20235,25520},{14540,20845},{20525,14500},{16876,24557},{24085,23720},{25427,18964},{21036,14573},{24420,23315},{22805,24760},{21547,25304},{16139,23952},{21360,14645},{24715,17120},{19765,25520},{19388,25491},{22340,25005},{25520,19765},{25365,21320},{23124,15443},{20845,14540},{24301,16532},{16685,24420},{25100,17875},{22125,25100},{15699,23468},{14592,21131},{25460,19155},{17837,25084},{23468,24301},{25460,20845},{18453,25304},{21131,14592},{22805,15240},{19475,25500},{15443,23124},{25355,21360},{15285,22880},{20000,25525},{24085,16280},{22163,25084},{22880,15285},{14916,22163},{16280,24085},{24875,17400},{22600,24875},{14573,21036},{25427,21036},{17120,24715},{25500,19475}};
//        MinAreaRectangleII minAreaRectangleII = new MinAreaRectangleII();
//        System.out.print(minAreaRectangleII.minAreaFreeRect(points));
//    }
//
//}
