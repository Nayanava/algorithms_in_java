package com.leetcode.hard;

public class StringReverse {

    public String reverseWords(String s) {
        s = s.trim();
        char[] chars = s.toCharArray();
        reverseString(chars, 0, chars.length - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < chars.length; i++) {
            while(i < chars.length && chars[i] == ' '){
                i++;
                continue;
            }
            int begin = i;
            while(i < chars.length && chars[i] != ' ') {
                i++;
            }
            reverseString(chars, begin, i-1);
            stringBuilder.append(chars, begin, i-begin);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString().trim();
    }

    private void reverseString(char[] arr, int begin, int end) {
        while(begin <= end) {
            char temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;
            begin++;
            end --;
        }
    }

    public static void main(String[] args) {
        StringReverse stringReverse = new StringReverse();

        System.out.println(stringReverse.reverseWords("a good   example"));
    }
}
