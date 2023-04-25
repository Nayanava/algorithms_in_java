package com.leetcode.hard.tree;

import java.util.*;

public class BreadSliceAssignment {
    int index = 0;
    char[] res;

    public char[] breadSliceAssignment(char[][] connections, int K, char host) {
        res = new char[(int) Math.pow(2, K)];
        Map<Character, List<Character>> map = new HashMap<>();
        for (char[] conn : connections) {
            map.computeIfAbsent(conn[0], x->new ArrayList<>()).add(conn[1]);
            map.computeIfAbsent(conn[1], x->new ArrayList<>()).add(conn[0]);
        }
        assignSlices(map, host, '0');
        return res;
    }

    private void assignSlices(Map<Character, List<Character>> adj, char start, char parent) {
        for (char next : adj.get(start)) {
            if (next == parent) {
                continue;
            }
            assignSlices(adj, next, start);
        }
        res[index++] = start;
    }

    public static void main(String[] args) {
        char[][] connections = {{'A', 'B'}, {'B', 'C'},
                {'C', 'D'},
                {'B', 'G'},
                {'A', 'E'},
                {'E', 'F'},
                {'A', 'H'}};
		BreadSliceAssignment bsa = new BreadSliceAssignment();
		bsa.breadSliceAssignment(connections, 3, 'A');
		for(char c : bsa.res) {
			System.out.print(c + " ");
		}
		connections = new char[][]{
				{'A','C'},
				{'C','B'},
				{'A','D'},
		};
		System.out.println("\n*********************************");
		bsa = new BreadSliceAssignment();
		bsa.breadSliceAssignment(connections, 2, 'A');
		for(char c : bsa.res) {
			System.out.print(c + " ");
		}
    }
}
