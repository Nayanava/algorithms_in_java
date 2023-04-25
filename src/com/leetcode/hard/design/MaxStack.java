package com.leetcode.hard.design;

import java.util.LinkedList;
import java.util.TreeMap;

class MaxStack {
	TreeMap<Integer, LinkedList<ListNode>> maxStack;
	ListNode head;
	public MaxStack() {
		maxStack = new TreeMap<>();
	}

	public void push(int x) {
		ListNode newNode = addFirst(x);
		maxStack.computeIfAbsent(x, k -> new LinkedList<>()).add(newNode);
	}

	public int pop() {
		int x = head.val;
		removeNode(head);
		maxStack.get(x).removeLast();
		if(maxStack.get(x).isEmpty()) {
			maxStack.remove(x);
		}
		return x;
	}

	public int top() {
		return head.val;
	}

	public int peekMax() {
		return maxStack.lastKey();
	}

	public int popMax() {
		int max = maxStack.lastKey();
		ListNode node = maxStack.get(max).removeLast();
		removeNode(node);
		if(maxStack.get(max).isEmpty()) {
			maxStack.remove(max);
		}
		return max;
	}
	private ListNode addFirst(int val) {
		ListNode node = new ListNode(val);
		node.next = head;
		if(head != null) {
			head.prev = node;
		}
		head = node;
		return node;
	}

	private void removeNode(ListNode node) {
		ListNode left = node.prev;
		ListNode right = node.next;
		if(left == null && right == null) {
			head = null;
			return;
		}
		if(left == null) {
			head = right;
			head.prev = null;
		}
		else{
			if(left != null) {
				left.next = right;
			}
			if(right != null) {
				right.prev = left;
			}
		}
	}
	class ListNode {
		ListNode prev, next;
		int val;

		ListNode(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		MaxStack ms = new MaxStack();
		ms.push(5);
//		ms.push(1);
//		ms.push(5);
//		System.out.println(ms.top());
//		System.out.println(ms.popMax());
//		System.out.println(ms.top());
		System.out.println(ms.peekMax());
		System.out.println(ms.popMax());
//		System.out.println(ms.pop());
//		System.out.println(ms.top());
	}
}
