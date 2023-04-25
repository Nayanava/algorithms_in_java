package com.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, Node> map;
    int cacheCapacity;
    int currentStore;
    CircularLinkedList list = new CircularLinkedList();
    public LRUCache(int capacity) {
        map = new HashMap<>();
        cacheCapacity = capacity;
        currentStore = 0;
    }

    public int get(int key) {
        Node node = map.getOrDefault(key, null);
        if(node != null) {
            list.changePlaces(node);
        }
        return node == null ? -1 : node.val;
    }

    public void put(int key, int value) {
        //if the object is present in the map.
        // we need to pull the object out of the current location and add it to the end of the list.
        //if it is not present in the map we add it to the end of the linkedlist and add it to the map
        //once we find an entry which is beyond the capacity of the cache we pull the first node and remove it from the map and the queue

        Node node = map.get(key);
        if(node != null) {
            node.val = value;
            list.changePlaces(node);
            return;
        }

        if(currentStore >= cacheCapacity) {
            Node removedNode = list.poll();
            map.remove(removedNode.key);
        }
        Node newNode = new Node(key, value);
        list.addLast(newNode);
        map.put(key, newNode);

        currentStore++;
        if(currentStore > cacheCapacity)
            currentStore = cacheCapacity;
    }

    class Node {
        int key;
        int val;
        Node left, right;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.left = this.right = this;
        }
    }

    class CircularLinkedList {
        int count;
        Node head;

        void addLast(Node newNode) {
            //the head of the linkedList is null
            // then this becomes the head
            if(this.head == null) {
                this.head = newNode;
                return;
            }
            Node tail = head.left;
            tail.right = newNode;
            newNode.left = tail;
            newNode.right = head;
            head.left = newNode;
        }

        void changePlaces(Node node) {
            deleteNode(node);
            addLast(node);
        }

        Node deleteNode(Node node) {
            //if node = head
            if(node == head) {
                return poll();

            }
            Node left = node.left;
            Node right = node.right;
            left.right = right;
            right.left = left;

            return node;
        }

        Node poll() {
            //removing the head of the linkedList
            Node temp = head;
            head = temp.right;
            head.left = temp.left;
            head.right = temp.right;
            return temp;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.print(cache.get(1) + " ");
        cache.put(3, 3);
        System.out.print(cache.get(2) + " ");
        cache.put(4,4);
        System.out.print(cache.get(1) + " ");
        System.out.print(cache.get(3) + " ");
        System.out.print(cache.get(4) + " ");
    }
}
