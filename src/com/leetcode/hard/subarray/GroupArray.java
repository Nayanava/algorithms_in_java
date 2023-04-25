package com.leetcode.hard.subarray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupArray {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        if(groupSizes.length == 0)
            return new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < groupSizes.length; i++) {
            map.putIfAbsent(groupSizes[i], new ArrayList<>());
            map.get(groupSizes[i]).add(i);
        }

        List<List<Integer>> listOfLists = new ArrayList<>();

        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            List<Integer> values = entry.getValue();
            List<Integer> list = new ArrayList<>();
            int count = 0;
            for(Integer value : values) {
                if(count > 0 && count % key == 0) {
                    listOfLists.add(list);
                    list = new ArrayList<>();
                }
                list.add(value);
                count++;
            }
            listOfLists.add(list);
        }
        return listOfLists;
    }

    public static void main(String args[]) {
        int arr[] = {3,3,3,3,3,1,3};
        GroupArray ga = new GroupArray();
        ga.groupThePeople(arr);
    }
}
