class NumberOfSubstrings {
    public int numberOfSubstrings(String s) {
        int[] map = new int[3];
        int start = 0, res = 0;
        int totalCount = 3, n = s.length();
        for(int i = 0; i < n; i++) {
            if(map[s.charAt(i) - 'a'] ++ == 0) {
                totalCount--;
            }
            while(start < n && totalCount == 0) {
                res += (n-i);
                if(--map[s.charAt(start++) - 'a'] == 0) {
                    totalCount += 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String a = "aaabc";
        System.out.println(new NumberOfSubstrings().numberOfSubstrings(a));
    }
}