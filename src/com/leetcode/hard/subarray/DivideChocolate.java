package com.leetcode.hard.subarray;

public class DivideChocolate {
    public int maximizeSweetness(int[] arr, int K) {

        K = K+1;
        int n = arr.length, high = arr[0], low = 0;
        for(int i = 1; i < n; i++) {
            high += arr[i];
        }
        if(K == n-1) {
            return low;
        }
        if( K > n) {
            return -1;
        }
        int pieces = 0, sum = 0;
        while( low <= high) {
            //System.out.println(low + " " + high);
            int mid = (low + high) >>> 1;
            sum = 0;
            pieces = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] > mid) {
                    pieces = 0;
                    break;
                }
                sum += arr[i];
                if (sum > mid) {
                    pieces++;
                    sum = 0;//arr[i];
                }
            }

//            if (pieces == K) {
//                ans = mid;
//            }

            if (pieces >= K || pieces == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        sum = 0;
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            if(sum + arr[i]== low) {
                System.out.println(true);
            }
            if(sum + arr[i] > low) {

                sum = 0;
            }
            sum += arr[i];
        }
        return low;
    }

    public static void main(String[] args) {
        //int arr[] = {87002,22650,61737,4432,87341,67643,13454,83823,87836,2978,99313,82797,77350,55994,31403,73836,54451,54807,60146,72381,7271,37633,32603,33752,78004,76288,94608,3516,98287,16577,36186,40401,70733,35764,76303,74279,18351,74113,26480,64253,49402,47512,37185,42488,43068,3542,55773,91365,86770,52915};
        int arr[] = {1, 2, 10, 5, 19, 23, 63};
        int K = 3;
        DivideChocolate dc = new DivideChocolate();
        System.out.println(dc.maximizeSweetness(arr, K));
    }
}
