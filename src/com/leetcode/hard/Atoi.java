package com.leetcode.hard;


import java.util.*;
import java.util.stream.Collectors;

public class Atoi {

    public int myAtoi(String str) {
        return myAtoiUtil(str);
    }

    private Integer myAtoiUtil(String str) {
        Set<Character> initialStates = new HashSet<>(
                Arrays.asList(new Character[]{'+', '-'}));
        boolean isNaturalNumberEncountered = false;
        str = str.trim();
        if(str.isEmpty()) {
            return 0;
        }
        //checking initial state
        if(!isCharADigit(str.charAt(0)) && !initialStates.contains(str.charAt(0)) ){
            return 0;
        }

        StringBuilder resultString = new  StringBuilder("");

        if(initialStates.contains(str.charAt(0))) {
            resultString.append(String.valueOf(str.charAt(0)));
        }


        if(str.charAt(0) != '0' && !initialStates.contains(str.charAt(0))) {
            resultString.append(str.charAt(0));
            isNaturalNumberEncountered = true;
        }

        for(int i = 1; i < str.length(); i++) {
            if(!isCharADigit(str.charAt(i))) {
                break;
            }
            if(!isNaturalNumberEncountered && str.charAt(i) == '0') {
                continue;
            }

            isNaturalNumberEncountered = true;
            resultString.append(str.charAt(i));
        }

        String result = resultString.toString();

        String maxValue = String.valueOf(Integer.MAX_VALUE);
        String minValue = String.valueOf(Integer.MIN_VALUE);

        if(result.isEmpty() || !isCharADigit(result.charAt(result.length() - 1))) {
            return 0;
        }


        if(result.startsWith("-")) {
            if(result.length() < minValue.length()) {
                return Integer.parseInt(result);
            }
            if(result.length() > maxValue.length()) {
                return Integer.MIN_VALUE;
            }
            return compare(result.substring(1), minValue.substring(1)) == 0 ? Integer.parseInt(result) : Integer.MIN_VALUE;
        }

        if(result.startsWith("+")) {
            if(result.length() > maxValue.length() + 1) {
                return Integer.MAX_VALUE;
            }

            if(result.length() < maxValue.length()) {
                return Integer.parseInt(result);
            }

            return compare(result.substring(1), maxValue) == 0 ? Integer.parseInt(result) : Integer.MAX_VALUE;
        }

        if(result.length() < maxValue.length()) {
            return Integer.parseInt(result);
        }

        if(result.length() > maxValue.length()) {
            return Integer.MAX_VALUE;
        }
        return compare(result, maxValue) == 0 ? Integer.parseInt(result) : Integer.MAX_VALUE;

    }

    private int compare(String a, String b) {
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) > b.charAt(i)) {
                return 1;
            }

            if(b.charAt(i) > a.charAt(i)) {
                return 0;
            }
        }
        return 0;
    }

    private boolean isCharADigit(char c) {
        return c >= 48 && c <= 57;
    }



    public int maxPoints(int[][] points) {

        if(points.length < 3) {
            return points.length;
        }

        int maxCount = 1;
        for( int i = 0; i < points.length - 1; i++) {
            int duplicates = 0;
            int pointsOnHorizontalLine = 0;
            Map<Double, Integer> slopeCount = new HashMap<>();
            for(int j = i + 1; j < points.length; j++) {
                Double slope;
                double numerator = (points[j][0] - points[i][0]);
                double denominator  = (points[j][1] - points[i][1]);

                if( points[j][0] == points[i][0] && points[j][1] == points[i][1]) {
                    duplicates++;
                    continue;
                }
                else if(denominator == 0) {
                    pointsOnHorizontalLine++;
                    continue;
                }

                else {

                    slope = numerator/denominator;

                    slope = slope == -0.0 ? 0.0 : slope;
                }
                if(slopeCount.containsKey(slope)) {
                    slopeCount.put(slope, slopeCount.get(slope) + 1);

                }
                else {
                    slopeCount.put(slope, 1);
                }
            }
            List<Integer> values = slopeCount.values().stream().collect(Collectors.toList());

            pointsOnHorizontalLine += duplicates + 1;
            maxCount = Math.max(maxCount, pointsOnHorizontalLine);
            if(!values.isEmpty()) {
                maxCount = Math.max(maxCount, Collections.max(values) + duplicates + 1);
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        Atoi atoi = new Atoi();

        Map<Double, Integer> map = new HashMap<>();
        System.out.println(atoi.myAtoi("            +004500"));

        int[][] arr = {{1,1},{1,1},{2,3}};
        System.out.println(atoi.maxPoints(arr));
    }
}

