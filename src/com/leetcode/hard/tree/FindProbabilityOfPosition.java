package com.leetcode.hard.tree;

import java.util.*;

public class FindProbabilityOfPosition {
    Map<Integer, Map<Integer, Double>> probabilityMap;

    FindProbabilityOfPosition(Node root) {
        probabilityMap = findProbability(root);
    }

    public Map<Integer, Map<Integer, Double>> findProbability(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        map.putIfAbsent(0, new HashMap<>());
        map.get(0).put(root.val, 1.0);
        int level = 0;
        while (!q.isEmpty()) {
            level += 1;
            int size = q.size();
            map.putIfAbsent(level, new HashMap<>());
            while (size-- > 0) {
                Node parent = q.poll();
                double branches = (double)parent.children.size();
                for (Node children : parent.children) {
                    Map<Integer, Double> temp = map.get(level);
                    double prob = (double) (map.get(level - 1).get(parent.val) * (1 / branches));
                    temp.put(children.val, temp.getOrDefault(children.val, 0.0) + prob);
                    q.offer(children);
                }
            }
        }
        return map;
    }

    double findProbability(int timestamp, int nodeVal) {
        if (probabilityMap.get(timestamp).get(nodeVal) == null) {
            return 0.0;
        }
        return probabilityMap.get(timestamp).get(nodeVal);
    }

    static class Node {
        int val;
        List<Node> children;
        Node(int val) {
        	this.val = val;
        	this.children = new ArrayList<>();
		}
    }

    public static void main(String[] args) {
    	Node node0 = new Node(0);
    	Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		Node node8 = new Node(8);
		Node node9 = new Node(9);
		Node node10 = new Node(10);
    	node0.children.addAll(Arrays.asList(node1, node2, node3));
    	node1.children.addAll(Arrays.asList(node4, node5));
    	node2.children.addAll(Arrays.asList(node6, node7));
		node3.children.addAll(Arrays.asList(node8, node9, node10));

		Node root = new Node(1);
		root.children = new ArrayList();
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		root.children.add(n2);
		root.children.add(n3);

		Node n4 = new Node(4);
		n2.children = new ArrayList();
		n2.children.add(n4);

		n3.children = new ArrayList();
		n3.children.add(n4);
		n3.children.add(n2);
		
		//FindProbabilityOfPosition fpp = new FindProbabilityOfPosition(node0);
		FindProbabilityOfPosition fpp = new FindProbabilityOfPosition(root);
		for(int i = 0; i < 3; i++) {
			System.out.print("level = "+i);
			for(int j = 1; j <= 4; j++) {
				System.out.print("probability of node " + j + " = " + fpp.findProbability(i, j) + " ");
			}
			System.out.println();
		}
	}
}
