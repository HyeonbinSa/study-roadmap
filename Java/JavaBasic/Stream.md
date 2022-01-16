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

### 2.  Stream 중간 연산 

- Stream 중간 연산은 n번 수행이 가능하며 연산 결과는 Stream이다.

  - 예) `stream.distinct().limit(5).sorted().forEach(System.out::println)`
  - `.distinct().limit(5).sorted()` : 중간 연산

- `Stream<T>  distinct()`  : 중복 제거 

  - Stream 요소의 중복된 값을 제거하는 작업을 수행

    ```java
    IntStream intStream = IntStream.of(1, 1, 1, 2, 2, 2, 3, 4, 3, 2, 5, 6);
    intStream.distinct().forEach(System.out::print);
    // 출력 : 123456
    ```

- `Stream<T> filter(Predicate<T> predicate)` : 조건에 **안** 맞는 요소 제외

  - 인자로 전달받은 조건에 부합하지 않는 값은 제거하는 작업을 수행

    ```java
    IntStream intStream = IntStream.rangeClosed(1, 10);
    intStream.filter(n -> n % 2 == 0).forEach(System.out::print); // 짝수만 filter
    // 출력 : 246810  (홀수가 제거된 결과값)
    ```

- `Stream<T> limit(long maxSize)` : Stream 일부를 잘라냄.

  - maxSize 이후의 요소를 잘라내는 작업을 수행 

    ```java
    IntStream intStream = IntStream.rangeClosed(1,10);
    intStream.limit(7).forEach(System.out::print);
    // 출력 : 1234567 
    ```

- `Stream<T> skip(long n)` : Steram 일부 건너 띔.

  - Stream의 앞에서부터 n개를 건너띄는 작업을 수행

    ```java
    IntStream intStream = IntStream.rangeClosed(1,10);
    intStream.skip(5).forEach(System.out::print);
    // 출력 : 678910
    // limit과 skip을 함께 사용할 경우 
    IntStream intStream = IntStream.rangeClosed(1,10);
    intStream.skip(3).limit(5).forEach(System.out::print);
    // 출력 : 45678
    ```

- `Stream<T> peek(Consumer<T> action)` : Stream 요소에 작업 수행

  - 최종 연산의 forEach와 동일한 기능을  수행하지만 Stream의  요소를 소비하지 않는다.(Stream<T> 반환)

  - 중간 연산 작업 결과 확인  시에  사용

    ```java
    IntStream intStream = IntStream.rangeClosed(1, 10);
    intStream.skip(3)
              .peek(num -> System.out.print(num +" "))
              .limit(5)
              .forEach(System.out::print);
    // 출력 : 4 45 56 67 78 8
    ```

- `Stream<T> sorted()` : Stream 요소 정렬 

  - 인자값이  없을  경우에는 기본 정렬

    ```java
    Stream<String> strStream = Stream.of("Z", "apple", "Google", "wed", "Downer", "cherry", "BANANA");
    strStream.sorted().forEach(str -> System.out.print(str+" "));
    // 출력 : BANANA Downer Google Z apple cherry wed 
    ```

  - `Stream<T> sorted(Comparator<T> comparator)` : 특정 정렬 기준에 따라 정렬

    ```java
    Stream<String> strStream = Stream.of("Z", "apple", "Google", "wed", "Downer", "cherry", "BANANA");   strStream.sorted(Comparator.comparing(String::length)).forEach(str -> System.out.print(str+" "));  // 길이 순 정렬  
    // 출력 : Z wed apple Google Downer cherry BANANA 
    ```

- `Stream<T> map` : Stream 요소를 변환

  - Stream<R>  map(Function<?  super T,  ? extends R> mapper) : 함수를 인자로 받아서 수행

  - Stream<T>  -> Stream<R> ,  T Type을 넣어 R  Type으로 받을 수 있다.

    ```java
    File[] fileArray = {new File("Ex1.java"), new File("Ex1")
                    , new File("Ex1.bak"), new File("Ex1.txt")};
    Stream<File> fileStream = Stream.of(fileArray);
    Stream<String> fileNameStream = fileStream.map(File::getName);
    fileNameStream.forEach(fileName -> System.out.print("[" + fileName + "] "));
    // 출력 : [Ex1.java] [Ex1] [Ex1.bak] [Ex1.txt] 
    ```

- `Stream<T> flatMap` : Stream 요소를 변환

  - String[] 의 Stream을  map()으로 변환 : <u>Stream의 Stream</u>

    ```java
    // String[]의 Stream을 map()으로 변환했을 때
    Stream<String[]> stringStream =
                Stream.of(new String[]{"aaa", "bbb", "ccc"}
                        , new String[]{"AAA", "BBB", "CCC"});
    // Stream<Stream<String>> : Stream의 Stream으로 변환이 됨.
    Stream<Stream<String>> stringStreamStream = stringStream.map(Arrays::stream);
    stringStreamStream.forEach(str -> System.out.print(str + " "));
    // 출력 : java.util.stream.ReferencePipeline$Head@1f17ae12 java.util.stream.ReferencePipeline$Head@4d405ef7
    ```

  - String[] 의 Stream을  flatMap()으로 변환 : <u>Stream</u>

    ```java
    // flatMap()으로 변환했을 때
    Stream<String> stringStreamByFlatMap = stringStream2.flatMap(Arrays::stream);
            stringStreamByFlatMap.forEach(str -> System.out.print(str + " "));
    // 출력 : aaa bbb ccc AAA BBB CCC 
    ```

---

### 3. Stream 최종  연산

- Stream 최종  연산은 중간 연산과 다르게 <u>**1번만 수행**</u>이 가능하며 최종 연산 수행 후 Stream이 닫힌다.

  - 예) `stream.distinct().limit(5).sorted().forEach(System.out::println)`
  - `.forEach(System.out::println)` : 최종 연산

- `void forEach(Consumer<? super T> action)` : 각 요소에 지정된 작업 수행

  - `void forEachOrdered(Consumer<? super T> action)` : 각 요소에 지정된 작업을  순서를 유지하며 수행 (병렬  스트림에서)

    ```java
    System.out.print("병렬 Stream forEach : ");
    IntStream.rangeClosed(1,9).parallel().forEach(number -> System.out.print(number +" "));
    System.out.println();
    System.out.print("병렬 Stream forEachOrdered : ");
    IntStream.rangeClosed(1,9).parallel().forEachOrdered(number -> System.out.print(number +" "));
    // 병렬 Stream forEach : 8 2 9 3 7 4 5 1 6         (순서 보장하지 않음.)
    // 병렬 Stream forEachOrdered : 1 2 3 4 5 6 7 8 9  (순서 보장함.)
    ```

- `long count()` : 요소의 개수 반환

- `Optional<T>  max/min(Comparator<<? super T> comparator)` : 정렬 기준에 따라  최대값 / 최솟값 반환

- `Optional<T> findAny()` : Stream 요소 아무거나 **하나**를 반환(병렬) (filter와 같이 쓰임.)

  - 결과가 <u>null</u>일 수 있기때문에 Optional<T>를 반환한다.

  - 병렬 Stream에서 스레드가 조건에 부합하는 값을 발견했을 때 반환

    ```java
    IntStream intStream2 = IntStream.rangeClosed(1, 9);
    OptionalInt optionalInt2 = intStream2.parallel().filter(number -> number >= 3).findAny();
    System.out.println("findAny() : " + optionalInt2.getAsInt());
    // 출력 : findAny() : 6 (병렬 스트림에서 사용)
    ```

  - `Optional<T> findFirst()` : Stream 요소 첫번째 **하나**를 반환 (직렬)

    ```java
    IntStream intStream = IntStream.rangeClosed(1, 9);
    OptionalInt optionalInt = intStream.filter(number -> number >= 3).findFirst();
    System.out.println("findFirst() : " + optionalInt.getAsInt());
    // 출력 : findFirst() : 3
    ```

- `boolean *Match(Predicate<T> p)` : 주어진 **조건**을 만족하는지 여부 반환

  - `boolean allMatch(Predicate<T> p)` : 모두 만족하는지

    ```java
    IntStream intStream = IntStream.rangeClosed(1, 9);
    boolean result = intStream.allMatch(number -> number > 8);
    System.out.println("allMatch : " + result);
    // 출력 : allMatch : false
    ```

  - `boolean anyMatch(Preedicate<T> p)` : 하나라도 만족하는지

    ```java
    IntStream intStream = IntStream.rangeClosed(1, 9);
    boolean result = intStream.anyMatch(number -> number > 8);
    System.out.println("anyMatch : " + result);
    // 출력 : anyMatch : true
    ```

  - `boolean noneMatch(Predicate<T> p)` : 모두 만족하지 않는지 

    ```java
    IntStream intStream = IntStream.rangeClosed(1, 9);
    boolean result = intStream.noneMatch(number -> number > 8);
    System.out.println("anyMatch : " + result);
    // 출력 : noneMatch : false
    ```

- `Object[] toArray()` : Stream의 요소를 객체 배열로 반환

- `Optional<T> reduce(BinaryOperator<t> accumulator)` : Stream의 요소를 줄여가면서 계산(누적연산, accumulator)

  ```java
  String[] stringArray = {"Java", "Python", "C++", "C", "Kotlin", "Javascript"};
  // Case 1. 초기값 있을 경우
  Stream<String> stringStream1 = Stream.of(stringArray);
  IntStream stringLengthStream1 = stringStream1.mapToInt(String::length);
  int sum = stringLengthStream1.reduce(0, (a, b) -> a + b);
  System.out.println("문자열 길이의 합 : " + sum);
  // 출력 : 문자열 길이의 합 : 30
  // Case 2. 초기값 없을 경우
  Stream<String> stringStream2 = Stream.of(stringArray);
  IntStream stringLengthStream2 = stringStream2.mapToInt(String::length);
  OptionalInt max = stringLengthStream2.reduce(Integer::max);
  System.out.println("최대 길이 : " + max.getAsInt());
  // 출력 : 최대 길이 : 10
  ```

  - `accumulator` : 이전 연산 결과와 스트림의 요소에 수행할 연산을 의미한다.

  ```java
  // stream.reduce(초기값, 연산)
  stringLengthStream1.reduce(0, (a, b) -> a + b);
  // 연산의 수행 
  int a = 0;(초기값) 
  for(int b : stream){
  		a = a+b;
  }
  ```

- `R collect(Collector<T,A,R> collector)` : reduce를 이용하여 Group 작업을 수행