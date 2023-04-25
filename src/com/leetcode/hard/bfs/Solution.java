package com.leetcode.hard.bfs;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] target) {
        Map<String, Set<String>> adj = new HashMap<>();
        Map<String, Integer> nameNumberMap = new HashMap<>();
        int index = 0;
        for(String name : names) {
            nameNumberMap.put(name, index++);
        }
        for(int[] road : roads) {
            String city1 = names[road[0]];
            String city2 = names[road[1]];
            adj.computeIfAbsent(city1, x -> new HashSet<>()).add(city2);
            adj.computeIfAbsent(city2, x -> new HashSet<>()).add(city1);
        }
        int count = 0;
        Queue<CityDetails> queue = new LinkedList<>();
        int[] traversals = new int[target.length];
        for(index = 0; index < target.length; index++) {
            if(!nameNumberMap.containsKey(target[index])) {
                count++;
                traversals[index] = roads[0][index % 2];
            } else{
                queue.offer(new CityDetails(target[index], count, traversals, index));
                break;
            }
        }
        if(count == target.length) {
            return Arrays.stream(traversals).boxed().collect(Collectors.toList());
        }
        int minEditDistance = target.length;
        int[] result = new int[target.length];
        while(!queue.isEmpty()) {
            CityDetails currentCity = queue.poll();
            if(currentCity.index == target.length-1) {
                if(currentCity.missedCount == 0) {
                    break;
                }
                if(currentCity.missedCount < minEditDistance) {
                    minEditDistance = currentCity.missedCount;
                    result = currentCity.traversals;
                }
                continue;
            }
            for(String city : adj.get(currentCity.cityName)) {
                index = currentCity.index + 1;
                int missedCount = currentCity.missedCount + (city.equals(target[index]) ? 0 : 1);
                int[] travs = cloneParent(currentCity.traversals, index);
                travs[index] = nameNumberMap.get(city);
                queue.offer(new CityDetails(city, missedCount, travs, index));
            }
        }
        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    private int[] cloneParent(int[] currentArr, int index) {
        int[] traversals = new int[currentArr.length];
        for(int i = 0; i < index; i++) {
            traversals[i] = currentArr[i];
        }
        return traversals;
    }

    private class CityDetails {
        String cityName;
        int missedCount;
        int[] traversals;
        int index;
        private CityDetails(String cityName, int missedCount, int[] traversals, int index) {
            this.cityName = cityName;
            this.missedCount = missedCount;
            this.traversals = traversals;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        new Solution().mostSimilar(5,
                new int[][] {{0,2},{0,3},{1,2},{1,3},{1,4},{2,4}},
                new String[]{"ATL","PEK","LAX","DXB","HND"},
                new String[] {"ATL","DXB","HND","LAX"});
    }
}