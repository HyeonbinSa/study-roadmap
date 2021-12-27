package com.benworld.algorithm;

import java.util.*;

public class DFSStudy {
    private static HashMap<String, ArrayList<String>> graph = new HashMap<>();

    public DFSStudy() {
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

    public Queue<String> dfs(String start) {
        Queue<String> visited = new LinkedList<>();
        Stack<String> needVisit = new Stack<>();

        needVisit.add(start);
        while(!needVisit.isEmpty()){
            String node = needVisit.pop();
            if(!visited.contains(node)){
                visited.add(node);
                needVisit.addAll(graph.get(node));
            }
        }
        return visited;
    }
}
