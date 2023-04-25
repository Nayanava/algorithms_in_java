package com.leetcode.hard.dynamic.programming;

public class FindFarthestBuildingBricksAndRopes {
    public int findFarthestDistance(int[] arr, int bricks, int ropes) {
        int maxDistance = dfs(arr, bricks, ropes, 0);
        return Math.min(arr.length, maxDistance);
    }

    private int dfs(int[] arr, int b, int r, int index) {
        if (index == arr.length-1) {
            return index;
        }
        if(b == 0 && r == 0) {
        	return arr[index] >= arr[index+1] ? dfs(arr, b, r, index+1) : index;
		}
        if (r < 0) {
            return index-1;
        }
        if (arr[index] >= arr[index+1]) {
            return dfs(arr, b, r, index + 1);
        }
        int height = arr[index+1] - arr[index];
        if (height > b) {
            return dfs(arr, b, r - 1, index + 1);
        }
        return Math.max(dfs(arr, b - height, r, index + 1), dfs(arr, b, r - 1, index + 1));
    }

    public static void main(String[] args) {
    	int[] height = {4,2,7,6,9,11,14,12,8};
    	int b = 0, r = 1;
    	FindFarthestBuildingBricksAndRopes ffbbr = new FindFarthestBuildingBricksAndRopes();
    	System.out.println(ffbbr.findFarthestDistance(height, b, r));
	}
}
