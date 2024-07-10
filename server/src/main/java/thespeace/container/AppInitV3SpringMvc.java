package thespeace.container;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import thespeace.spring.HelloConfig;

/**
 * <h1>스프링 MVC 제공 WebApplicationInitializer 활용</h1>
 * <ul>
 *     <li>spring-web</li>
 *     <li>META-INF/services/jakarta.servlet.ServletContainerInitializer</li>
 *     <li>org.springframework.web.SpringServletContainerInitializer</li>
 * </ul>
 *
 * @see <a href="http://localhost:8080/hello-spring">url</a>
 */
public class AppInitV3SpringMvc implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("AppInitV3SpringMvc.onStartup");

        //스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        //스프링 MVC 디스패처 서블릿 생성, 스프링 컨테이너 연결
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        //디스패처 서블릿을 서블릿 컨테이너에 등록 (이름 주의! dispatcherV3)
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherV3", dispatcher);

        //모든 요청이 디스패처 서블릿을 통하도록 설정
        servlet.addMapping("/");
    }
}
