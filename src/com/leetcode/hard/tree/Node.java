package com.leetcode.hard.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class HeightNAry {
    public int maxDepth(Node root) {
        if(root == null) {
            return 0;
        }

        int height = 1;
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size != 0) {
                Node temp = ((LinkedList<Node>) queue).pop();
                for(Node it : temp.children) {
                    queue.add(it);
                }
            }
            if(size == 0) {
                height++;
            }

        }

        return height;
    }

    public int maxDepth1(Node root) {
        if( root == null) {
            return 0;
        }
        int height = 0;
        for(Node it : root.children) {
            height = Math.max(height, maxDepth(it));
        }

        return height + 1;
    }
}