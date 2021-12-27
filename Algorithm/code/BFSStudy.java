package com.benworld.algorithm;

import java.util.*;

public class BFSStudy {
    private static HashMap<String, ArrayList<String>> graph = new HashMap<>();

    public BFSStudy() {
        initial();
    }

    private void initial() {
        graph.put("A", new ArrayList<>(Arrays.asList("B", "C")));
        graph.put("B", new ArrayList<>(Arrays.asList("A", "D")));
        graph.put("C", new ArrayList<>(Arrays.asList("A", "G", "H", "I")));
        graph.put("D", new ArrayList<>(Arrays.asList("B", "E", "F")));
        graph.put("E", new ArrayList<>(Arrays.asList("D")));
        graph.put("F", new ArrayList<>(Arrays.asList("D")));
        graph.put("G", new ArrayList<>(Arrays.asList("C")));
        graph.put("H", new ArrayList<>(Arrays.asList("C")));
        graph.put("I", new ArrayList<>(Arrays.asList("C", "J")));
        graph.put("J", new ArrayList<>(Arrays.asList("I")));
    }

    public Queue<String> bfs(String start) {
        Queue<String> visited = new LinkedList<>();
        Queue<String> needVisit = new LinkedList<>();

        needVisit.add(start);

        while(!needVisit.isEmpty()){
            String node = needVisit.poll();
            if(!visited.contains(node)){
                visited.add(node);
                needVisit.addAll(graph.get(node));
            }
        }
        return visited;
    }
}
