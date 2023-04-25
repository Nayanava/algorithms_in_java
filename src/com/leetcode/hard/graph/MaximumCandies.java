/*
import java.util.ArrayList;

class MaximumCandies {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        List<Integer> list = new ArrayList<>()
        Map<Character, Integer> map = new HashMap<>();
        Map<String, Integer> res = new HashMap<>();
        int max = 0;
        int end = 0, begin = 0;
        while (end < s.length()) {

            if (end - begin + 1 == minSize) {
                System.out.println(end + " " + begin);
                map.put(s.charAt(begin), map.get(s.charAt(begin)) - 1);
                if (map.get(s.charAt(begin)) == 0)
                    map.remove(s.charAt(begin++));
            }
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end++), 0) + 1);
            if (end - begin + 1 == minSize && map.size() <= maxLetters) {
                String substr = s.substring(begin, minSize);
                res.put(substr, res.getOrDefault(substr, 0) + 1);
                //System.out.println(substr);
                max = Math.max(max, res.get(substr));
            }
        }
        return max;
    }
}*/
