package thespeace.embed;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import thespeace.spring.HelloConfig;

/**
 * <h1>내장 톰캣 - 스프링</h1>
 * main() 메서드를 실행하면 다음과 같이 동작한다.
 * <ul>
 *     <li>내장 톰캣을 생성해서 8080 포트로 연결하도록 설정한다.</li>
 *     <li>스프링 컨테이너를 만들고 필요한 빈을 등록한다.</li>
 *     <li>스프링 MVC 디스패처 서블릿을 만들고 앞서 만든 스프링 컨테이너에 연결한다.</li>
 *     <li>디스패처 서블릿을 내장 톰캣에 등록한다.</li>
 *     <li>내장 톰캣을 실행한다.</li>
 * </ul>
 * 코드를 보면 알겠지만, 서블릿 컨테이너 초기화와 거의 같은 코드이다.<br>
 * 다만 시작점이 개발자가 main() 메서드를 직접 실행하는가, 서블릿 컨테이너가 제공하는 초기화 메서드를 통해서
 * 실행하는가의 차이가 있을 뿐이다.
 *
 * @see <a href="http://localhost:8080/hello-spring">test url</a>
 */
public class EmbedTomcatSpringMain {
    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatSpringMain.main");

        //톰캣 설정
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        //스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        //스프링 MVC 디스패처 서블릿 생성, 스프링 컨테이너 연결
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        //디스패처 서블릿 등록
        Context context = tomcat.addContext("", "/");
        tomcat.addServlet("", "dispatcher", dispatcher);
        context.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();
    }
}
