## Scanner 
---
### 1. Sacnner 기본 사용법
```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in)l
```

### 2. Scanner - next() vs. nextLine()
- next()
    - 스페이스 및 공백이 입력되기 전까지의 문자열을 반환
    - `예시`
        ```java
        Scanner sc = new Scanner(System.in);
        String str1 = scanner.next();
        // Hello World 입력 시 
        System.out.println(str1);
        // 출력 값 : Hello
        ```
- nextLine()
    - Enter가 입력되기 전까지 작성한 문자열을 모두 반환
    - `예시`
        ```java
        Scanner sc = new Scanner(System.in);
        String str1 = scanner.nextLine();
        // Hello World 입력 시 
        System.out.println(str1);
        // 출력 값 : Hello World
        ```
