##  HttpServletResponse

---

### HttpServletResponse의 역할

- HTTP Response Message 생성

  - `HTTP 응답 코드(Status Code)` 지정

    ```java
    // [Status Line]
    response.setStatus(HttpServletResponse.SC_OK); // return 200;
    ```

    - HttpServletResponse 클래스 안에 각 Status Code가 상수화 되어있다.

  - `Header` 생성

    ```java
    // [Response Header 생성]
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("my-header", "hello");
    ```

    - response.setHeader() 함수를 통해 Header를 생성할 수 있으며 "my-header"와 같이 사용자 정의 헤더를 생성할  수 있다.

  - `Body` 생성

    - 단순 텍스트 응답 

      ```java
      // [Message Body 생성]
      PrintWriter printWriter = response.getWriter();
      printWriter.println("Ok!");
      ```

    - HTML 응답 : Content-Type을 `text/html`로 지정

      ```java
      // Content-Type: text/html;charset=urf-8
      response.setContentType("text/html");
      response.setCharacterEncoding("utf-8");
      ```

- 편의 기능 제공

  - Content-Type

    ```java
    //Content-Type: text/plain;charset=utf-8
    //Content-Length: 2 
    //response.setHeader("Content-Type", "text/plain;charset=utf-8");을 아래와 같이 사용할 수 있음.
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
    response.setContentLength(2); // Content-Length는 생략 시 자동 계산 후 생성된다.
    ```

  - Cookie

    ```java
    // Cookie 객체를 통해 설정할 수 있다.
    //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");를 아래와 같이 사용할 수 있음.
    Cookie cookie = new Cookie("myCookie", "good");
    cookie.setMaxAge(600); //600초
    response.addCookie(cookie);
    ```

  - Redirect

    ```java
    //response.setStatus(HttpServletResponse.SC_FOUND); //302
    //response.setHeader("Location", "/basic/hello-form.html");
    response.sendRedirect("/basic/hello-form.html"); // sendRedirect를 통해 사용 가능
    ```

    

