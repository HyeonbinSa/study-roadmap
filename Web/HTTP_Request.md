## HTTP Request Data

---

### 1. GET - Query Parameter

- Message Body가 없으며 URL의 Query Parameter를 통해 데이터를 전달

  > http://localhost:8080/request-param?username=hello&age=20과 같은 형식으로 사용

- 주로 검색, 필터, 페이징 등에서 사용한다.

- `사용방법`

  1. 전체 파라미터 조회

     - `getParameterNames()` 함수를 통해서 Queryr Parameter로 넘어온 값의 Parameter Name들을 Enumeration 형태로 반환

     ```java
     // HttpServletRequest request
     Enumeration<String> parameterNames = request.getParameterNames();
     ```

  2. 단일 파라미터 조회

     - `getParameter(String name)`함수에 원하는 Parameter Name의 값을 넣어 값을 반환

     ```java
     String username = request.getParameter("username");
     ```

  3. 복수 파라미터 조회

     - `getParameterValues()` 함수를 통해 Parameter Name은 하나인데 값이 중복될 경우 사용 
     - 특이사항 : 복수의 파라미터를 가지고 있을 때 `getParameter(name)`을 사용하게 되면 가장 첫번째 파라미터만을 반환

     ```java
     String[] usernames = request.getParameterValues(username);
     ```

     

### 2. POST - HTML  Form

- HTTP Request Message > `content-type:application/x-www-form-urlencoded`
  - `content-type`은 <u>HTTP Meassage Body의  데이터 형식</u>을 지정
- Message Body에 **Query Parameter**형식으로 데이터를 전달
  - `username=hello&age=20` 형태로 Message Body에 저장되어 전달됨
  - 즉, 1. GET - Query Parameter에서 사용한 **getParameter() 등의 함수를 그대로 사용**할 수 있다.
- 주로 회원 가입, 상품 주문, HTML Form 등에서 사용한다.

### 3. HTTP Message Body 

- HTTP API에서 주로 사용하며 JSON, XML, TEXT 형식으로 데이터를 HTTP Message Body에 직접 담아서 요청

- 주로 <u>JSON</u>을 많이 사용하며 POST, PUT, PATCH에서 사용한다.

- getInputStream()을 통해서 바이트 코드로 값을 받아오며 StreamUtils를 사용해 String 형태로 Messgae Body 받아옴

  ```java
  ServletInputStream inputStream = request.getInputStream();
  String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
  ```

- JSON으로 데이터 전송

  - content-type:application/json
  - message  body : `{"username":"hi","age":20}`과 같은 형식
  - JSON 결과를 파싱해서 사용할 수 있는 Java 객체로 변환하기 위해 Jackson과 같은 JSON 변환 라이브러리를 사용해야한다.
    - Spring Boot의 Spring MVC에서는 기본적으로 Jackson 라이브러리를 제공하여 ObjectMapper를 사용하여 변환할 수 있다.



