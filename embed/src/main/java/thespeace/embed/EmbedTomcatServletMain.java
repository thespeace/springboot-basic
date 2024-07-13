package thespeace.embed;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import thespeace.servlet.HelloServlet;

/**
 * <h1>내장 톰캣 - 서블릿</h1>
 * 이제 본격적으로 내장 톰캣을 사용해보자.<br>
 * 내장 톰캣은 쉽게 이야기해서 톰캣을 라이브러리로 포함하고 자바 코드로 직접 실행하는 것이다.<p><p>
 *
 * test url에 접속해보면 내장 톰캣을 사용한 덕분에 IDE에 별도의 복잡한 톰캣 설정 없이 main() 메서드만<br>
 * 실행하면 톰캣까지 매우 편리하게 실행되었다. 물론 톰캣 서버를 설치하지 않아도 된다!<p><p>
 *
 * 하지만 내장 톰캣을 개발자가 직접 다룰일은 거의 없다.<br>
 * 스프링 부트에서 내장 톰캣 관련된 부분을 거의 대부분 자동화해서 제공하기 때문에 내장 톰캣을 깊이있게<br>
 * 학습하는 것은 권장하지 않는다. 어떤 방식으로 동작하는지 그 원리를 대략 이해하는 정도면 충분하다.
 *
 * @see <a href="http://localhost:8080/hello-servlet">test url</a>
 */
public class EmbedTomcatServletMain {
    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatServletMain.main");

        //톰캣 설정
        Tomcat tomcat = new Tomcat(); //내장 톰캣 생성.
        Connector connector = new Connector();
        connector.setPort(8080); //톰캣이 제공하는 커넥터를 사용해서 8080 포트에 연결.
        tomcat.setConnector(connector);

        //서블릿 등록
        Context context = tomcat.addContext("", "/"); //톰캣에 사용할 contextPath와 docBase를 지정.
        tomcat.addServlet("", "helloServlet", new HelloServlet()); //서블릿 등록.
        context.addServletMappingDecoded("/hello-servlet", "helloServlet"); //등록한 서블릿의 경로를 매핑.
        tomcat.start();//톰캣 시작.
    }
}
