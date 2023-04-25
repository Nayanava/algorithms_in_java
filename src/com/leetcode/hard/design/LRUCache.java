package com.leetcode.hard.design;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    Map<Integer, LinkedList.Node> map;
    int capacity;
    LinkedList list = new LinkedList();
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            list.remove(map.get(key));
            list.addFirst(map.get(key));
            return map.get(key).val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)) {
            LinkedList.Node node = list.addFirst(key, value);
            map.put(key, node);
            if(map.size() > capacity) {
                LinkedList.Node delNode = list.removeLast();
                map.remove(delNode.key);
            }
        } else {
            LinkedList.Node node = map.get(key);
            node.val = value;
            list.remove(node);
            list.addFirst(node);
        }
    }
    
    class LinkedList {
        Node head, rear;
        LinkedList() {
            this.head = null;
            this.rear = null;
        }
        public Node addFirst(int key, int val) {
            Node node = new Node(key, val);
            return addFirst(node);
        }
        public Node addFirst(Node node) {
            if(head == null) {
                return head = rear = node;
            }
            node.next = head;
            head.prev = node;
            return head = node;
        }
        public Node remove(Node node) {
            if(rear == node) {
                rear = node.prev;
                if(head == node)
                    head = rear;
            } else if(head == node) {
                head = node.next;
            } else{
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            node.prev = node.next = null;
            return node;
        }
        private Node removeLast() {
            return remove(rear);
        }
        class Node {
            int key;
            int val;
            Node next, prev;
            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
//        cache.put(4, 4);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));
    }
}