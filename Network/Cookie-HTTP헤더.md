## Cookie (쿠키) - HTTP Header

---

###  쿠키

- Set-Cookie : Server -> Client 쿠키 전달(**응답**)
- Cookie : Client -> Server 쿠키 저장 및 HTTP **요청** 시 서버로 전달
- 쿠키의 필요성
  - HTTP는 Stateless 프로토콜이라서 클라이언트와 서버가 요청과 응답을 주고 받으면 연결이 끊어지게 된다.
    - 클라이언트가 재요청했을 때 서버는 이전 요청을 기억하지 못한다.
    - 즉, 클라이언트와 서버는 서로의 상태를 유지하지 않는다.

### 쿠키의 동작

- Client -> Server 

  > POST /login HTTP/1.1
  >
  > user=Ben

- Server -> Client

  > HTTP/1.1 200 OK
  >
  > Set-Cookie: user=Ben

- 쿠키 저장소에 해당 쿠키 정보를 저장

- Client -> Server

  > GET  /welcome HTTP/1.1
  >
  > Cookie: use=Ben

- Server  -> Client

  > HTTP/1.1 200 OK
  >
  > 반가워요. Ben님

### 쿠키의 설정

> Ex) set-cookie: **sessionId=abcde1234**; **expires**=Sat,  26-DEC-2020 00:00:00 GMT; **path**=/; **domain**=.google.com;**Secure**

- **생명주기**
  - expires : 만료일이 되면 쿠키를 삭제한다.
    - expires: Sat,  26-DEC-2020 00:00:00와 같은 형태
  - max-age : 0이나 음수가 지정될 경우 쿠키 삭제
    - max-age:  3600 (3600초)
  - 세션 쿠키 
    - 만료 날짜를 생략했을 때 브라우저 종료 시까지만 유지
  - 영속 쿠키
    - 만료 날짜를 입력하면 해당 날짜까지 유지
- **도메인**
  - 도메인을 명시하며 해당 도메인에서 쿠키 생성 및 접근 가능
  - 명시 : domain=example.org
    - example.org뿐만 아니라 dev.example.org와 같이 서브 도메인에도 접근이 가능
  - 생략 : example.org
    - 작성된 도메인(example.org)에서만 접근 가능
- **경로**
  - 명시한 경로를 포함한 하위  경로 페이지만 쿠키 접근 가능
  - 일반적으로 path=/ 형식으로  사용
- **보안**
  - Secure
    - 쿠키를 https인 경우에만 전송하도록 설정
    - 원래, 쿠키는 http, https를 구분하지 않고 전송함
  - HttpOnly
    - XSS 공격방지
    - JavaScript(document.cookie)에서 접근할 숭 ㅓㅂㅅ다.
    - HTTP 전송에만 사용된다.
  - SameSite
    - XSRF 공격 방질
    - 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송

### 쿠키의 사용

- 로그인 세션 정보를 관리할 때 자주 사용
  - 최근에 광고 정보 트래킹에사용
- 쿠키 정보는  항상 서버에 전송이 된다.
  - 네트워크 트래픽이 추가로 유발된다.
  - 최소한의 정보만 사용하도록한다.
  - 서버에 전송하지 않고, 웹 브라우저 내부에 데이터를 저장하고 싶다면 웹 스토리지를 이용
- 주의점
  - 개인 정보, 민감 정보는 포함하면 안된다.