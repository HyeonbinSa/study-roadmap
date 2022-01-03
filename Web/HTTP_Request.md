## HTTP Request Data

---

### 1. GET - Query Parameter

- Message Body가 없으며 URL의 Query Parameter를 통해 데이터를 전달
- 주로 검색, 필터, 페이징 등에서 사용한다.

### 2. POST - HTML  Form

- HTTP Request Message > content-type:application/x-www-form-urlencoded
- Message Body에 **Query Parameter**형식으로 데이터를 전달
  - username=hello&age=20
- 주로 회원 가입, 상품 주문, HTML Form 등에서 사용한다.

### 3. HTTP Message Body 

- HTTP API에서 주로 사용하며 JSON, XML, TEXT 형식으로 데이터를 HTTP Message Body에 직접 담아서 요청
- 주로 JSON을 많이 사용하며 POST, PUT, PATCH에서 사용한다.



