## HTTP Message Converter
---

### @ResponseBody 사용 원리 
- `@ResponseBody`의 사용을 통해서 HTTP의 Body에 내용은 직접 반환한다.
- `ViewResolver` 대신 `HttpMessageConverter`가 동작하게 된다.
  - `StringHttpMessageConverter`
  - `MaapingJackson2HttpMessageConverter`

### Spring - Http Message Converter 적용 경우
- HTTP 요청 
  - @RequestBody
  - HttpEntity(RequestEntity)
- HTTP 응답 
  - @ResponseBody
  - HttpEntity(ResponseEntity)

### Http Message Converter 동작 방식
1. Message Converter가 클래스, 미디어 타입을 지원하는지 확인한다.
   - `canRead()`, `canWrite()`라는 함수를 HttpMessageConverter Interface가 가지고 있다.
2. Message Converter를 통해 메세지를 읽고 쓰는 기능을 수행 
   - `read()`, `write()` 함수 이용


### 주요 HttpMessageConverter 
1. `ByteArrayHttpMessageConverter`
   - byte() 데이터를 처리
   - ClassType : byte[]
   - MediaType : */*
   - `예시`
     - @RequestBody byte[] data
     - @ResponseBody return byte[]
     - application/octet-stream
2. `StringHTtpMessageConverter`
   - String 문자로 데이터를 처리
   - ClassType : String
   - MediaType : */*
   - `예시`
     - @RequestBody String data
     - @ResponseBody return "Ok"
     - text/plain
3. `MappingJackson2HttpConverter`
   - HashMap 데이터를 처리
   - ClassType : 객체 또는 HashMap
   - MediaType : application/json 관련
   - `예시`
   - `예시`
     - @RequestBody HelloData helloData
     - @ResponseBody return helloData
     - application/json