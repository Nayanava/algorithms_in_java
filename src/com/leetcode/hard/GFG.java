package com.leetcode.hard;
// Java implementation to Divide  
// two integers without using  
// multiplication, division  
// and mod operator  

// Function to divide a by b
// and return floor value it  
class GFG {
    public static int divide(long dividend,
                              long divisor) {

        long sign = ((dividend < 0) ^
                (divisor < 0)) ? -1 : 1;

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        long quotient = 0, temp = 0;

        for (int i = 31; i >= 0; --i) {

            if (temp + (divisor << i) <= dividend) {
                temp += divisor << i;
                quotient |= 1L << i;
            }
        }

        return (int)(sign * quotient);
    }

    // Driver code
    public static void main(String args[]) {
        int a = 10, b = 3;
        System.out.println(divide(a, b));

        int a1 = 43, b1 = -8;
        System.out.println(divide(a1, b1));


    }
}