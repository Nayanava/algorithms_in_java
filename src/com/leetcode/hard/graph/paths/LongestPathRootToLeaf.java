package com.leetcode.hard.graph.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LongestPathRootToLeaf {
    static class Node {
        char id;
        List<Link> links;
        Node(char id) {
            this.id = id;
            links = new ArrayList<>();
        }
    }

    static class Link {
        int bytesPerSec;
        Node dest;

        Link(int bps, Node newNode) {
            bytesPerSec= bps;
            dest = newNode;
        }
    }

    static class NodeInfo {
        Node node;
        int index;
        NodeInfo(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    private static int maxPathRootToLeaf(Node root) {
        if(null == root.links || root.links.isEmpty()) {
            return 0;
        }
        int max = 0;
        for(Link link : root.links) {
            max = Math.max(max, maxPathRootToLeaf(link.dest) + link.bytesPerSec);
        }
        return max;
    }

    private static int maxPathRootToLeafIt(Node root) {
        if(null == root.links || root.links.isEmpty()) {
            return 0;
        }
        Stack<NodeInfo> st = new Stack<>();
        st.push(new NodeInfo(root, 0));
        int sum = 0, maxSum = 0;
        while(!st.isEmpty()) {
            NodeInfo info = st.pop();
            Node currNode = info.node;
            int index = info.index;
            sum += currNode.links.get(index).bytesPerSec;
            Node next = currNode.links.get(index).dest;
            if(currNode.links.size() > index+1) {
                info.index++;
                st.push(info);
            }
            if(next.links != null && !next.links.isEmpty()) {
                st.push(new NodeInfo(next, 0));
            } else {
                maxSum = Math.max(maxSum, sum);
                sum -= currNode.links.get(index).bytesPerSec;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Node node = new Node('A');
        Node root = node;
        Link newLink = new Link(2, new Node('B'));
        node.links.add(newLink);
        newLink = new Link(1, new Node('C'));
        node.links.add(newLink);
        node = node.links.get(0).dest;
        Node node2 = root.links.get(1).dest;
        newLink = new Link(3, new Node('D'));
        node.links.add(newLink);
        newLink = new Link(1, new Node('E'));
        node2.links.add(newLink);
        newLink = new Link(2, new Node('F'));
        node2.links.add(newLink);
        newLink = new Link(5, new Node('G'));
        node2.links.add(newLink);

        System.out.println(maxPathRootToLeaf(root));
        System.out.println(maxPathRootToLeafIt(root));
    }
}
