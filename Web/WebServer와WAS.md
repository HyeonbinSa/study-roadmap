## Web Sever  Vs. WAS

---

### Web Server

- HTTP 기반으로 동작을 하며 **정적 리소스**를 제공한다.(기타 부가기능 제공) - Ex) NGINX, APACHE 등
  - 정적 리소스 : HTML, CSS, JS, Image, Video 등

### WAS (Web Application Server)

- HTTP 기반으로 동작을 하며 웹서버의 기능인 정적 리소스 제공하는 기능 수행을 포함한다.
- **프로그램 코드를 실행하여 Application Logic**을 수행한다.  - Ex) Tomcat, Jetty 등
  - 동적 HTML, HTTP API(JSON) - Ex) 사용자마다 다른 화면을 보여줄 수 있다.
  - Servlet, JSP,  Spring MVC

### Web System의 구성  

- WAS와 DB만으로 시스템이 구성 가능하며 WAS는 정적 리소스, 애플리케이션 로직 모두 제공이 가능하다.
- 문제점
  - WAS의 역할이 너무 커서 서버의 과부하가 발생할 가능성이 존재
  - 중요한 애플리케이션 로직이 정적 리소스때문에 수행이 불가할 가능성 존재
  - WAS 장애 시 오류 화면 노출 불가능
- 개선 구성 사항
  - Client -> Web Server(정적 리소스) -> WAS(애플리케이션 로직) -> DB
  - 장점
    - WAS가 애플리케이션 로직에 집중할 수 있다.
    - 효율적으로 리소스를 관리할 수 있다. WAS와 Web Server를 각각 관리할 수 있기 때문에 효율적이다.
    - WAS, DB 장애 시 Web Server에서 오류 화면 제공 가능하다.