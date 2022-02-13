## String 사용
---

### 1. split()
- 특정 조건이나 문자를 기준으로 문자열을 자르는 메소드
- 기본 사용
    - ex) `split(",");`
- 구분자 여러개 (파이프라인"|"을 사용)
    - ex) `split(",|:");`
- 정규식을 이용한 방법
    - ex) `split("[a-z]");`

### 2. startWith(), endWith()
- 대상 문자열이 특정 문자 or 특정 문자열로 시작하거나 끝나는지 확인하는 메서드
- startsWith(String prefix)
    - 반환 Type : boolean
    ```java
    String str = "Hello World";
    System.out.println(str.startsWith("Hello"));
    // 출력 값 : true
    ```
- endsWith(String suffix)
    - 반환 Type : boolean
    ```java
    String str = "Hello World";
    System.out.println(str.endsWith("rld"));
    // 출력 값 : true
    ```

### 3. substring()
- 문자열의 index를 기준으로 문자열을 자르는 메서드
- substring(int start, int end);
    - start 번째 문자부터 end **전의** 문자까지 자르기


### 4. indexOf()
- 검색하고자 하는 문자가 몇번째에 위치한지 위치를 반환한다.
- indexOf("a")
    - 주어진 문자열에서 "a"의 위치를 반환