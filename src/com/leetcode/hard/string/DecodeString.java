package com.leetcode.hard.string;

import java.util.Stack;

class DecodeString {
	public String decodeString(String s) {
		Stack<String> st = new Stack<>();
		Stack<Integer> intStack = new Stack<>();
		for(int i = 0; i < s.length(); i++) {
			if(isNum(s.charAt(i))) {
				int num = s.charAt(i++) - '0';
				while( i < s.length() && isNum(s.charAt(i))) {
					num = num * 10 + (s.charAt(i++) - '0');
				}
				intStack.push(num);
			} else if(s.charAt(i) == ']') {
				StringBuilder sb = new StringBuilder();
				while(!st.isEmpty() && !st.peek().equals("[")) {
					sb.append(st.pop());
				}
				sb.reverse();
				int multiplier = intStack.pop();
				StringBuilder multipliedString = new StringBuilder();
				while(--multiplier > 0 ) {
					multipliedString.append(sb);
				}
				st.pop();
				st.push(multipliedString.toString());
			}
		}

		StringBuilder res = new StringBuilder();
		while(!st.isEmpty()) {
			res.append(st.pop());
		}

		return res.reverse().toString();
	}

	boolean isNum(char c) {
		return c >= '0' && c <= '9';
	}
}
