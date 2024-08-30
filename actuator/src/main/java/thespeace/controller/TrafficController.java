package thespeace.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>그라파나 - 메트릭을 통한 문제 확인</h1>
 * 애플리케이션에 문제가 발생했을 때 그라파나를 통해서 어떻게 모니터링 하는지 확인해보자.<br>
 * 실제 우리가 작성한 애플리케이션에 직접 문제를 발생시킨 다음에 그라파나를 통해서 문제를 어떻게 모니터링 할 수 있는지 확인해보자.<br>
 * 실무에서 주로 많이 발생하는 다음 4가지 대표적인 예시를 확인해보자.
 *
 * <ul>
 *     <li>CPU 사용량 초과</li>
 *     <li>JVM 메모리 사용량 초과</li>
 *     <li>커넥션 풀 고갈</li>
 *     <li>에러 로그 급증</li>
 * </ul><br>
 *
 * <h3>정리</h3>
 * 참고: 메트릭을 보는 것은 정확한 값을 보는 것이 목적이 아니다. 대략적인 값과 추세를 확인하는 것이 주 목적이다.
 */
@Slf4j
@RestController
public class TrafficController {

    /**
     * <h2>CPU 사용량 초과</h2>
     * <p>CPU에 간단히 부하를 주는 코드로 각자 컴퓨터 성능에 따라서 루프 횟수를 바꾸어야 할 수 있다.</p>
     *
     * @see <a href="http://localhost:8080/cpu">test url</a>
     */
    @GetMapping("/cpu")
    public String cpu() {
        log.info("cpu");
        long value = 0;
        for (long i = 0; i < 100000000000L; i++) {
            value++;
        }
        return "ok value=" + value;
    }

    private List<String> list = new ArrayList<>();

    /**
     * <h2>JVM 메모리 사용량 초과</h2>
     * <p>메모리 사용을 누적하는 코드</p>
     * <p>
     *     계속 요청하면서 대시보드를 확인해보면 JVM 메모리 사용량이 계속 증가하다가 최대치를 넘는 순간 메트릭이 잡히지 않는다.
     *     JVM 내부에서 OOM이 발생했기 때문이다.
     *     기다려보면 애플리케이션 로그에서 다음과 같은 오류를 확인할 수 있다.
     *     {@code java.lang.OutOfMemoryError: Java heap space}
     * </p>
     *
     * @see <a href="http://localhost:8080/jvm">test url(여러번 실행)</a>
     */
    @GetMapping("/jvm")
    public String jvm() {
        log.info("jvm");
        for (int i = 0; i < 1000000; i++) {
            list.add("hello jvm!" + i);
        }
        return "ok";
    }

    @Autowired DataSource dataSource;

    /**
     * <h2>커넥션 풀 고갈</h2>
     * <p>Active 커넥션이 커넥션 풀의 최대 숫자인 10개를 넘어가게 되면, 커넥션을 획득하기 위해 대기(Pending)하게 된다.
     * 그래서 커넥션 획득 부분에서 쓰레드가 대기하게 되고 결과적으로 HTTP 요청을 응답하지 못한다.</p>
     * <p>DB 커넥션을 획득하기 위해 대기하던 톰캣 쓰레드가 30초 이상 DB 커넥션을 획득하지 못하면 다음과 같은 예외가
     * 발생하면서 커넥션 획득을 포기한다. {@code Connection is not available, request timed out after 30004ms.}</p>
     *
     * @see <a href="http://localhost:8080/jdbc">test url(10번 이상 실행)</a>
     */
    @GetMapping("/jdbc")
    public String jdbc() throws SQLException {
        log.info("jdbc");
        Connection conn = dataSource.getConnection();

        log.info("connection info={}", conn);
        //conn.close(); //커넥션을 닫지 않는다.
        return "ok";
    }

    /**
     * <h2>에러 로그 급증</h2>
     * <p>애플리케이션에서 ERROR 레벨의 로그가 급증한다면 심각한 문제가 발생한 것으로 이해할 수 있다.</p>
     * <p>ERROR Logs , logback_events_total 메트릭에서 ERROR 로그가 급증하는 것을 확인할 수 있다.</p>
     *
     * @see <a href="http://localhost:8080/jdbc">test url(여러번 실행)</a>
     */
    @GetMapping("/error-log")
    public String errorLog() {
        log.error("error log");
        return "error";
    }




}
