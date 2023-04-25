//package com.leetcode.hard.divide.and.conquer;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//public class ShortestWayToFormString {
//
//    public int shortestWay1(String source, String target) {
//        char[] cs = source.toCharArray(), ts = target.toCharArray();
//        int res = 1;
//        List<Integer>[] idx = new List[26];
//        for (int i = 0; i < 26; i++) idx[i] = new ArrayList<>();
//        for (int i = 0; i < cs.length; i++) idx[cs[i] - 'a'].add(i);
//        int j = 0;
//        for (int i = 0; i < ts.length; i++) {
//            List<Integer> tar = idx[ts[i] - 'a'];
//            if (tar.isEmpty()) return -1;
//            int k = Collections.binarySearch(tar,j);
//            if (k < 0) k = -k - 1;
//            if (k == tar.size()) {
//                res++;
//                j = tar.get(0) + 1;
//            } else {
//                j = tar.get(k) + 1;
//            }
//        }
//        return res;
//    }
//
//    class Solution {
//        public int shortestWay(String source, String target) {
//            List<Integer>[] map = new List[26];
//            Arrays.fill(map, new ArrayList<>());
//            for(int i = 0; i < source.length(); i++) {
//                map[source.charAt(i) - 'a'].add(i);
//            }
//
//            int j = 0, res = 1;
//            for(int i = 0; i < target.length(); i++) {
//                List<Integer> list = map[target.charAt(i) - 'a'];
//                if(list.isEmpty()) return -1;
//                int k = Collections.binarySearch(list, j);
//                if( k < 0) k = -(k+1);
//                if(k == list.size()) {
//                    res++;
//                    j = list.get(0) + 1;
//                }
//                else {
//                    j = list.get(k) + 1;
//                }
//            }
//            return res;
//        }
//    }
//
//    public static void main(String[] args) {
//        String source = "abcab";
//        String target = "xzyxz";
//
//        ShortestWayToFormString str = new ShortestWayToFormString();
//
//        System.out.println(str.shortestWay(source, target));
//    }
//}
