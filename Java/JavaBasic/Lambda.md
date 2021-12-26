## Lambda, 람다식

---

### 람다식이란?

- Java 8부터 지원되는 기능으로 자바에서 함수형 프로그래밍(Functional Programming)을 구현하는 방식
  - 외부 변수가 사용되지 않고 내부 변수만을 통해서 동작
  - 입력 받은 자료를 기반으로 수행되고 외부에 영향을 받지 않기 때문에 병렬 처리에 용이하다.
- 클래스를 생성하지 않고 함수의 호출만으로 기능을 수행한다.
- 내부적으로 익명 객체가 생성이 되는 방식

### 람다식 문법

1. 매개 변수 하나인 경우, 괄호가 생략이 가능하다.

   ```java
   str -> {System.out.println(str);}
   ```

2. 중괄호 안의 구현부가 한 문장일 경우, 중괄호 생략이 가능하다

   ```java
   str -> System.out.println(str);
   ```

3. 중괄호 안의 구현부가한문장이라도 `return문`이 존재하면 중괄호를 생략할 수 없다.

   ```java
   str -> return str.length(); // Error 발생
   ```

4. 중괄호 안의 구현부가 반환문 하나라면 `return`과 중괄호 모두 생략할 수 있다.

   ```java
   (x, y) -> x+y				// 두 값을 더하여 반환
   str -> str.length() // 문자열의 길이를 반환
   ```

### 람다식 테스트

```java
@FunctionalInterface // Lambda식을 위한 인터페이스라는 것을 명시
public interface MyMaxNumber {
    int getMaxNumber(int x, int y); // 메소드를 2개 이상 작성 불가
}

...
// 실행하는 Class에서 main() 메소드 
MyMaxNumber maxNumber = (x, y) -> (x >= y) ? x : y; // 람다식 적용
System.out.println(maxNumber.getMaxNumber(10,20));
,,,
```

```
결과값 : 20
```

- 람다 함수 생성 조건
  - interface로 생성이 되야한다.
    - @FuntionalInterface로 명시
    - 해당 Annotation이 있을 경우 메소드는 2개  이상 작성할 수 없다.

### 함수를 변수처럼 사용하는 람다식

- 사용할 Lambda Interface 생성

```java
interface PrintString {
    void showString(String str);
}
```

1. 자료형에 기반하여 선언

```java
public class Main {
  public void main(String[] args){
    PrintString lambdaStr = s -> System.out.println(s);
    lambdaStr.showString("Test");
  }
  ...
}
```

2. 매개변수로 전달하여 사용

```java
public class Main {
  public void main(String[] args){
    PrintString lambdaStr = s -> System.out.println(s);
    showMyString(lambdaStr);
  }
  ...
    public static void showMyString(PrintString p) {
    p.showString("Test2");
  }
  ...
}
```

3. 메서드의 반환값으로 사용

```java
public class Main {
  public void main(String[] args){
    PrintString lambdaTest = returnString();
    lambdaTest.showString("Test3");
  }
  ...
  public static PrintString returnString() {
    return s->System.out.println(s+"!!!");
  }
  ...
}
```

