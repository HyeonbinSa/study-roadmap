## HTTP 헤더 - Cache

---

### 캐시의 필요성

- 데이터의 변경이 없어도 네트워크를 통해 데이터를 계속 다운로드 받아야함.
- 브라우저 로딩이 느려지며 사용자 입장에서 서비스가 늦다고 느낌.

### 캐시의 사용

- HTTTP Response에 캐시에 대한 설정을 담아서 응답함.
  - `cache-control` > cache-control: max-age=60 와 같이 HTTP Header에 담아서 전달.
  - 해당 데이터는 브라우저 캐시에 설정한 시간(예시에서는  60초)동안 캐시를 저장한다.
  - 이후 동일한 요청을 한면 네트워크가 아닌 브라우저 캐시에서 꺼내온다.

###  캐시의 시간 초과

- 캐시 유효 시간이 초과하여 다시 요청할 때 발생할 수  있는 상황은 2가지

  1. 서버에서 기존 데이터를 변경함.
  2. 서버에서 기존 데이터를 변경하지 않음.

- **검증 헤더**

  - 검증 헤더를 포함하여 조건부 요청을 할 수 있다.

    1. Last-Modified
    2. ETag

    

#### 검증 헤더 - Last-Modified

- Server -> Client : 해당 데이터의 최종 수정일을 작성하여 전달.
- `Last-Modified:2020-11-10, 10:00:00`
- Client -> Server : 캐시 유효시간이 초과되어 브라우저 캐시에 담겨진 데이터에 저장된 최종 수정일을 Server에 전달
- `if-modified-since:2020-11-10, 10:00:00`
- Server -> Client : **변경 사항이 없을 경우**, 변경사항이 없다고 전달
  - `HTTP/1.1 304  Not  Modified`
  - `Last-Modified:2020-11-10, 10:00:00`
  - <u>HTTP Body가 없음.</u>
- Server -> Client : **변경 사항이 있을 경우**, 변경사항이 없다고 전달
  - `HTTP/1.1 200 OK` 
  - `Last-Modified:2020-11-10, 11:00:00`
  - <u>모든 데이터 + HTTP Body</u>
- 단점
  - 1초  미만 단위로  캐시 조정이  불가능
  - 날짜 기반의 로직을 사용한다.  (데이터를 변경했다가 다시 원래 데이터로 수정해도 수정 시간이 다르기때문에 변경된 것으로 인식)

#### 검증  헤더 - ETag

- ETag(Entity Tag) : 캐시용 데이터에 임의의 고유한 버전 이름을 설정
  - 캐시 제어 로직을  **<u>서버에서 관리</u>**
- 데이터가 변경되면 이름을 변경해서 보내면 데이터가 변경되었다고 인식
  - Server -> Client : 보내는 데이터의 ETag를 설정해서 전달
    - `ETag:"aaaaaaa"`
  - Client -> Server : If-None-Match:"ETag이름"
    - `If-Non-Match: "aaaaaaa"`
  - Server -> Client : **변경 사항이 있을 경우**, 변경사항이 없다고 전달
    - `HTTP/1.1 304  Not  Modified`
    - `ETag:"aaaaaaa"`
    - <u>HTTP Body가 없음.</u>

### 캐시 제어 헤더

- Cache-Control  
  - `max-age` : 캐시 유효 시간 (얼마나 유효하게 설정 할것인지), 초 단위
  - `no-cache` : 데이터는 캐시해도 되지만, <u>항상 Server에 검증하고 사용하도록 설정</u>
  - `no-store` : 데이터에 민감한  정보가 있으니 저장하면 안될 때 사용
  - `must-revalidate` : 캐시 만료 후 최초 조회 시에는 Origin 서버에 검증해야한다.
    - Origin 서버에 접근이 실패할 경우 **반드시**  오류가 발생해야한다(504 - Gateway Timeout)
- Pragma
  - Cache-Control 하위 호환으로 HTTP 1.0  하위 호환이다.
  - `no-chache`
- Expires
  - 캐시 만료일을 지정(하위 호환)
  - `expires: Mon,  01 Jan 1990  00:00:00 GMT`
- `정리`
  - 현재  Cache-Control이 더  유연하여 사용을 권장하며 Pragma와 Expires는 거의 미사용
  - `Cache-Control: max-age`를 사용시 `Expires`는 무시

### 프록시 캐시 서버

- 미국과 같이 거리가 먼 서버에서 데이터를 가지고오면 시간이 오래걸리기 때문에 가까운 위치에 있는 **프록시 캐시 서버**에서 데이터를 가지고 온다.
- 프록시 캐시 서버 : Public 캐시
- 웹 브라우저, 로컬 캐시 : Private 캐시
- 캐시 지시어(Directives)  
  - `Cache-Control: public` : 응답이 Public에 저장되어도 된다.
  - `Cache-Control: private` :  응답이 Private 캐시에 저장되어야한다. (Defaullt)
  - `Cache-Control:  s-maxage` : 프록시 캐시에만 적용되는 max-age
  - `Age: 60` : 데이터가 프록시 서버에 머무른 시간







