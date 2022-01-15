## Java Optional

---

### Optional

- T  타입 객체의 Wrapper Class -  Optional<T>

  ```java
  public final class Optional<T>{
  	private final T value;  // T 타입의 참조변수
  	...
  }
  ```

- 사용 목적

  - 간접적으로 null을 다루기 위함.
    1. null을  직접 다루게되면 **NullPointerException**과 같은 위험이 있음.
    2. null을 체크하기위해 if문을 사용해야해서 **코드가 지저분**해짐.

### Optional<T> 객체의 생성

- Optional<T> 객체를 생성하는 방법

  ```java
  String str = "abc";
  Optional<String> opt = Optional.of(str);
  Optional<String> opt = Optional.of("abc");
  // Optional<String> opt = Optional.of(null); // NullPointerException에러 발생
  Optional<String> opt = Optional.ofNullable(null);
  ```

- 비어있는 Optionall<T> 객체 생성 방법

  ```java
  Optional<String> opt = null // null로 초기화하는 방법은 가능은 하나 바람직하지 못한 생성 방법
  Optional<String> opt = Optional.empty(); // 비어있는 객체로 초기화
  ```

### Optional<T> 객체  값  가져오기

- `get()` : 저장된 값을 반환하나 null이면 <u>예외 발생</u>

  ```java
  Optional<String> opt = Optional.empty();
          System.out.println("opt : " + opt.get()); //  NoSuchElementException: No value present Exception 발생
  // try-catch 구문을 통해 예외처리가 필요
  ```

- `orElse(대체값)`  : value가 null일 경우 인자로  넘어온 대체값을  반환 

  ```java
  Optional<String> opt = Optional.empty();
  System.out.println("opt : " + opt.orElse("null Optional 객체"));
  // 출력 : opt : null Optional 객체 (대체값이 출력된다.)
  ```

- `orElseGet(람다식)` : 람다식을 사용할  수 있는  메서드

  ```java
  Optional<String> opt = Optional.empty();
  System.out.println("opt : " + opt.orElseGet(()->"Lambda"));
  // 출력 : opt : Lambda
  ```

- `orElseThrow()`  : null이면  예외 발생하는데 예외 종류를 지정 가능

- `isPresent()` : Optional객체의  Value가 null이면 `false`, 아니면 `true`를 반환

  ```java
  OptionalInt optZero = OptionalInt.of(0);
  OptionalInt optEmpty = OptionalInt.empty();
  System.out.println("optZero.isPresent() : " + optZero.isPresent());
  System.out.println("optEmpty.isPresent() : " + optEmpty.isPresent());
  // 출력 1 : optZero.isPresent() : true
  // 출력 2 : optEmpty.isPresent() : false
  ```

- `ifPresect()` :  Optional객체의 Valuer가 null이 아닐 경우에만 작업을 수행

  ```java
  OptionalInt optInt = OptionalInt.of(142);
  OptionalInt optEmpty = OptionalInt.empty();
  System.out.print("optInt.isPresent() : ");
  optInt.ifPresent(System.out::println);
  System.out.print("optEmpty.isPresent() : ");
  optEmpty.ifPresent(number -> System.out.println(number));
  // 출력 1 : optInt.isPresent() : 142
  // 출력 2 : optEmpty.isPresent() : 
  ```

### Optional 기본형

- 성능을 위해 기본형 값을 감싸는 Wrapper 클래스를 사용
- OptionalInt, OptionallLong, OptionalDouble
- 예) OptionalInt.of(0) vs. OptionalInt.empty(), 두 개의 값이 구별이 가능한가?
  - `isPresent()`를 통해 구별 