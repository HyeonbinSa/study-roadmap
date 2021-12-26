## TCP/UDP

---

###  TCP/IP 4계층

1. 애플리케이션 계층 : HTTP, FTP ... 
   - 웹브라우저, 네트워크 게임, 채팅 등
   - Ex ) Socket 라이브러리
2. 전송 계층  : TCP, UDP
3. 인터넷 계층 : IP
4. 네트워크 인터페이스 계층 
   - LAN 드라이버 / LAN 장비

### TCP/UDP 프로토콜이 사용되는 이유

- IP는 Host to Host의 전송을 지원한다. 즉, 장비 <-> 장비 간의 데이터 전송은 IP 주소로 처리할 수 있으나 **한 장비 내의 여러 응용프로그램을 사용**하고 있을 경우 IP 프로토콜의 한계가 존재한다.
  - Ex) 내 컴퓨터 <---(게임,  화상통화, 웹 브라우저 등)---> 서버, 한 서버에 여러 프로그램을 실행할 경우 
  - Port 정보를 통해 해결
    - Ex) 내컴퓨터(Port 8090) <- 게임 -> Server(Port 11220)
    - Ex) 내컴퓨터(Port 11002) <- 화상 통화 -> Server(Port 9920)

### TCP 특징

- 전송 제어 프로토콜 (Transmission Control  Protocol)
  - 연결 지향 - TCP 3 Way Handwhake(***가상  연결***)
  - 데이터 전달 보증
    - Client에서 Server로 데이터를 전송할 때, Server는 데이터를 전달받았다고 답장
  - 순서 보장
    - 패킷이 1,2,3으로 보냈을 때 1,3,2로 도착한 경우 Server가 2번부터 다시 보내라고 메세지를 보냄
    - 헤더에 순서 정보가 포함되어있기 때문에 가능
  - 신뢰할 수  있는 프로토콜
- IP 정보를 포함한 패킷에 Port 정보를 추가하여 구분

### TCP 연결 방법

- **3 Way Handshake**
  1. Client -> [SYN] -> Server
  2. Client <- [SYN+ACK] <- Server
  3. Client -> [ACK] -> Server
- SYN : 접속 요청
- ACK : 요청 수락
- 3번째 단계 [ACK]와 함께 데이터 전송이 가능하다.

### UDP 특징

- 사용자 데이터그램 프로토콜 (User Datagram Protocol)
- 비연결 지향 : 3 Way Handshake 미수행
- 데이터 전달 미보증
- 순서 보장 안함
- 데이터 전달 및 순서가 보장되지는 않으나 **단순하고 빠르다는 특징**을 가지고 있다.
  - 3 Way Handshake를 수행하지 않음.
- IP 정보에 Port, Checksum의  정보가 추가되어있다.

### Port 특징

- Ex) IP 주소가 아파트 주소라면 Port 번호는 1xx동 과 같은 주소
- 0 ~  65535의 포트 번호를 할당할 수 있다.
- Well Known Port : 잘 알려진 포트로 사용하지 않는 것을 권장
  - 0 ~ 1023
  - 예시
    - FTP : 20, 21
    - TELNET : 23
    - HTTP : 80
    - HTTPS : 443

### DNS

- DNS : Domain Name Service
- Client --(google.com)-->  DNS Server --(100.100.100.2)-->  Client --(100.100.100.2)--> Server(100.100.100.2)
- IP는 변경  가능성이 있고 주소가 어려운데 Domain을 등록해서 쉽게 찾을 수 있다.