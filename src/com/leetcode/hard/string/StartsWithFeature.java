package com.leetcode.hard.string;

import java.util.Map;
import java.util.TreeMap;

public class StartsWithFeature {

    public static void main(String[] args) {
        String a = "abcd";
        String b = "bcdp";
        for(int i = 0; i < a.length(); i++)
            System.out.println(b.startsWith(a.substring(i)));

        TreeMap map = new TreeMap<>();
        //b.length() -(a.length() - i)
    }
}
