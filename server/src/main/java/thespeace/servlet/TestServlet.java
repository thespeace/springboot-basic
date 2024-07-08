package thespeace.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * <h1>서블릿 등록</h1>
 * 전체 설정이 잘 동작하는지 확인하기 위해 간단한 서블릿을 하나 만들어보자.<br>
 * 웹 서버를 통해 이 서블릿이 실행되어야 한다.<br>
 * 이 서블릿을 실행하려면 톰캣 같은 웹 애플리케이션 서버(WAS)에 이 코드를 배포해야 한다.
 *
 * @see <a href='http://localhost:8080/test'>url</a>
 */
@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.service");
        resp.getWriter().println("test");
    }
}
