package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

public class FindMaxEvenPathSum {
    public int findMaxPath(TreeNode root) {
        findMaxEvenPath(root);
        return max;
    }

    int max = 0;

    public Data findMaxEvenPath(TreeNode root) {
        if (null == root) {
            return new Data(0, 0);
        }
        Data left = findMaxEvenPath(root.left);
        Data right = findMaxEvenPath(root.right);
        int tempMax = 0;
        if (root.val % 2 != 0) {
            if (left.odd == 0 && right.odd == 0) {
                tempMax = left.even + right.even;
            } else if (left.odd == 0) {
                tempMax = Math.max(right.odd + root.val, left.even + right.even);
            } else if (right.odd == 0) {
                tempMax = Math.max(left.odd + root.val, left.even + right.even);
            } else {
                tempMax = max(left.odd + right.odd, left.even + right.odd + root.val, left.odd + right.even + root.val, left.even + right.even);
            }
        } else {
            if(left.odd == 0 && right.odd == 0) {
                tempMax = Math.max(root.val, left.even + right.even + root.val);
            } else if(left.odd == 0 || right.odd == 0) {
                tempMax = left.even + right.even + root.val;
            } else {
                tempMax = max(left.odd + right.odd + root.val, left.even + right.even + root.val, left.odd + right.odd, left.even + right.even);
            }
        }
        max = Math.max(tempMax, max);
        if (root.val % 2 == 0) {
            if(left.odd == 0 && right.odd == 0) {
                return new Data(0, Math.max(left.even, right.even) + root.val);
            }
            return new Data(max(left.odd, right.odd) + root.val, max(left.even, right.even) + root.val);
        }
        int oddMax = 0, evenMax = 0;
        if(left.odd == 0 && right.odd == 0){
           oddMax = root.val;
           evenMax = Math.max(left.even, right.even);
        } else {
            oddMax = Math.max(left.even, right.even) + root.val;
            evenMax = Math.max(left.odd, right.odd) + root.val;
        }
        return new Data(oddMax, evenMax);
    }

    private int max(int... arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }


    class Data {
        int odd, even;

        Data(int odd, int even) {
            this.odd = odd;
            this.even = even;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(101);
        root.right.right = new TreeNode(13);

        FindMaxEvenPathSum fmeps = new FindMaxEvenPathSum();
        System.out.println(fmeps.findMaxPath(root));
    }
}
