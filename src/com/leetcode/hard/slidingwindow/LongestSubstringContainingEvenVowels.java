package com.leetcode.hard.slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringContainingEvenVowels {
    public int findTheLongestSubstring(String s) {
        //count the number of vowels in the entire string
        //keep inserting until one of the letters can't made even
        //once we reach that stage we remove characters from the substring which are odd.
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e','i','o','u'));
        int map[] = new int[26];
        int window[] = new int[26];
        for(char c : s.toCharArray()) {
            map[c-'a']++;
        }
        int start = 0, res = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            window[c-'a']++;
            map[c-'a']--;
            if(set.contains(c)) {
                if ((map[c - 'a'] + window[c - 'a']) % 2 == 1 && map[c - 'a'] == 0) {
                    window[c-'a']--;
                    while (window['a' - 'a'] % 2 != 0 || window['e' - 'a'] % 2 != 0 || window['i' - 'a'] % 2 != 0 || window['o' - 'a'] % 2 != 0 || window['u' - 'a'] % 2 != 0) {
                        window[s.charAt(start++) - 'a']--;
                    }
                    res = Math.max(res, i - start);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "qnplnlarrtztkotazhufrsfczrzibvccaoayyihidztfljcffiqfviuwjowkppdajmknzgidixqgtnahamebxfowqvnrhuzwqohquamvszkvunbxjegbjccjjxfnsiearbsgsofywtqbmgldgsvnsgpdvmjqpaktmjafgkzszekngivdmrlvrpyrhcxbceffrgiyktqilkkdjhtywpesrydkbncmzeekdtszmcsrhsciljsrdoidzbjatvacndzbghzsnfdofvhfxdnmzrjriwpkdgukbaazjxtkomkmccktodig";
        LongestSubstringContainingEvenVowels ls = new LongestSubstringContainingEvenVowels();
        System.out.println(ls.findTheLongestSubstring(str));
    }
}
