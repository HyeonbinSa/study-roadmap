## BFS(Breadth First Search) - 너비 우선 탐색

---

### BFS 란

- 너비 우선 탐색 - Breadth First Search

- 정점들과 같은 Level에 있는 노드(형제 노드)를 우선으로 탐색하는 방식

  > 1. Queue를 이용하여 구현할 수 있다.

### Graph 구현 코드 

```java
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
  ... // bfs 기능 구현 코드 
}
```

### BFS 구현 코드

```java
...
public Queue<String> bfs(String start) {
    Queue<String> visited = new LinkedList<>();
    Queue<String> needVisit = new LinkedList<>(); // FIFO, 선입선출을 위해 Queue 자료구조를 이용

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
...
```

### 출력 결과

> [A, B, C, D, G, H, I, E, F, J]

### BFS 구현 방식

1. 시작이 될 노드를 입력받아 "방문해야할 노드(needVisit) - Queue"에 추가
2. 입력받은 노드와 인접하고 방문된 적(visited)이 없는 노드를 "방문해야할 노드(needVisit)"에 추가
3. 모든 노드를 방문할 때까지 반복



