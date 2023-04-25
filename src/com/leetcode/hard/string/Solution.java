package com.leetcode.hard.string;

import java.io.*;
import java.util.*;

class Solution {

	static boolean validateIP(String ip) {
		// your code goes here
    String[] ipSplit = ip.split(".");
    if(ipSplit.length != 4)
      return false;
    for(String str : ipSplit) {
      if(!isValid(str))
        return false;
    }
    return true;
	}
  
//  12..5.6
  static boolean isValid(String str) {
    int num = 0;
    for(char c : str.toCharArray()) {
      if(c < '0' || c > '9')
        return false;
      num = num*10 + (c - '0');
    }
    System.out.println(num);
    return num >= 0 && num <= 255;
  }

	public static void main(String[] args) {
    String ip = "192.168.0.1";
    validateIP(ip);
	}
}
