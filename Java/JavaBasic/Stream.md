## Java Stream

---

### Stream

- 다양한  데이터 소스를 표준화된 방법으로 다루기 위해 사용
- 기존 Collection은 (List, Set) / Map 이 사용 방법이 달라 표준화된 방법이라고 하기에 완벽하지 않았음.
- Java 1.8 버전부터 Collection(List, Set, Map), Array와 같은 데이터 소스로부터 Stream을 만들 수 있다.
  - Stream으로 만든 뒤 같은 작업(중간연산 -> 최종 연산)을 수행할 수 있다.
    - 중간연산은 n번 수행 가능하지만 최종연산은 1번만 수행할 수 있다.
- `스트림 생성 -> 중간 연산 -> 최종 연산` 의 순서로 수행

---

### Stream의 특징

- 원본 데이터(데이터 소스)를 <u>**읽기**</u> 만 할 뿐 변경하지 않는다.(<u>원본은 그대로 남는다.</u>)

- Iterator와 같이 <u>**일회성**</u>이며 필요 시 재생성해야한다.

  ```java
  List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
  Stream<Integer> integerStream = list.stream();
  integerStream.forEach(System.out::println);
  // integerStream.forEach(System.out::println);
  // -> "java.lang.IllegalStateException: stream has already been operated upon or closed" 에러 메세지 발생
  ```

- 최종 연산이 수행되기 전까지 중간 연산이 수행되지 않는다. - 지연된 연산

- Stream은 **내부 반복**을 통해 작업을 처리한다. 

  - 해당 메소드 내에 for문을 넣어서 코드의 간결함을 유지할 수 있다.

- Stream은 작업을 처리할 때 병렬 스트림을 지원한다.

  - `stream.parallel()` : Stream을 병렬 Stream으로 전환 
  - `stream.sequential()` : 병렬 Stream을 일반 Stream으로 전환(기본이 sequential)

- **기본형 스트림** : AutoBoxing & UnBoxing의 비효율을 제거하기 위해 제공

  - Stream<Integer> -> IntStream
  - IntStream, LongStream, DoubleStream ... 

---

### 1.  Stream 생성 

1. Collection -> Stream 

   - Collection Intreface에는 stream()이라는 메서드를 통해 Stream을 생성할  수 있다

     ```java
     List<Integer> list = Arrays.asList(1,2,3,4,5);
     Stream<Integer> intStream = list.stream(); // Collection 
     // Stream<E> stream()
     ```

2. 배열(Array) -> Stream

   - Stream.of()를 통해 배열을 Stream으로 변환할 수 있다.

     ```java
     Stream<String> strStream = Stream.of(new String[]{"a", "b", "c"});
     ```

3. 람다식 -> Stream

   - Stream.iterate(), Stream.generate()에서 람다식을 통해 Stream을 생성할 수 있다.

     ```java
     Stream<Integer> evenStream = Stream.iterate(0, n->n+2);
     Stream<Double> randomStream = Stream.generate(Math::random);
     ```

4. 난수 Stream

   - Random을 통해 난수 스트림을 생성할 수있다.

     ```java
     IntStream intStream = new Random.ints(5);
     ```

---

