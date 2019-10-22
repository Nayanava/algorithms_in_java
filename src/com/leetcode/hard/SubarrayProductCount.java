package com.leetcode.hard;

public class SubarrayProductCount {

    public int countSubArrays(int arr[], int key) {

        int strt_ptr = 0;
        int n = arr.length;
        int prod = 1;
        int count = 0;
        for( int i = 0; i < n; i++) {
            prod *= arr[i];
            if(prod < key) {
                continue;
            }

            while(strt_ptr <= i && prod > key) {
                prod /= arr[strt_ptr];
                strt_ptr++;
            }

            if(prod == key) {
                count += 1;
                int temp = i;
                while (i < n-1 && arr[i+1] == 1) {
                    count++;
                    i++;
                }
                while(prod/arr[strt_ptr] == key) {
                    prod /= arr[strt_ptr];
                    strt_ptr++;
                    count ++;
                }
                while (prod/arr[i] == key) {
                    prod /= arr[i];
                    i--;
                    count++;
                }
                i = temp;
                strt_ptr = temp+1;
                prod = 1;
            }
        }

        return count;
    }

    public int countOne(int arr[]) {

        int count = 0;
        for( int i = 0; i < arr.length; i++){
            int ones = 0;
            if(arr[i] == 1){
                ones += 1;
               while(arr[i+1] == 1){
                   i++;
                   ones++;
               }
            }
            count += ones * (ones + 1) / 2;
        }
        return count;
    }
    public static void main (String[] args)
    {
        int arr[] = new int[]{ 2, 1, 1, 1, 3, 1, 1, 4};
        int k = 1;

        if (k == 1) {
            System.out.println(new SubarrayProductCount().countOne(arr));
        }
        else {
            System.out.println(new SubarrayProductCount().countSubArrays(arr, k));
        }
    }
}
