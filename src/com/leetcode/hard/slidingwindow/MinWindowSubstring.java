package com.leetcode.hard.slidingwindow;

public class MinWindowSubstring {
    public String minWindow(String s, String t) {
      int totalCount = t.length();
      int map[] = new int[128];
      int start = 0, res = s.length()+1;
      int resStart = -1;
      for(char c : t.toCharArray()) {
        map[c]++;
      }
      
      for(int i = 0; i < s.length(); i++) {
        if(map[s.charAt(i)]-- > 0) {
          totalCount--;
        }
        while(totalCount == 0) {
          if(res > i-start+1) {
            res = i-start+1;
            resStart = start;
          }
          if(++map[s.charAt(start++)] > 0) {
            totalCount++;
          }
        }
      }
      if(resStart == -1) {
        return "";
      }
      return s.substring(resStart, resStart + res);
    }
}
