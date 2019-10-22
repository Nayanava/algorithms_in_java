package com.leetcode.hard;

import java.util.*;
import java.util.stream.IntStream;

class KillProcess {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> childMap = new HashMap<>();

        IntStream.range(0, ppid.size()).forEach(id -> {
            List<Integer> childrenList;
            if(!childMap.containsKey(ppid.get(id))) {
                childrenList = new ArrayList<>();
                childrenList.add(pid.get(id));
            }
            else {
                childrenList = childMap.get(ppid.get(id));
                childrenList.add(pid.get(id));
            }
            childMap.put(ppid.get(id), childrenList);

        });

        List<Integer> killList = new ArrayList<>();
        killRecursion(kill, childMap, killList);

        return killList;
    }

    public void killRecursion(Integer child, Map<Integer, List<Integer>> childMap, List<Integer> killList) {

        killList.add(child);
        if(!childMap.containsKey(child)) {
            return;
        }

        for(Integer newChild : childMap.get(child)) {
            killRecursion(newChild, childMap, killList);
        }
    }

    public static void main(String[] arg) {
        KillProcess killProcess = new KillProcess();
        List<Integer> pid = Arrays.asList(new Integer[]{1, 3, 10, 5});
        List<Integer> ppid = Arrays.asList(new Integer[] {3, 0, 5, 3});
        int kill = 5;
        killProcess.killProcess(pid, ppid, kill);
    }
}