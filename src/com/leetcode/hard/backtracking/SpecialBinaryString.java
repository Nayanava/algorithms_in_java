package com.leetcode.hard.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialBinaryString {
    public String makeLargestSpecial(String S) {
        int count = 0, i = 0;
        List<String> res = new ArrayList<>();
        for (int j = 0; j < S.length(); ++j) {
            if (S.charAt(j) == '1') count++;
            else count--;
            if (count == 0) {
                res.add('1' + makeLargestSpecial(S.substring(i + 1, j)) + '0');
                i = j + 1;
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return String.join("", res);
    }

    public static void main(String[] args) {
        String bin = "110110111010001000";
        SpecialBinaryString sbs = new SpecialBinaryString();
        System.out.println(sbs.makeLargestSpecial(bin));
    }
}
