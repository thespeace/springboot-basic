package thespeace.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

/**
 * <h1>JVM 내부에서 동작하는 메모리 DB를 사용</h1>
 * <ul>
 *     <li>메모리 모드로 동작 옵선: jdbc:h2:mem:test</li>
 *     <li>JdbcTransactionManager 는 DataSourceTransactionManager 와 같은 것으로 생각하면
 *         된다. 여기에 예외 변환 기능이 보강되었다.</li>
 *     <li>JdbcTemplate 을 사용해서 회원 데이터를 DB에 보관하고 관리하는 기능으로<br>
 *         DataSource , TransactionManager , JdbcTemplate 을 스프링 빈으로 직접 등록한다.</li>
 *     <li>회원 데이터를 DB에 보관하고 관리하기 위해 앞서 빈으로 등록한 JdbcTemplate , DataSource , TransactionManager 가 모두 사용되었다.
 *         그런데 생각해보면 DB에 데이터를 보관하고 관리하기 위해 이런 객체들을 항상 스프링 빈으로 등록해야 하는 번거로움이 있다.
 *         만약 DB를 사용하는 다른 프로젝트를 진행한다면 이러한 객체들을 또 스프링 빈으로 등록해야 할 것이다. 이러한 번거로움은
 *         나중에 스프링 부트로 해결해보자.</li>
 * </ul>
 */
@Slf4j
//@Configuration
public class DbConfig {

    @Bean
    public DataSource dataSource() {
        log.info("dataSource 빈 등록");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setJdbcUrl("jdbc:h2:mem:test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public TransactionManager transactionManager() {
        log.info("transactionManager 빈 등록");
        return new JdbcTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        log.info("jdbcTemplate 빈 등록"); //호출 확인용
        return new JdbcTemplate(dataSource());
    }
}
