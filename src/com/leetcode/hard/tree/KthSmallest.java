//package com.leetcode.hard.tree;
//
//import com.leetcode.hard.utils.TreeNode;
//import com.sun.source.tree.Tree;
//
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//
//public class KthSmallest {
//
//    List<Integer> list = new LinkedList<>();
//
//    public KthSmallest(TreeNode root){
//        kthSmallest(root);
//    }
//
//    private void kthSmallest(TreeNode root) {
//        if(root == null)
//            return;
//
//        kthSmallest(root.left);
//        list.add(root.val);
//        kthSmallest(root.right);
//    }
//
//    //insert into the tree
//    private void insert(TreeNode newNode) {
//        int k = Collections.binarySearch(list, newNode.val);
//        if(k < 0) {
//            k = -(k+1);
//        }
//        list.add(k, newNode.val);
//    }
//
//    private void delete(TreeNode deleteNode) {
//        int k = Collections.binarySearch(list, deleteNode.val);
//        if(k<0) {
//            //node not found;
//            return;
//        }
//    }
//
//    private int searchKthSmallest(int k) {
//        list.forEach(node -> System.out.print(node.intValue() + " "));
//        System.out.println();
//        int low = 0, high = list.size()-1;
//        while(low <= high) {
//            int mid = (low + high) >>> 1;
//            if(mid < k) {
//                low = mid + 1;
//            } else if(mid > k) {
//                high = mid - 1;
//            } else
//                return mid;
//        }
//        return -1;
//    }
//
//    public static void main(String[] args) {
//        TreeNode node = new TreeNode(5);
//        node.left = new TreeNode(3);
//        node.right = new TreeNode(6);
//        node.left.left = new TreeNode(2);
//        node.left.right = new TreeNode(4);
//        node.left.left.left = new TreeNode(1);
//        KthSmallest kthSmallest = new KthSmallest(node);
//        System.out.println(kthSmallest.searchKthSmallest(3));
//
//        kthSmallest.insert(new TreeNode(7));
//        return Integer.
//    }
//}
