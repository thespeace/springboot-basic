package thespeace.boot;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

/**
 * <ul>
 *     <li>기존 코드를 모아서 편리하게 사용할 수 있는 클래스를 만들었다.<br>
 *         MySpringApplication.run() 을 실행하면 바로 작동한다.</li>
 *     <li>configClass : 스프링 설정을 파라미터로 전달받는다.</li>
 *     <li>args : main(args) 를 전달 받아서 사용한다. 참고로 예제에서는 단순히 해당 값을 출력한다.</li>
 *     <li>tomcat.start() 에서 발생하는 예외는 잡아서 런타임 예외로 변경했다.</li>
 * </ul>
 */
public class MySpringApplication {
    public static void run(Class configClass, String[] args) {
        System.out.println("MySpringBootApplication.run args=" + List.of(args));

        //톰캣 설정
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        //스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(configClass);
        //스프링 MVC 디스패처 서블릿 생성, 스프링 컨테이너 연결
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        //디스패처 서블릿 등록
        Context context = tomcat.addContext("", "/");
        tomcat.addServlet("", "dispatcher", dispatcher);
        context.addServletMappingDecoded("/", "dispatcher");
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}
