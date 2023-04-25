//class Solution {
//    class PartitionSubsets {
//        public boolean canPartitionKSubsets(int[] arr, int K) {
//            int total= 0, n = arr.length;
//            for(int i = 0; i < n; i++) {
//                total += arr[i];
//            }
//            if(total % K != 0) {
//                return false;
//            }
//            int sum[] = new int[k];
//            return canPartition(arr, K, n, sum, int total, 0);
//        }
//
//        private boolean canPartition(int[] arr, int K, int n, int[] sum, int total, int start) {
//            if(n == 0) {
//                return validate(sum, total);
//            }
//            for(int i = start; i < k; i++) {
//                if(sum[i] + arr[n-1] <= total) {
//                    sum[i] += arr[n-1];
//                }
//                if(canPartition(arr, K, n-1, sum, total, (sum[i] == 0) ? i+1 : i)) {
//                    return true;
//                } else {
//                    sum[i] -= arr[n-1];
//                }
//            }
//            return false;
//        }
//
//        public boolean validate(int[] sum, int total) {
//            for(int i = 0 < i < sum.length; i++) {
//                if(sum[i] != total) {
//                    return false;
//                }
//            }
//            return true;
//        }
//    }
//
//}