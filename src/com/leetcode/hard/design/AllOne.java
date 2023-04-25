package com.leetcode.hard.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class AllOne {

    Map<String, Integer> map = new HashMap<>();
    Map<Integer, Node> nodeMap = new HashMap<>();
    Node head, tail;
    /** Initialize your data structure here. */
    public AllOne() {

    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        //step 1. put the key into the map.
        map.put(key, map.getOrDefault(key, 0) + 1);
        //find the current frequency
        int freq = map.get(key);
        Node node = nodeMap.get(freq);
        if(null == node) {
            node = addNodeAfter(nodeMap.get(freq-1));
            nodeMap.put(freq, node);
        }
        node.keys.add(key);
        if(freq != 1) {
            nodeMap.get(freq-1).keys.remove(key);
            if(nodeMap.get(freq-1).keys.isEmpty()) {
                removeNode(nodeMap.get(freq-1));
                nodeMap.remove(freq-1);
            }
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        // find currentBucket of node.
        map.put(key, map.getOrDefault(key, 0) - 1);
        int freq = map.get(key);
        if(freq != 0) {
            Node node = nodeMap.get(freq);
            if(null == node) {
                node = addNodeBefore(nodeMap.get(freq+1));
            }
            node.keys.add(key);
        }
        nodeMap.get(freq+1).keys.remove(key);
        if(nodeMap.get(freq+1).keys.isEmpty()) {
            removeNode(nodeMap.get(freq+1));
            nodeMap.remove(freq+1);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.keys.iterator().next();
    }

    private void removeNode(Node node) {
        Node next = node.next;
        Node prev = node.prev;

        if(head == tail) {
            head = tail = null;
            node.next = node.prev = null;
            return;
        }
        if(prev == null) {
            head = node.next;
            node.next.prev = null;
        } else if(next == null) {
            tail = node.prev;
            node.prev.next = null;
        } else {
            prev.next = next;
            next.prev = prev;
        }
        node.prev = node.next = null;
    }

    private Node addNodeAfter(Node node) {
        //add a node after the current node
        Node newNode = new Node();
        if(null == head && null == tail) {
            head = tail = newNode;
            return newNode;
        }
        if(null == node) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return head;
        }
        newNode.next = node.next;
        node.next = newNode;
        newNode.prev = node;
        if(newNode.next != null) {
            newNode.next.prev = newNode;
        } else {
            tail = newNode;
        }
        return newNode;
    }

    private Node addNodeBefore(Node node) {
        Node prev = node.prev;
        Node newNode = new Node();
        newNode.next = node;
        node.prev = newNode;
        if(prev == null) {
            head = newNode;
            return newNode;
        } else {
            prev.next = newNode;
            newNode.prev = prev;
        }

        return newNode;
    }
    class Node {
        Node prev, next;
        Set<String> keys;

        Node() {
            keys = new LinkedHashSet<>();
        }
    }

    public static void main(String[] args) {
        AllOne ao = new AllOne();
        ao.inc("2");
        ao.inc("2");
        ao.inc("1");
        System.out.print(ao.getMaxKey() + " " + ao.getMinKey());
        ao.dec("2");
        ao.dec("2");
        System.out.print(ao.getMaxKey() + " " + ao.getMinKey());
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */