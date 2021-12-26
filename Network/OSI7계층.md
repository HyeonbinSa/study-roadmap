## OSI 7계층

---

### OSI 7계층 구조

<img src="https://postfiles.pstatic.net/MjAxNzEwMjNfNTUg/MDAxNTA4NzU0NzQyMjYw.HKmTZyVd4130sVfZhQKdduzYyF2pnmMjTHJV73TRcq4g.Fe0AINnj5Flj7Kf32W596v23GLjAonSZTF20Hmqt9HMg.PNG.shb1833/image_2426958421508754591752.png?type=w966">

### 계층 구조로 나눈 이유

통신이 일어나는 과정을 **단계별로 파악**할 수 있고 각 계층이 독립되어있기 때문에 **다른 계층에 영향없이 수정**이 가능.

#### 1. 물리 계층(Pysical)

> 리피터, 허브,  케이블

데이터를 전송하는 역할 / (전기적, 기계적 특성을 이용한 데이터 전송)

단위 : Bit

####  2. 데이터링크 계층(Data  Link)

>스위치, 브릿지

직접 연결된 다른 2개의 네트워킹 장치간 데이터 전송을 담당하며 물리적인 주소(MAC)를 통해서 통신(에러 검출, 재전송, 흐름제어 등의 기능을 수행)

단위 :  Frame

#### 3. 네트워크 계층(Nework)

>라우터, IP

데이터를 목적지까지 빠르고 안전하게 보내는 역할을 한다.(빠르게 보내기 위한 최적의 경로 선택 과정 : 라우팅)

단위 : 패킷

#### 4. 전송 계층(Transport)

> TCP, UDP

송신자와 수신자의 신뢰할 수 있는 데이터 전송 서비스를 제공하며 패킷을 분할 및 재조립하여 신뢰성 있는 통신을 보장한다.

- TCP : 연결지향, 신뢰성
- UDP : 비연결지향, 비신뢰성, 실시간

단위 : 세그먼트  /  데이터그램

####  5.  세션 계층(Session)

> API,  Soceck

응용프로그램 간 연결을 유지하기 위한 구조를 제공, TCP/IP 세션을 연결, 유지, 해제 등의 기능을 수행

#### 6. 표현 계층(Presentation)

> JPEG, MPEG

응용프로그램이 다루는 정보를 통신에 알맞는 형태로 변환하는 역할을 수행하며, 압축 및 암호화의 기능을 수행

#### 7. 응용 계층(Application)

> HTTP, FTP, IMAP, POP3 등

응용 프로그램 간 정보 교환 및 파일 전송 등의 기능을 수행