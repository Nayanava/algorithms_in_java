package com.leetcode.hard.graph;

import java.util.*;

public class ClosestDifferencePath {
	Map<String, Set<String>> neighbours = new HashMap<>();

	public int findMinDifference(String[] arr) {
		Set<String> nodes = listAllNodes();
		int min = Integer.MAX_VALUE;
		Map<String, Integer> dp = new HashMap<>();
		for (String node : nodes) {
			if (diff(arr[0], node) > min) {
				continue;
			}
			min = Math.min(min, dfs(node, 1, arr, dp));
		}
		return min;
	}

	private int dfs(String node, int index, String[] arr, Map<String, Integer> map) {
		if (index == arr.length) {
			return 0;
		}
		String key = node + "_" + index;
		if (map.containsKey(key)) {
			System.out.println("hit");
			return map.get(key);
		}
		Set<String> nodes = null;
		if (!neighbours.containsKey(node)) {
			nodes = getAllNeighbours(node);
			neighbours.put(node, nodes);
		} else {
			nodes = neighbours.get(node);
		}
		int min = Integer.MAX_VALUE;
		for (String neighbour : nodes) {
			int diff = diff(neighbour, arr[index]);
			if (diff > min) {
				continue;
			}
			int temp = dfs(neighbour, index + 1, arr, map);
			if (temp != Integer.MAX_VALUE) {
				min = Math.min(min, temp + diff);
			}
		}
		map.put(key, min);
		return min;
	}

	private int diff(String a, String b) {
		int diff = 0;
		for (int i = 0; i < 3; i++) {
			diff += Math.abs(a.charAt(i) - b.charAt(i));
		}
		return diff;
	}

	static Map<String, Set<String>> neighbors = new HashMap();

	private static Set<String> getAllNeighbours(String str) {
		return neighbors.get(str);
	}

	private static Set<String> listAllNodes() {
		Set<String> nodes = new HashSet<>(Arrays.asList(new String[]{"AAA", "BBB", "EDD", "DDD", "CCD", "XXX", "BBC"}));
		return nodes;
	}


	public static void main(String[] args) {
		neighbors.put("AAA", new HashSet(Arrays.asList(new String[]{"BBB", "DDD", "XXX", "BBC"})));
		neighbors.put("BBB", new HashSet(Arrays.asList(new String[]{"AAA", "EDD"})));
		neighbors.put("EDD", new HashSet(Arrays.asList(new String[]{"BBB", "DDD"})));
		neighbors.put("DDD", new HashSet(Arrays.asList(new String[]{"AAA", "EDD", "CCD"})));
		neighbors.put("CCD", new HashSet(Arrays.asList(new String[]{"BBC", "DDD", "XXX"})));
		neighbors.put("XXX", new HashSet(Arrays.asList(new String[]{"AAA", "CCD"})));
		neighbors.put("BBC", new HashSet(Arrays.asList(new String[]{"AAA", "CCD"})));

		String[] arr = {"AAA", "BBB", "CCC", "DDD"};
		ClosestDifferencePath cdp = new ClosestDifferencePath();
		System.out.print(cdp.findMinDifference(arr));
		arr = new String[] {"AAA", "CCC", "AAA", "BBD"};
		System.out.print(cdp.findMinDifference(arr));

	}
}
