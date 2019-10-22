package com.leetcode.hard;

import java.util.*;

public class HardNumber {

    public static void main(String[] args) {
        Test solution = new Test();
        System.out.println(solution.isNumber(" 005047e+6"));
    }

static class Test {

    private final String NUM = "number";
    private final String NUM_PRIME = "number_prime";
    private final String DOT = ".";
    public boolean isNumber(String s) {
        Map<String, List<String>> dfa = new HashMap<>();
        Set<String> initialStates = new HashSet<>(Arrays.asList(new String[]{DOT, "-", NUM}));
        Set<String> finalStates = new HashSet<>(Arrays.asList(new String[]{NUM, NUM_PRIME}));
        dfa(dfa);

        return isNumberUtil(s, dfa, initialStates, finalStates);
    }

    private void dfa(Map<String, List<String>> dfa) {

        dfa.put(NUM, Arrays.asList(new String[]{NUM, DOT, "e"}));
        dfa.put(NUM_PRIME, Arrays.asList(new String[]{NUM_PRIME, "-", "+"}));
        dfa.put("+", Arrays.asList(new String[]{NUM, NUM_PRIME, DOT}));
        dfa.put("-", Arrays.asList(new String[]{NUM, NUM_PRIME, DOT}));
        dfa.put(DOT, Arrays.asList(new String[]{NUM, "e"}));
        dfa.put("e", Arrays.asList(new String[]{"-", "+", NUM_PRIME}));

    }

    private boolean isNumberUtil(String s,
                                 Map<String, List<String>> dfa,
                                 Set<String> initialStates,
                                 Set<String> finalStates) {

        String currentState = null;
        String prevState = null;
        boolean encounteredExp = false;
        boolean encounteredDot = false;
        boolean encounteredNumber = false;
        s = s.trim();
        if(s.isEmpty()) {
            return false;
        }

        String[] splitStr = s.split("");

        if (!isCharANumber(splitStr[0].toCharArray()[0])) {
            if (!initialStates.contains(splitStr[0]))
                return false;
        }

        for (String str : splitStr) {
            if (isCharANumber(str.toCharArray()[0])) {
                currentState = encounteredExp ? NUM_PRIME : NUM;
                encounteredNumber = true;
            }
            else {
                currentState = str;
            }
            if(currentState.equals(DOT)) {
                if(encounteredDot) {
                    return false;
                }
                encounteredDot = true;
            }
            if (!dfa.containsKey(currentState)) {
                return false;
            }

            if (currentState.equals("e")) {
                encounteredExp = true;
            }
            if (prevState != null) {
                if (prevState.equals(DOT) && currentState.equals("e") && !encounteredNumber) {
                    return false;
                }
                if (!isAllowedTransition(dfa, prevState, currentState)) {
                    return false;
                }
            }
            prevState = currentState;
        }

        return finalStates.contains(currentState) || currentState.equals(DOT) && encounteredNumber;
    }

    private boolean isCharANumber(char c) {
        return c >= 48 && c <= 57;
    }

    private boolean isAllowedTransition(Map<String, List<String>> dfa,
                                        String prevState,
                                        String currentState) {
        if (dfa.containsKey(prevState)) {
            List<String> permissibleStates = dfa.get(prevState);
            return permissibleStates.contains(currentState);
        }
        return false;
    }

}
}