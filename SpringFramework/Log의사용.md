## Spring Log - SLF4J

---

### Log 라이브러리

- `System.out.println()`과 같은 System Console을 사용하지 않고 별도 Loggin 라이브러리를 통해 로그를 출력
- Spring Boot 기본  제공 Logging 라이브러리
  - SLF4J :  다양한 Log 라이브러리를 통합하여 인터페이스 형태로 제공하는 SLF4J 라이브러리를 사용
  - Logback : SLF4J의 구현체 (Spring Boot가  기본 제공)
- 사용 시  장점
  - 스레드 정보, Class 이름 등 부가 정보를  함께 확인할 수  있으며  출력 메세지를 조정할 수 있다.
  - Log Level 설정을 통해  상황에 맞게 조절할 수 있다.



### Log 선언

- `org.slf4j` 패키지 내에 존재하는 Logger, LoggerFactory 클래스를 이용하여 선언한다.

  ```java
  // Log 선언 1
  private final Logger log = LoggerFactory.getLogger(getClass()); // 현재 Class 지정
  // Log 선언 2
  private final Logger log = LoggerFactory.getLogger(xxx.class); // 현재 Class 지정
  // Log 선언 3
  @Slf4J // Lombok을 이용한 Log 선언 Annotation
  public class LogTestController {
  ...
  }
  ```



### Log 호출

- `log.info()`를 이용하여 Log를 호출한다.

  - Log  Level을 설정하여 출력이 가능하다.(아래로 내려갈 수록 높은 Level)
    - TRACE
    - DEBUG
    - INFO
    - WARN
    - ERROR

- Log 출력

  ```java
  log.info(" info log={}", name);
  // 출력 Log 메세지 : 2022-01-23 20:25:50.041  INFO 7391 --- [nio-8080-exec-2] hello.springmvc.basic.LogTestController  :  info log=String
  ```

- Log 호출 시 주의점

  - `log.info(" info log = " + name)`와 같은 방식으로 `{}`없이 출력 시 `" info log = " + name`의 <u>문자열을 합치는 연산이 일어나기</u>때문에 리소스 낭비
  - `log.info(" info log={}", name)`와 같이 사용 시 의미없는  연산이 발생하지 않는다.



###  Log 레벨 설정

-  `application.properties` 파일에서 출력할 Log Level을 선택할 수 있다.

  - Default

    ```properties
    ## 기본 설정이 info 이상 Level의 Log 출력하도록 설정되어있음
    logging.level.root=info
    ```

  - Log Level 설정

    ```properties
    ## hello.springmvc 포함 하위 패키지에 info 이상의 log 출력
    logging.level.hello.springmvc=info 
    ```

    