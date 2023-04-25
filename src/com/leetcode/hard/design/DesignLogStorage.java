package com.leetcode.hard.design;

import java.util.*;
import java.util.stream.Collectors;

public class DesignLogStorage {
    Map<String, Integer> map = new HashMap<>();
    TreeMap<String, LinkedList<Integer>> logs;
    String min = "2000:01:01:00:00:00";
    String max = "2017:12:31:23:59:59";
    public DesignLogStorage() {
        map.put("year", 4);
        map.put("month", 7);
        map.put("day", 10);
        map.put("hour", 13);
        map.put("minute", 16);
        map.put("second", 19);
        logs = new TreeMap<>();
    }

    public void put(int id, String timestamp) {
        logs.computeIfAbsent(timestamp, x -> new LinkedList<>()).add(id);
    }
    public List<Integer> retrieve(String s, String e, String gra) {
        String key = gra.toLowerCase();
        String startTime = s.substring(0, map.get(key)) + min.substring(map.get(key));
        String endTime = e.substring(0, map.get(key)) + max.substring(map.get(key));
        return logs.subMap(startTime, true, endTime, true).entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }
}