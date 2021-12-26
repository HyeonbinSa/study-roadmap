## @Configuration 

### @Configuration 이란?

- Spring 설정 정보를 가진 파일을 만들기 위한 Annotation이며 Bean을 등록한다.

###  @Configuration의  기능

1. Spring Container에서 Bean을 관리할 수 있도록 함.
2. **Singleton을 보장해주는 역할을 한다.**

### @Configuration - Singleton 보장 테스트

- Spring 설정 정보를 담은 AppConfig의 Bean을 호출했을 때 Singleton이 보장되는지 테스트

```java
@Configuration
public class AppConfig {
		
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
		...
}
```

#### AppConfig 호출 시 각 Bean 별 호출 테스트

- 예상 결과값

```
call AppConfig.memberService
call AppConfig.memberRepository
call AppConfig.memberRepository
call AppConfig.orderService
call AppConfig.memberRepository
```

- 실제 결과값

```
call AppConfig.memberService
call AppConfig.memberRepository
call AppConfig.orderService
```

코드 상으로  memberRepository()가 총 3번 호출되었어야한다.

1. memberService - > memberRepository
2. memberRepository
3. orderServie -> memberRepository

실제로는 memberRepository()가 한번만 호출

#### MemberRepository가 Singleton이 지켜졌는지 테스트

```java
...
  @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
...
```

결과값 

```java
memberService -> memberRepository = hello.core.member.MemoryMemberRepository@5f049ea1
orderService -> memberRepository = hello.core.member.MemoryMemberRepository@5f049ea1
memberRepository = hello.core.member.MemoryMemberRepository@5f049ea1
```

모두 **동일한 주소의 memberRepository 인스턴스**를 가지고 있는 것을 확인할 수 있다.

### @Configuration  - CGLIB 

```java
@Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
				
        System.out.println("bean = " + bean.getClass()); // Class Type 출력
    }
```

- 예상 결과값  : `bean = class.hello.core.AppConfig`

- 실제  결과값 : `bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$161b2bcb`

#### Class Type이 예상과 다르게 출력된 이유

- Spring이 CGLIB이라는 바이트 코드  조작 라이브러리를 통해 AppConfig를 상속받은 AppConfig$~~~라는 임의의 다른 클래스를 생성하며 Bean으로 등록.

#### Singleton과 GCLIB

- CGLIB를 통해 만들어진 상속 Class의 내부 코드가 호출한 클래스가 `이미 Container에 등록되었다면 찾아서 반환,  없으면 생성하고 Container에 등록하여 반환` 의 코드를 동적으로 생성하기 때문에 Singleton이 보장된다.

### @Configuration이 없다면?

- @Bean만을 사용했을 때 Spring Bean으로 등록은 되지만 Singleton이 보장되지 않는다.
  - [memberRepository 호출 테스트](#MemberRepository가 Singleton이 지켜졌는지 테스트)와 같이 동일한 주소의 인스턴스가 아닌 각각 다른 인스턴스를 가지게된다.

