## @ComponentScan과 @Autowired

---

### Spring Bean 생성 시 지금까지의 방식

- @Bean이나 XML의 <bean> 등의 코드를 통해 설정 정보에 직접 등록할 Spring Bean을 나열
- 의존 관계도 직접 명시



### Component Scan 사용 방법

- 사용할 설정 정보 파일에 `@ComponentScan` Annotation을 붙여준다.
- `@ComponentScan`은 `@Component` Annotation이 붙은 Class를 스캔하여 Spring Bean으로 등록한다.
  - Bean으로 등록할 클래스에 `@Component` Annotation 추가
- 자동으로 의존관계를 주입하기 위해 `@Autowired`를 사용한다.



### @ComponentScan의 특징

- `@ComponentScan`은  `@Component`가 붙은 모든 클래스를 Spring Bean으로 등록한다.

- 기본적으로 Spring Bean의 이름은 Class명을 사용하되 맨 앞글자를 소문자로 변경하여 사용한다.

  > EX) MemberServiceImpl -> memberServiceImpl

- Spring Bean의 이름을 직접 지정하고자 한다면 `@ComponentScan("memberServiceMine")`과 같이 이름 부여할 수있다.

#### @ComponentScan - basePackages

- 탐색할 Package의 시작 위치를 지정할 때 사용한다. 설정한 Package를 포함한 하위 패키지를 모두 탐색한다.

  - 또한, 여러 개의 시작 위치를 지정할 수 있다

    ```java
    basePackage = {"hello.core", "hello.service"}
    ```

- **지정하지 않을 경우** `@ComponentScan`이 <u>지정된 설정 정보 Class의 패키지가 시작 위치</u>가 된다.

- 모든 Class를 컴포넌트 스캔하면 시간이 많이 걸리기 때문에 원하는 Class만 스캔하기 위해 사용된다.

  > * 권장 방법 : 설정 정보 Class의 위치를 **프로젝트 최상단**에 지정하는 것이다. -> 현재 Spring Boot의 기본 제공 방법



### @ComponentScan의 대상

- 컴포넌트 스캔은 <u>아래의 Annotation</u>에 대해 스캔을 하여 Spring Bean을 생성한다.
  - 각각의 클래스의 소스코드를 보면 `@Component`을 포함하고 있는 것을 확인할 수 있다.
  - `@Component` : 컴포넌트 스캔에서 사용된다.
  - `@Controller` : Spring MVC Controller에서 사용된다.
  - `@Service` : Spring Business Logic에서 사용된다.
  - `@Repository` : Spring 데이터 접근 계층에서 사용된다.
  - `@Configuration` : Spring 설정 정보에서 사용된다.

### @ComponentScan의 필터

- `includeFilters` : ComponentScan 대상을 추가로 지정
- `excludeFilters`  : ComponentScan에서 제외할 대상을 지정
- 테스트 - `includeFilters` : BeanA, `excludeFiltlers` : BeanB

```java
public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
				BeanA beanA = ac.getBean("beanA", BeanA.class);
      	BeanA beanB = ac.getBean("beanB", BeanB.class);
        ...
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
```

- 임의로 만든 `@MyIncludeComponent`,  `@MyExcludeComponent` Annotation

```java
@MyIncludeComponent
public class BeanA {
}

@MyExcludeComponent
public class BeanB {
}
```

- 테스트 결과

>[main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'beanA' // BeanA는 정상적으로 생성
>
>org.springframework.beans.factory.NoSuchBeanDefinitionException: No Bean named 'beanB' ... // BeanB는 Bean 생성이 되지 않아 에러가 발생함.