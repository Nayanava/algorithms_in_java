package com.leetcode.medium;
/**
 * @author nayanava
 */

import javax.net.ssl.SSLContext;
import java.io.*;
import java.util.*;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size(), idx = 0;
        DSU dsu = new DSU(n);
        for(List<String> userAccounts : accounts) {
            for(int i = 1; i < userAccounts.size(); i++) {
                dsu.union(userAccounts.get(i), idx);
            }
            idx++;
        }
        dsu.printParent();
        Map<Integer, Set<String>> parentMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int index = dsu.find(i);
            parentMap.computeIfAbsent(index, x -> new HashSet<>());
            populateSet(parentMap.get(index), accounts.get(i));
        }
        List<List<String>> res = new LinkedList<>();
        for(Map.Entry<Integer, Set<String>> entry : parentMap.entrySet()) {
            List<String> list = new ArrayList<>();
            list.add(accounts.get(entry.getKey()).get(0));
            list.addAll(entry.getValue());
            Collections.sort(list.subList(1, list.size()));
            res.add(list);
        }

        return res;
    }

    private void populateSet(Set<String> emails, List<String> accounts) {
        emails.addAll(accounts);
        emails.remove(accounts.get(0));
    }
    class DSU {
        Map<String, Integer> emailIndex;
        int[] parent;
        DSU(int n) {
            parent = new int[n];
            emailIndex = new HashMap<>();
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        void union(String email, int index) {
            int pEmail = find(email);
            if(pEmail == -1) {
                emailIndex.put(email, index);
            } else {
                parent[pEmail] = index;
            }
        }

        int find(String email) {
            if(!emailIndex.containsKey(email)) {
                return -1;
            }
            int index = emailIndex.get(email);
            while(parent[index] != index) {
                index = parent[index];
            }
            return index;
        }
        int find(int index) {
            while(index != parent[index]) {
                index = parent[index];
            }
            return index;
        }

        void printParent() {
            for(int i = 0; i < parent.length; i++) {
                System.out.print(parent[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new LinkedList<>();
        accounts.add(Arrays.asList(new String[] {"David","David0@m.co","David1@m.co"}));
        accounts.add(Arrays.asList(new String[] {"David","David3@m.co","David4@m.co"}));
        accounts.add(Arrays.asList(new String[] {"David","David4@m.co","David5@m.co"}));
        accounts.add(Arrays.asList(new String[] {"David","David2@m.co","David3@m.co"}));
        accounts.add(Arrays.asList(new String[] {"David","David1@m.co","David2@m.co"}));
        AccountsMerge accountsMerge = new AccountsMerge();
        System.out.print(accountsMerge.accountsMerge(accounts));
    }
}
