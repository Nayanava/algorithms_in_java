package com.leetcode.hard.graph;

import java.util.*;

public class MaximizeTurn {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            int arr[] = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = s.nextInt();
            }
            System.out.println(count(arr));
        }
    }

    private static int count(int[] arr) {
        Arrays.sort(arr);
        for(int i : arr)
            System.out.print(i + " ");
        int n = arr.length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if(a[1] != b[1])
                return a[1] - b[1];
            return a[0]-b[0];
        });
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            int count = 0;
            if(i > 0 && arr[i-1] + 1 == arr[i])
                count++;
            if(i < n-1 && arr[i+1] == arr[i] + 1) {
                count++;
            }
            q.offer(new int[] {arr[i], count});
        }

        int res = 0;
        while(!q.isEmpty()) {
            int[] node = q.poll();
            if(set.contains(node[0])) {
                set.remove(node[0]);
                res++;
                set.remove(node[0]-1);
                set.remove(node[0]+1);
            }
        }
        return res;
    }
}
