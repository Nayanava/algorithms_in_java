package com.leetcode.hard.subarray;

import java.util.*;

class AnalyseWebsiteVisit {
    Map<String, Integer> threeSeq = new HashMap<>();
    int maxCount;
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Visit>> map = new HashMap<>();
        for(int i = 0; i < username.length; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Visit(timestamp[i], website[i]));
        }
        
        for(List<Visit> visit : map.values()) {
            if(visit.size() < 3)
                continue;
            Collections.sort(visit, (a, b) -> a.timestamp - b.timestamp);
            dfs(visit, 3, "", 0);
        }
        
        List<String> websites = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : threeSeq.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            if(entry.getValue() == maxCount) {
                websites.add(entry.getKey());
                // System.out.println(entry.getKey() + " " + maxCount);
            }
        }
        Collections.sort(websites);
        return Arrays.asList(websites.get(0).split("_"));
    }
    
    private void dfs(List<Visit> visits, int k, String key, int start) {
        if(k == 0) {
            threeSeq.put(key, threeSeq.getOrDefault(key, 0) + 1);
            maxCount = Math.max(maxCount, threeSeq.get(key));
            return;
        }
        for(int i = start; i < visits.size(); i++) {
            dfs(visits, k-1, key == "" ? visits.get(i).website : key + "_" + visits.get(i).website, i+1);
        }
    }
    
    class Visit {
        int timestamp;
        String website;
        Visit(int time, String site) {
            this.timestamp = time;
            this.website = site;
        }
    }

    public static void main(String[] args) {
        String username[] = {"h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"};
        int timestamp[] = {527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930};
        String website[] = {"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"};

        AnalyseWebsiteVisit awv = new AnalyseWebsiteVisit();
        awv.mostVisitedPattern(username, timestamp, website);
    }
}