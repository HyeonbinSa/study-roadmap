##  Java Memory 구조

---

### Java Memory 기본 구조

1. Stack
2. Heap
3. Method Area
4. ...

---

### Stack - Java Memory 1

- Stack의 구조를 사용하며 push(), pop()을 이용한 자료구조를 사용하기에 LIFO(Last-In, First-Out)의 동작 방식을 사용한다.

- Stack Frame : Method의 실행 정보를 담고 있는 Frame(하나의 Method -> Stack Frame)

  - ```java
    // Stack Frame 1
    public static void main(String[] args) {
    	int num1 = 10;
    	int num2 = 20;
    	int result = sum(num1, num2);
    }
    // Stack Frame 2
    public int sum(int num1, int num2) {
    	return num1 + num2;
    }
    ```

  - Local Variables Array : 로컬 변수 저장

  - Operand Stack : 명령어의 중간 연산 결과를 일시적으로  저장하는 Stack

  - Frame Data : 메서드 관련 정보 (메서드 종료 후 돌아올 위치, 메소드 반환 값 등)

- 특징

  - Method의 매개변수도 별도의 메모리 공간을 가진 변수
  - Method 내부 Local 변수의 수명 주기는 Stack Frame 단위
  - Stack 메모리가 꽉차게 되면 StackOverFlow 발생
  - 각 스레드마다 존재하며 스레드가 시작될 때 할당

---

### Heap  - Java Memory 2

- `new` 키워드로 생성된 Instance나 문자열 상수는 Heap이라는 메모리 공간에 저장
- JVM이 데이터를 저장하기 위해 런타임 시 동적으로 할당하여 사용하는 영역
- 특징
  - 참조되지 않는 Instance나 문자열 상수는  Garbage Collector(GC)가 수거되어 <u>메모리에서 해제</u>된다.

---

### Method Area - Java Memory 3

- JVM이 시작 시 생성되며 Java Code를 실행하기 위한 주요 정보를 저장
  - Runtime Constant Pool
  - Symbolic Reference
  - Class 정보
  - Field 정보(멤버 변수)
  - Method 정보, 정적 메서드 정보
  - interface 정보
  - 생성사 정보
  - 정적 변수(static)

