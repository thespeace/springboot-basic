package thespeace.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thespeace.datasource.MyDataSource;

import java.time.Duration;
import java.util.List;

/**
 * <h1>외부설정 사용 - @Value</h1>
 * {@code @Value} 를 사용하면 외부 설정값을 편리하게 주입받을 수 있다.<br>
 * 참고로 @Value 도 내부에서는 Environment 를 사용한다.<br>
 *
 * <ul>
 *     <li>@Value 에 ${} 를 사용해서 외부 설정의 키 값을 주면 원하는 값을 주입 받을 수 있다.</li>
 *     <li>@Value 는 필드에 사용할 수도 있고, 파라미터에 사용할 수도 있다.
 *         <ul><li>myDataSource1() 은 필드에 주입 받은 설정값을 사용한다.</li>
 *             <li>myDataSource2() 는 파라미터를 통해서 설정 값을 주입 받는다.</li></ul>
 *     </li>
 * </ul><p><p>
 *
 * <h3>기본값</h3>
 * 만약 키를 찾지 못할 경우 코드에서 기본값을 사용하려면 다음과 같이 : 뒤에 기본값을 적어주면 된다.
 * <ul>
 *     <li>예) @Value("${my.datasource.etc.max-connection:1}") : key 가 없는 경우 1 을 사용한다.</li>
 * </ul><p><p>
 *
 * <h3>단점</h3>
 * {@code @Value}를 사용하는 방식도 좋지만, @Value 로 하나하나 외부 설정 정보의 키 값을 입력받고, 주입 받아와야 하는 부
 * 분이 번거롭다. 그리고 설정 데이터를 보면 하나하나 분리되어 있는 것이 아니라 정보의 묶음으로 되어 있다. 여기서는
 * my.datasource 부분으로 묶여있다. 이런 부분을 객체로 변환해서 사용할 수 있다면 더 편리하고 더 좋을 것이다.
 */
@Slf4j
@Configuration
public class MyDataSourceValueConfig {

    @Value("${my.datasource.url}")
    private String url;
    @Value("${my.datasource.username}")
    private String username;
    @Value("${my.datasource.password}")
    private String password;
    @Value("${my.datasource.etc.max-connection}")
    private int maxConnection;
    @Value("${my.datasource.etc.timeout}")
    private Duration timeout;
    @Value("${my.datasource.etc.options}")
    private List<String> options;

    @Bean
    public MyDataSource myDataSource1() {
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }

    @Bean
    public MyDataSource myDataSource2(
            @Value("${my.datasource.url}") String url,
            @Value("${my.datasource.username}") String username,
            @Value("${my.datasource.password}") String password,
            @Value("${my.datasource.etc.max-connection}") int maxConnection,
            @Value("${my.datasource.etc.timeout}") Duration timeout,
            @Value("${my.datasource.etc.options}") List<String> options) {
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }

}
