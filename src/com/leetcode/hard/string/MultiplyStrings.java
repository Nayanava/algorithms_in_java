//class Solution {
//    List<String>[] charArr = new List[10];
//    public List<String> letterCombinations(String s) {
//        List<String> res = new ArrayList<>();
//        if(digits.contains("0") || digits.contains("1"))
//            return res;
//        charArr[2] = Arrays.asList(new String[] {"a", "b", "c"});
//        charArr[3] = Arrays.asList(new String[] {"d", "e", "f"});
//        charArr[4] = Arrays.asList(new String[] {"g", "h", "i"});
//        charArr[5] = Arrays.asList(new String[] {"j", "k", "l"});
//        charArr[6] = Arrays.asList(new String[] {"m", "n", "o"});
//        charArr[7] = Arrays.asList(new String[] {"p", "q", "r", "s"});
//        charArr[8] = Arrays.asList(new String[] {"t", "u", "v"});
//        charArr[9] = Arrays.asList(new String[] {"w", "x", "y", "z"});
//
//        int[] digits = new int[s.length()];
//        int n = s.length();
//        int index = 0;
//        for(char c : s.toCharArray()) {
//            digits[index++] = c - '0';
//        }
//
//    }
//
//    private List<String> dnc(int[] digits, int low, int high) {
//        List<String> res = new ArrayList<>();
//        if(low > high) {
//            return res;
//        }
//        if(high == high)
//            if(low <= high) {
//                int mid = (low + high) >>> 1;
//                List<String> left = dnc(digits, low, mid);
//                List<String> right = dnc(digits, mid+1, high);
//            }
//        return res;
//    }
//}