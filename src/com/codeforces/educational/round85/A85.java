package com.codeforces.educational.round85;

import java.util.Scanner;

public class A85 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            int play_prev = -1, clear_prev = -1;
            boolean wrong = false;
            while(n-- > 0) {
                int play_curr = s.nextInt(), clear_curr = s.nextInt();
                if(play_curr < play_prev || clear_curr < clear_prev || play_curr < clear_curr || (clear_curr - clear_prev) > (play_curr - play_prev)) {
                    wrong = true;
                }
                play_prev = play_curr;
                clear_prev = clear_curr;
            }
            System.out.println(wrong ? "NO" : "YES");
        }
    }
}
