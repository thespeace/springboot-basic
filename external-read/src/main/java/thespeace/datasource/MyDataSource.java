package thespeace.datasource;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;

@Slf4j
public class MyDataSource {

    private String url; //접속 url
    private String username; //이름
    private String password; //비밀번호
    private int maxConnection; //최대 연결 수
    private Duration timeout; //응답 지연시 타임아웃
    private List<String> options; //연결시 사용하는 기타 옵션들

    public MyDataSource(String url, String username, String password, int maxConnection, Duration timeout, List<String> options) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxConnection = maxConnection;
        this.timeout = timeout;
        this.options = options;
    }

    /**
     * <h2>확인을 위해 설정된 값을 출력</h2>
     */
    @PostConstruct
    public void init() {
        log.info("url={}", url);
        log.info("username={}", username);
        log.info("password={}", password);
        log.info("maxConnection={}", maxConnection);
        log.info("timeout={}", timeout);
        log.info("options={}", options);
    }
}