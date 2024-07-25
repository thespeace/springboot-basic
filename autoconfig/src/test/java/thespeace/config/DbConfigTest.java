package thespeace.config;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class DbConfigTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    TransactionManager transactionManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * <h2>의존관계 주입 확인</h2>
     * <p>
     *     DbConfig.java의 @Configuration 주석처리하여 우리가 등록한 JdbcTemplate , DataSource , TransactionManager를
     *     스프링 빈으로 등록하지 않았다. 그런데 테스트는 통과하고 있고, 출력결과에 해당 빈들이 존재하는 것을 확인 할 수 있는데,
     *     사실 이 빈들은 모두 스프링 부트가 자동으로 등록해 준 것이다.
     * </p>
     */
    @Test
    void checkBean() {
        log.info("dataSource= {}", dataSource);
        log.info("transactionManager= {}", transactionManager);
        log.info("jdbcTemplate= {}", jdbcTemplate);

        assertThat(dataSource).isNotNull();
        assertThat(transactionManager).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
    }
}
