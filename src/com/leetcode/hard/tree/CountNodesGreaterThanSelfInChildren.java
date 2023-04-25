package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

import java.util.*;

public class CountNodesGreaterThanSelfInChildren {
    Map<TreeNode, Integer> dpMap =new HashMap<>();

    CountNodesGreaterThanSelfInChildren(TreeNode root) {
        //populate map
        countNodes(root);
    }

    public int findNodeCount(TreeNode root) {
        return dpMap.getOrDefault(root, -1);
    }

    public List<Integer> countNodes(TreeNode root) {
        if(root.left == null && root.right == null) {
			dpMap.put(root, 0);
			return Arrays.asList(new Integer[] {root.val});
		}
        List<Integer> left = countNodes(root.left);
        List<Integer> right = countNodes(root.right);

        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }
        while (i < left.size()) {
            merged.add(left.get(i++));
        }
        while (j < right.size()) {
            merged.add(right.get(j++));
        }
        int index = Collections.binarySearch(merged, root.val);
        int count = merged.size();
        if (index < 0) {
            index = -(index + 1);
            count -= index;
        } else {
        	count -= index+1;
		}
        dpMap.put(root, count);
        merged.add(index, root.val);

        return merged;
    }

    public static void main(String[] args) {
    	TreeNode root = TreeNode.buildTree
				(new Integer[]{1,3,4,6,7,10,2,5,4});
    	CountNodesGreaterThanSelfInChildren nodesGreaterThanSelfInChildren = new CountNodesGreaterThanSelfInChildren(root);

    	for(TreeNode node : nodesGreaterThanSelfInChildren.dpMap.keySet()) {
    		System.out.println(node.val + " " + nodesGreaterThanSelfInChildren.dpMap.get(node));
		}
	}
}
