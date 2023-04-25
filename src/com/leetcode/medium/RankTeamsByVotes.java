package com.leetcode.medium;
/**
 * @author nayanava
 */

import java.io.*;
import java.util.Arrays;

public class RankTeamsByVotes {
    public String rankTeams(String[] votes) {
        int possibleRanks = 26, numberOfTeams = 26;
        TeamVote[] teamVotes = new TeamVote[numberOfTeams];

        for(int i = 0; i < numberOfTeams; i++) {
            teamVotes[i] = new TeamVote((char)('A' + i), possibleRanks + 1);
        }

        for(String vote : votes) {
            int rank = 1;
            for(char c : vote.toCharArray()) {
                teamVotes[c-'A'].votesForRank[rank++]++;
            }
        }
        Arrays.sort(teamVotes, (team1, team2) -> {
            for(int i = 1; i < possibleRanks; i++) {
                if(team1.votesForRank[i] != team2.votesForRank[i]) {
                    return team2.votesForRank[i] - team1.votesForRank[i];
                }
            }
            return team1.name - team2.name;
        });
        StringBuilder sb = new StringBuilder("");
        for(TeamVote teamVote : teamVotes) {
            sb.append(teamVote.name);
        }
        return sb.toString();
    }

    private class TeamVote {
        char name;
        int[] votesForRank;

        TeamVote(char name, int possibleRanks) {
            this.name = name;
            votesForRank = new int[possibleRanks];
        }
    }

    public static void main(String[] args) {
        System.out.println(new RankTeamsByVotes().rankTeams(new String[]{"WXYZ","XYZW"}));
    }
}
