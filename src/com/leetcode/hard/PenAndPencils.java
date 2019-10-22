package com.leetcode.hard;

import java.util.Scanner;

public class PenAndPencils {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int numberOfRows = myScanner.nextInt();
        int arr[];
        for(int i = 1; i <= numberOfRows; i++) {
            arr = new int[5];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = myScanner.nextInt();
            }
            int minPens = arr[0] % arr[2] == 0 ? arr[0]/arr[2] : arr[0]/arr[2] + 1;
            int minPencils = arr[1] % arr[3] == 0 ? arr[1]/arr[3] : arr[1]/arr[3] + 1;

            if( minPencils + minPens > arr[arr.length - 1])
                System.out.println(-1);
            else {
                System.out.println(minPens + " " + minPencils);
            }
        }
    }


}
