package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;
import com.sun.source.tree.Tree;

class PredAndSucc {
    public TreeNode[] findPredAndSucc(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode[0];
        }
        TreeNode temp = root;
        TreeNode pred, succ;
        pred = succ = null;
        while (temp != null && temp.val != key) {
            if (temp.val > key) {
                succ = temp;
                temp = temp.left;
            } else {
                pred = temp;
                temp = temp.right;
            }
        }
        if (temp != null) {
        	TreeNode storeTemp = temp;
            if(storeTemp.left != null) {
            	storeTemp = storeTemp.left;
            	while(storeTemp != null) {
            		pred = storeTemp;
            		storeTemp = storeTemp.right;
				}
			}
            if (temp.right != null) {
                temp = temp.right;
                while (temp != null) {
                    succ = temp;
                    temp = temp.left;
                }
            }
        }
        return new TreeNode[]{pred, succ};
    }

    public static void main(String[] args) {
    	TreeNode root = new TreeNode(7);
    	root.left = new TreeNode(3);
    	root.left.left = new TreeNode(2);
    	root.left.right = new TreeNode(5);
    	root.left.right.left = new TreeNode(4);
    	root.left.right.right = new TreeNode(6);
    	root.right = new TreeNode(9);
    	root.right.left = new TreeNode(8);
    	PredAndSucc predAndSucc = new PredAndSucc();
//    	for(int i = 1; i <= 9; i++) {
//    		TreeNode[] res = predAndSucc.findPredAndSucc(root, i);
//    		System.out.println((res[0] == null ? "empty" : res[0].val) + " " + (res[1] == null ? "empty" : res[1].val));
//		}

    	root = new TreeNode(50);
    	root.left = new TreeNode(30);
    	root.right = new TreeNode(70);
    	root.left.left = new TreeNode(20);
    	root.left.right = new TreeNode(40);
    	root.right.left = new TreeNode(60);
    	root.right.right = new TreeNode(80);
    	root.left.left.left = new TreeNode(10);
    	predAndSucc = new PredAndSucc();
		TreeNode[] res = predAndSucc.findPredAndSucc(root, 65);
		System.out.println((res[0] == null ? "empty" : res[0].val) + " " + (res[1] == null ? "empty" : res[1].val));
	}
}
