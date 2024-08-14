package thespeace.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import thespeace.datasource.MyDataSource;

import java.time.Duration;
import java.util.List;

@Slf4j
@Configuration
public class MyDataSourceEnvConfig {

    private final Environment env;

    public MyDataSourceEnvConfig(Environment env) {
        this.env = env;
    }

    /**
     * <h2>MyDataSource 를 스프링 빈으로 등록하는 자바 설정</h2>
     * Environment 를 사용하면 외부 설정의 종류와 관계없이 코드 안에서 일관성 있게 외부 설정을 조회할 수 있다.<p><p>
     *
     * Environment.getProperty(key, Type) 를 호출할 때 타입 정보를 주면 해당 타입으로 변환해준다. (스프링 내부 변환기가 작동한다.)
     * <ul>
     *     <li>env.getProperty("my.datasource.etc.max-connection", Integer.class) : 문자 -> 숫자로 변환</li>
     *     <li>env.getProperty("my.datasource.etc.timeout", Duration.class) : 문자 -> Duration (기간) 변환</li>
     *     <li>env.getProperty("my.datasource.etc.options", List.class) : 문자 ->0 List 변환 ( A,B -> [A,B] )</li>
     * </ul>
     * 스프링은 다양한 타입들에 대해서 기본 변환 기능을 제공한다.
     *
     * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties.conversion">
     *     속성 변환기 - 스프링 공식 문서</a>
     */
    @Bean
    public MyDataSource myDataSource() {
        String url = env.getProperty("my.datasource.url");
        String username = env.getProperty("my.datasource.username");
        String password = env.getProperty("my.datasource.password");
        int maxConnection = env.getProperty("my.datasource.etc.max-connection", Integer.class);
        Duration timeout = env.getProperty("my.datasource.etc.timeout", Duration.class);
        List<String> options = env.getProperty("my.datasource.etc.options", List.class);
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }
}
