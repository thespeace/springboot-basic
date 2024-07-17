package thespeace;

import thespeace.boot.MySpringApplication;
import thespeace.boot.MySpringBootApplication;

/**
 * <h1>편리한 부트 클래스 만들기</h1>
 * <ul>
 *     <li>패키지 위치가 중요하다. thespeace 에 위치했다.</li>
 *     <li>여기에 위치한 이유는 @MySpringBootApplication 에 컴포넌트 스캔이 추가되어 있는데, 컴포넌트
 *         스캔의 기본 동작은 해당 애노테이션이 붙은 클래스의 현재 패키지 부터 그 하위 패키지를 컴포넌트
 *         스캔의 대상으로 사용하기 때문이다 애노테이션이 붙은 thespeace.MySpringBootMain 클래스의 패키지 위치는
 *         thespeace 이므로 그 하위의 thespeace.spring.HelloController 를 컴포넌트 스캔한다.</li>
 *     <li>MySpringApplication.run(설정 정보, args) 이렇게 한줄로 실행하면 된다.</li>
 *     <li>이 기능을 사용하는 개발자는 @MySpringBootApplication 애노테이션과 MySpringApplication.run() 메서드만 기억하면 된다.</li>
 *     <li>이렇게 하면 내장 톰캣 실행, 스프링 컨테이너 생성, 디스패처 서블릿, 컴포넌트 스캔까지 모든 기능이 한번에 편리하게 동작한다.</li>
 * </ul><p><p>
 *
 * <h2>스프링 부트</h2>
 * 지금까지 만든 것을 라이브러리로 만들어서 배포한다면? -> 그것이 바로 스프링 부트이다.<p><p>
 *
 * <h2>일반적인 스프링 부트 사용법</h2>
 * <pre>{@code
 *      @SpringBootApplication
 *      public class BootApplication {
 *          public static void main(String[] args) {
 *              SpringApplication.run(BootApplication.class, args);
 *          }
 *      }
 * }</pre>
 * 스프링 부트는 보통 예제와 같이 SpringApplication.run() 한줄로 시작한다.
 */
@MySpringBootApplication
public class MySpringBootAppMain {
    public static void main(String[] args) {
        System.out.println("MySpringBootAppMain.main");
        MySpringApplication.run(MySpringBootAppMain.class, args);
    }
}
