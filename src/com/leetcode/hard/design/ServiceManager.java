package com.leetcode.hard.design;

import java.util.*;
import java.util.stream.Collectors;

public class ServiceManager {
    Set<String> services = new HashSet<>();
    Map<Long, Map<String, List<String>>> serviceDetailsByTime = new HashMap<>();

    public void addService(String service) {
        services.add(service);
    }

    public void addServiceCall(String service, long time, String payload) throws Exception {
        if (!services.contains(service)) {
            throw new Exception();
        }
        serviceDetailsByTime.putIfAbsent(time, new HashMap<>());
        serviceDetailsByTime.get(time).computeIfAbsent(service, x -> new ArrayList<>()).add(payload);
    }

    public List<String> getAllServiceCallsBetweenTimes(long time1, long time2) {
        List<String> allPayloads = new ArrayList<>();
        for (long i = time1; i <= time2; i++) {
            if (!serviceDetailsByTime.containsKey(i)) {
                continue;
            }
            allPayloads.addAll(serviceDetailsByTime.get(i).values().stream().flatMap(value -> value.stream()).collect(Collectors.toList()));
        }
        return allPayloads;
    }

    public static void main(String[] args) throws Exception {
		ServiceManager m = new ServiceManager();
		m.addService("A");
		m.addService("B");
		m.addServiceCall("A", 1, "abc");
		m.addServiceCall("A", 5, "abec");
		m.addServiceCall("B", 3, "ac");
		m.getAllServiceCallsBetweenTimes(1, 4).stream().forEach(System.out::println);
	}
}
