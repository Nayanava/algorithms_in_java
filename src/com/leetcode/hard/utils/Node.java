package com.leetcode.hard.utils;

public class Node<T> {

    public int x;
    public int y;
    public int weight;
    public Node next;

    public Node(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.next = null;
    }

}
