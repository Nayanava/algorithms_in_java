package com.spoj.number.theory;

import java.util.Scanner;

/*
        https://www.spoj.com/problems/ADDREV/
 */
public class ADDREV {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            System.out.println("");
            int num1 = s.nextInt(), num2 = s.nextInt();
            System.out.print(reverse(reverse(num1) + reverse(num2)));
        }
    }

    private static int reverse(int num) {
        int newNum = 0;
        while(num > 0) {
            newNum = newNum*10 + num%10;
            num /= 10;
        }
        return newNum;
    }

}
