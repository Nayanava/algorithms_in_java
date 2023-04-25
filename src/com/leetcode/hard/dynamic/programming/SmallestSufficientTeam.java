package com.leetcode.hard.dynamic.programming;

import java.util.*;

class SmallestSufficientTeam {
    List<Integer> res = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int m = req_skills.length;
        for(int i = 0; i < m; i++) {
            map.put(req_skills[i], i);
        }
        List<Integer> pplSkill = new ArrayList<>();
        for(List<String> skills : people) {
            int skillMask = 0;
            for(String skill : skills) {
                skillMask |= 1 << map.get(skill);
            }
            pplSkill.add(skillMask);
        }

        dfs(pplSkill, 0, 0, new ArrayList<>(), m);
        int[] arr = new int[res.size()];
        int index = 0;
        for(int num : res) {
            arr[index++] = num;
        }
        return arr;
    }

    private void dfs(List<Integer> pplSkill, int index, int mask, List<Integer> ppl, int m) {
        if(Integer.bitCount(mask) == m) {
            if(res.isEmpty() || res.size() > ppl.size()) {
                res = new ArrayList<>(ppl);
            }
            return;
        }
        for(int i = index; i < pplSkill.size(); i++) {
            if((mask | pplSkill.get(i)) == mask) {
                continue;
            }
            ppl.add(i);
            dfs(pplSkill, i+1, mask | pplSkill.get(i), ppl, m);
            ppl.remove(ppl.size()-1);
        }
    }

    public static void main(String[] args) {
        String[] skills = {"java","nodejs","reactjs"};
        List<List<String>> res = new ArrayList<>();
        res.add(Arrays.asList("java"));
        res.add(Arrays.asList("nodejs"));
        res.add(Arrays.asList("nodejs","reactjs"));
        SmallestSufficientTeam sst = new SmallestSufficientTeam();
        Arrays.stream(sst.smallestSufficientTeam(skills, res)).forEach(idx -> System.out.println(idx + " "));
    }

}