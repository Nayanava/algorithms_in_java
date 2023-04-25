package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

public class LeafSimilarTrees {
	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		TreeNode head1 = morrisTraversal(root1);
		TreeNode head2 = morrisTraversal(root2);
		return compare(head1, head2);
	}

	public TreeNode morrisTraversal(TreeNode root) {
		TreeNode curr = root, head = null, tail = null;
		while(curr != null) {
			if(curr.left == null) {
				if(curr.right == null) {
					if(head == null) {
						head = curr;
						tail = curr;
					} else {
						tail.right = curr;
						tail = curr;
					}
				}
				curr = curr.right;
			} else {
				TreeNode pred = curr.left;
				while(pred.right != null && pred.right != curr) {
					pred = pred.right;
				}
				if(pred.right == null) {
					pred.right = curr;
				} else {
					if(pred.left == null) {
						if(head == null) {
							head = pred;
							tail = pred;
						} else {
							tail.right = pred;
							tail = pred;
						}

					}
					curr = curr.right;
					pred.right = null;
				}
			}
		}
		return head;
	}

	private boolean compare(TreeNode head1, TreeNode head2) {
		TreeNode temp1 = head1, temp2 = head2;
		while(temp1 != null && temp2 != null) {
			System.out.println(temp1.val + " " + temp2.val);
			// if(temp1.val != temp2.val) {
			// 	return false;
			// }
			temp1 = temp1.right;
			temp2 = temp2.right;
		}
		return temp1 == null && temp2 == null;
	}
}
