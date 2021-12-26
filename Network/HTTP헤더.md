## HTTP Header

### HTTP Header의 과거

- RFC2616
- header-field = field-name":"OWS field-value OWS (OWS는 띄어쓰기 허용을 의미한다.)
- field-name은 대소문자의 구분이 없다.
- 용도는 HTTP 전송에 필요한 모든 부가 정보를 담고 있다.
  - 메세지 바디의 내용, 크기, 압축, 인증, 서버 정보 등
- 분류
  - General : 메세지 전체에 적용되는 정보
  - Request : 요청 정보
  - Response : 응답 정보 
  - Entity : 엔티티 바디 정보 (예. Content-Type:text/html 등)
- 메세지 본문은 엔티티 본문을 전달하는데  사용 (메세지 본문[엔티티 본문])

### HTTP Header의 현재

- RFC7230 ~ 7235

- 메세지 본문(Message Body = Payload)를 표현 통해 데이터를 전달한다.

- **표현**이란 요청이나 응답에서 전달할 실제 데이터를 의미한다.

  - 표현 헤더는 표현 데이터(메세지 본문)을 해석할 수 있는 정보를 제공한다.

  - 데이터의 유형(html,json), 데이터의 길이 압축 정보 등

    > Content-Type:text/html;charset=UTF-8
    >
    > Content-Length:3423

### 표현

- 표현 헤더는 **전송과 응답**에 모두 사용된다.
- Content-Type : 표현 데이터의 형식 
  - Content Body에 들어가는 내용이 무엇인지, 미디어 타입, 문자 인코딩에 대한 정보를 담고 있음.
  - Ex)  text/html;charset=UFT-8, application/json, image/png 등
- Content-Encoding : 표현 데이터의 압축 방식
  - 표현 데이터를 압축하기 위해  사용한다.
  - 데이터를 전달하는 곳에서 압축한 뒤 인코딩 헤더를 추가하고 읽는 쪽에서 인코딩 헤더 정보로 압축 해제
  - Ex) gzip, deflate, identity
- Content-Language : 표현 데이터의 자연 언어
  - 표현 데이터의 자연 언어를 표현한다.
  - Ex) ko, en, en-US
- Content-Length : 표현 데이터의 길이
  - 바이트 단위
  - 참고 : Transfer-Encoding을 사용하면 Content-Length 사용 불가

### 협상(Content  Negotiation)

- 클라이언트가 선호하는 표현 요청(요청시에만 사용이 된다.)

  - 클라이언트가 원하는 우선 순위로 요청을 하는 것이고 Server는 요청한 Type에 맞춰서 전달.

- Accept : 클라이언트가 선호하는 미디어 타입 전달

- Accept-Charset : 클라이언트가 선호하는 문자 인코딩

- Accept-Encoding : 클라이언트가 선호하는 압축 인코딩

- Accept-Language : 클라이언트가 선호하는 자연 언어

  - Ex) Client(한국어 브라우저를 이용) <----> Server(다중 언어 지원 서버 [기본 영어(en), 한국어 지원(ko)])일 경우

  - Client 

    > GET /event
    >
    > Accept-Language: ko

  - Server 

    > Content-Language: ko
    >
    > 안녕하세요.

  - 복잡한 경우를 위해 우선순위를 사용(Quality Values)

    - Ex) Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
      - "한국어가 안되면 en-US -> en-US도 안되면 en으로 달라"

  - 구체적인 것이 우선

    - Accept: text/*, text/plain, text/plain;format=flowerd, */*

  - 구체적인 것을 기준으로 미디어 타입을 맞춤

    - Accept: text/*;q=0.3, text/plain;q=0.7, text/plain;format=flowerd;1=0.4, */*;1=0.5

### 전송

- 단순 전송

  - Content-Length
  - 요청한 그대로 전송

- 압축 전송

  - Content-Encoding
  - 

- 분할 전송

  - Transfer-Endcoding

  - Content-Length를 사용할 수 없음. 각 Chunk 마다 바이트 수가 있기때문.

  - Ex) chunked : 쪼개서 보냄

    >5
    >
    >Hello
    >
    >5
    >
    >World
    >
    >0
    >
    >\r\n

- 범위 전송

  - 요청) Range : bytes=1001-2000
  - 응답) Content-Range: bytes 1001-2000/2000

### 일반 정보

 - From : 유저 에이전트의 이메일 정보
   	- 검색 엔진 같은 곳에서 주로 사용하며 **요청**에서 사용
- Referer : 이전 웹페이지의 주소
  - 현재 요청된 웹 페이지 이전 주소
    - Ex) google.com ->  naver.com로 이동했을 때 Referer의 값은 google.com
  - 유입 경로 분석 가능하며 **요청**에서 사용
- User-Agent
  - Ex)Mozilla/5.0()
  - 클라이언트의 애플리케이션 정보(웹브라우저 정보 등)
  - 통계 정보
  - 특정 웹 브라우저에서 장애가 발생하는지 파악하는 용도로 사용하며 **요청**에서 사용
- Server 
  - 요청을 처리하는 Origin 서버의 소프트웨어 정보
    - Server: Apache/2.2.22 (Debian)
  - **응답**에서 사용
- Date
  - 메세지가 발생한 날짜와 시간
    - Date: Tue, 15 Nov 1994 08:12:31 GMT
  - **응답**에서 사용

### 특별한 정보

- Host `필수`

  - 요청한 호스트 정보(도메인)이며 **요청**에서 사용, 

  - 하나의 서버가 여러 도메인을 처리해야할 때 사용

  - 하나의 IP 주소에 여러 도메인이 적용되어 있을 때 사용

  - Ex) IP : 200.200.200.2 안에 "aaa.com", "bbb.com", "ccc.com" 호스트가 여러개 존재

    > GET /hello HTTP/1.1

  - 위와 같이 요청했을 때 어느 호스트로 접근할 지 모호하여 문제가 발생

    > GET /hello HTTP/1.1
    >
    > Host: aaa.com

  - 위와 같이 Host를 입력하여 해결 가능

- Location

  - 페이지 리다이렉션
  - 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면 Location 위치로 자동 이동(Redirect)
  - 201(Created) : Location 값은 요청에 의해 생성된 리소스 URI
  - 3xx(Redirection) : 요청을 자동으로 리다이렉션하기 위한 대상 리소스를 가리킨다.

- Allow

  - 허용 가능한 HTTP 메서드
  - 405(Method Not Allowd)에서 응답에 포함해야함
    - Allow: GET, HEAD, PUT

- Retry-After

  - 유저 에이전트가 다음 요청을 하기까지 기다려야하는 시간
  - 503(Service Unavailable): 서비스가 언제까지 불능인지 알려줄 수 있다.
    - Retry-After:Fri, 31 Dec 1999 23:59:59 GMT(날짜  표기)
    - Retry-AfterL12Q(초단위 표기)

### 인증

- Authorization
  - 클라이언트 인증 정보를 서버에 전달     
- WWW-Authenticate 
  - 리소스 접근 시 필요한 인증 방법을 정의한다.
  - 401(Unauthorized) 응답과 함께 사용한다.

