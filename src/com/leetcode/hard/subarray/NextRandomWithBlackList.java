package com.leetcode.hard.subarray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NextRandomWithBlackList {
    private Map<Integer, int[]> map = new HashMap<>();
    int index = 0;
    Random random;
    public NextRandomWithBlackList(int N, int[] arr) {
        random = new Random();
        if(arr.length == 0) {
            map.put(0, new int[]{0, N-1});
            return;
        }
        if(arr.length == 1) {
            if(arr[0] > 0 && arr[0] < N-1) {
                map.put(0, new int[]{0, arr[0]-1});
                map.put(1, new int[] {arr[0]+1, N-1});
                index = 2;
                return;
            }
            if(arr[0] == N-1) {
                map.put(0, new int[]{0, arr[0]-1});
                index = 1;
                return;
            }
        }
        Arrays.sort(arr);
        int start = arr[0];
        if(arr[0] > 0) {
            map.put(index, new int[]{0, arr[0]-1});
            index++;
        }
        for(int i = 1; i < arr.length; i++) {
            while(i < arr.length && arr[i-1] == arr[i]-1){
                start = arr[i];
                i++;
                continue;
            }
            if(i == arr.length) {
                break;
            }
            map.put(index, new int[]{start+1, arr[i]-1});
            index += 1;
            start = arr[i];
        }
        if(start+1 <= N-1) {
            map.put(index, new int[]{start + 1, N - 1});
            index++;
        }
    }

    public int pick() {
        int randomIndex = random.nextInt(index);
        if(map.size() == 0)
            return 0;
        int[] range = map.get(randomIndex);
        return random.nextInt(range[1] + 1 - range[0]) + range[0];
    }
    public static void main(String[] args) {
        int N = 3;
        int[] arr = {2,0};
        NextRandomWithBlackList nrb = new NextRandomWithBlackList(N, arr);
    }
}
