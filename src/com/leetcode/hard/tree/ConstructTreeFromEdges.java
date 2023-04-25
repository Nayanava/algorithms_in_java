package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

import java.util.*;

public class ConstructTreeFromEdges {
    public TreeNode constructTree(char[][] edges) {
        List<Integer>[] adj = new List[26];
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);
        for(int i = 0; i < 26; i++) {
            adj[i] = new ArrayList<>();
        }

        for(char[] edge :edges) {
            int u =edge[0] - 'a', v = edge[1] - 'a';
            adj[u].add(v);
            indegree[v] += (indegree[v] == -1) ? 2 : 1;
            if(indegree[u] == -1) {
                indegree[u] = 0;
            }
        }
        int rootVal = -1;
        for(int i = 0; i < 26; i++) {
            if(indegree[i] == 0) {
                rootVal = i;
                break;
            }
        }
        TreeNode root = new TreeNode((char)(rootVal + 'a'));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            int nodeVal = node.val - 'a';
            if(adj[nodeVal].size() > 0) {
                node.left = new TreeNode((char)(adj[nodeVal].get(0) + 'a'));
                q.offer(node.left);
            }
            if(adj[nodeVal].size() > 1) {
                node.right = new TreeNode((char)(adj[nodeVal].get(1) + 'a'));
                q.offer(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        char[][] edges = {{'a','b'}, {'a','d'}, {'b','c'}};
        ConstructTreeFromEdges ctfe = new ConstructTreeFromEdges();
        TreeNode root = ctfe.constructTree(edges);
        preorder(root);
    }
    public static void preorder(TreeNode root) {
        if(null == root) {
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);

    }
}
