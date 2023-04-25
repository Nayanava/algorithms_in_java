package com.leetcode.hard.subarray;

public class RangeSumQuery {
        int nums[];
        SegmentTree st;
        public RangeSumQuery(int[] nums) {
            int n = nums.length;
            this.nums = nums;
            st = new SegmentTree(n);
            st.buildTree(nums, 0, 0, nums.length - 1);
        }

        public void update(int i, int val) {
            int diff = val - nums[i];
            nums[i] = val;
            st.updateTree(i, diff, 0, nums.length - 1, 0);
        }

        public int sumRange(int i, int j) {
            if( i < 0 || j >= nums.length)
                return 0;
            return st.rangeSumQuery(i, j, 0, nums.length - 1, 0);
        }

        class SegmentTree {
            int segmentTree[];
            SegmentTree(int n) {
                int power = (int)(Math.log(n+1) / Math.log(2)) + 1;
                int size = (int)(Math.pow(2, power)) - 1;
                segmentTree = new int[size];
            }

            public void buildTree(int[] nums, int pos, int low, int high ) {
                if(low == high) {
                    segmentTree[pos] = nums[low];
                    return;
                }

                int mid = (low + high) >>> 1;
                buildTree(nums, 2*pos+1, low, mid);
                buildTree(nums, 2*pos+2, mid+1, high);

                segmentTree[pos] = segmentTree[2*pos+1] + segmentTree[2*pos+2];
            }

            public int rangeSumQuery(int qLow, int qHigh, int low, int high, int pos) {
                if(qLow <= low && qHigh >= high)
                    return segmentTree[pos];
                if(qLow > high || qHigh < low)
                    return 0;

                if(low >= high)
                    return 0;

                int mid = (low + high) >>> 1;
                return rangeSumQuery(qLow, qHigh, low, mid, 2*pos+1)
                        + rangeSumQuery(qLow, qHigh, mid+1, high, 2*pos + 2);
            }

            public void updateTree(int index, int diff, int low, int high, int pos) {
                if(index < low || index > high)
                    return;
                if( low == high) {
                    segmentTree[pos] += diff;
                    return;
                }

                int mid = (low + high) >>> 1;
                updateTree(index, diff, low, mid, 2*pos+1);
                updateTree(index, diff, mid+1, high, 2*pos + 2);

                segmentTree[pos] = segmentTree[2*pos+1] + segmentTree[2*pos+2];
            }
        }

    public static void main(String[] args) {
        int arr[] = {9, -8};
        RangeSumQuery rsq = new RangeSumQuery(arr);
        rsq.update(0, 3);
        System.out.print(rsq.sumRange(1, 1) + " ");
        rsq.update(1, 2);
        System.out.print(rsq.sumRange(0, 1) + " ");
        rsq.update(1, -3);
        System.out.print(rsq.sumRange(0, 1) + " ");
    }
}
