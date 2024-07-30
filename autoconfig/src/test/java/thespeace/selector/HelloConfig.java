package thespeace.selector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>설정 정보</h1>
 * HelloBean을 스프링 빈으로 등록한다.
 */
@Configuration
public class HelloConfig {

    @Bean
    public HelloBean helloBean() {
        return new HelloBean();
    }
}
