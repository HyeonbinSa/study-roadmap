## DFS(Depth First Search) - 깊이 우선 탐색

---

### DFS 란

- 깊이 우선 탐색 - Depth First Search

- 정점의 자식을 우선으로 탐색하는 방식

- Tree 또는 Graph의 데이터 구조를 Backtracking을 이용하여 탐색하는 알고리즘 원리

  > 1. Stack을 사용하여 구현할 수 있다.
  > 2. 재귀를 이용하여 구현할 수 있다.

### Graph 구현 코드

```java
public class DFSStudy {
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

### DFS 구현 코드

```java
...
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
...
```

### 출력 결과

> [A, C, I, J, H, G, B, D, F, E]

### DFS 구현 방식

1. 시작이 될 노드를 입력받아 "방문해야할 노드(needVisit) - Stack"에 추가
2. 입력받은 노드와 인접하고 방문된 적(visited)이 없는 노드를 "방문해야할 노드(needVisit)"에 추가
3. 모든 노드를 방문할 때까지 반복

