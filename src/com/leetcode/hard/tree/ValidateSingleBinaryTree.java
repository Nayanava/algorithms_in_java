package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

import java.util.*;

public class ValidateSingleBinaryTree {
    public boolean isSingleBinaryTree(List<TreeNode> nodes) {
        Map<TreeNode, Integer> map = new HashMap<>();
        for (TreeNode node : nodes) {
            if (!map.containsKey(node)) {
                map.put(node, 0);
            }
            if (node.left != null) {
                if (map.getOrDefault(node.left, 0) == 1) {
                    return false;
                }
                map.put(node.left, 1);
            }
            if (node.right != null) {
                if (map.getOrDefault(node.right, 0) == 1) {
                    return false;
                }
                map.put(node.right, 1);
            }
        }
        if (map.size() != nodes.size()) {
            return false;
        }
        int rootCount = 0;
        for (TreeNode node : map.keySet()) {
            if (map.get(node) == 0) {
                if (++rootCount > 1) {
                    return false;
                }
            }
        }
        return rootCount == 1;
    }

    public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);

		n1.left = n2;
		n2.left = n1;

		List<TreeNode> nodes = Arrays.asList(n1, n2);
		ValidateSingleBinaryTree vsbt = new ValidateSingleBinaryTree();
		System.out.println(vsbt.isSingleBinaryTree(nodes));
	}
}


