package com.codeforces.div.third.r617;

import java.util.Scanner;

public class FoodBuying {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            System.out.println( (n + (n%9 == 0 ? (n/9) - 1 : n/9)) );
        }
    }
}
