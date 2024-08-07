package thespeace;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnvironmentCheck {

    private final Environment env;

    public EnvironmentCheck(Environment env) {
        this.env = env;
    }

    /**
     * <h2>커맨드 라인 옵션 인수 실행</h2>
     * <pre>{@code --url=devdb --username=dev_user --password=dev_pw}</pre>
     * <p>
     *
     * <h2>자바 시스템 속성 실행</h2>
     * <pre>{@code -Durl=devdb -Dusername=dev_user -Dpassword=dev_pw}</pre>
     *
     */
    @PostConstruct
    public void init() {
        String url = env.getProperty("url");
        String username = env.getProperty("username");
        String password = env.getProperty("password");

        log.info("env url={}", url);
        log.info("env username={}", username);
        log.info("env password={}", password);
    }
}
