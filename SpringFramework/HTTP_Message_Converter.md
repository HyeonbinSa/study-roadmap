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
     - @RequestBody HelloData helloData
     - @ResponseBody return helloData
     - application/json


### Spring MVC 구조에서의 HTTP Message Converter
- Spring MVC 동작 순서 중 `HTTP Message Converter`
  1. HTTP 요청
  2. Dispatcher Servlet
  3. Handler Mapping, 핸들러 조회
  4. Handler Adapter List 중 처리 가능한 Handler Adapter 조회
  5. **Handler Adapter** 에서 `HTTP Massage Converter`의 기능이 수행된다.


### ArgumentResolver
- `HttpServletRequest`, `model`, `@RequestParam`, `@ModelAttribute`와 같은 Annotation, Class 등을 처리할 수 있도록 도와준다.
- Interface : `HandlerMethodArgumentResolver`
- `RequestMapppingHandlerAdapter`가 해당하는 ArgumentResolver를 요청하여 Object를 반환하여 다시 전달한다.
- **동작 방식** 
  1. ArgumentResolver의 `supportsParameter()`를 호출하여 해당하는 Parameter를 지원하는지 확인
  2. `resolveArgument()`를 호출하여 객체를 생성
  3. Controller 호출 시 2번에서 생성한 객체를 반환

### ReturnValueHandler
- `HandlerMethodReturnValueHndler`이며 <u></u>**응답**</u> 값을 변환하고 처리한다.
- `@ResponseBody`와 `HttpEntity`를 처리하고 HTTP Message  Converter를 호출하여 응답 결과를 만든다.