package thespeace.datasource;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>외부설정 사용 - @ConfigurationProperties 검증</h1>
 * {@code @ConfigurationProperties} 를 통해서 숫자가 들어가야 하는 부분에 문자가 입력되는 문제와 같은 타입이 맞지
 * 않는 데이터를 입력하는 문제는 예방할 수 있다. 그런데 문제는 숫자의 범위라던가, 문자의 길이 같은 부분은 검증이 어
 * 렵다.<br>
 * 예를 들어서 최대 커넥션 숫자는 최소 1 최대 999 라는 범위를 가져야 한다면 어떻게 검증할 수 있을까? 이메일을 외부
 * 설정에 입력했는데, 만약 이메일 형식에 맞지 않는다면 어떻게 검증할 수 있을까?<br>
 * 개발자가 직접 하나하나 검증 코드를 작성해도 되지만, 자바에는 자바 빈 검증기(java bean validation)이라는 훌륭한
 * 표준 검증기가 제공된다.<p><p>
 *
 * {@code @ConfigurationProperties}은 자바 객체이기 때문에 스프링이 자바 빈 검증기를 사용할 수 있도록 지원한다.<p><p>
 *
 * <h2>jakarta.validation</h2>
 * 패키지 이름에 jakarta.validation 으로 시작하는 것은 자바 표준 검증기에서 지원하는 기능이다.<p><p>
 *
 * <h2>org.hibernate.validator</h2>
 * 패키지 이름에 org.hibernate.validator 로 시작하는 것은 자바 표준 검증기에서 아직 표준화 된 기능은 아니고,
 * 하이버네이트 검증기라는 표준 검증기의 구현체에서 직접 제공하는 기능이다. 대부분 하이버네이트 검증기를 사용하므로
 * 이 부분이 크게 문제가 되지는 않는다.<p><p>
 *
 * <h2>정리</h2>
 * ConfigurationProperties 덕분에 타입 안전하고, 또 매우 편리하게 외부 설정을 사용할 수 있다.
 * 그리고 검증기 덕분에 쉽고 편리하게 설정 정보를 검증할 수 있다.<br>
 * 가장 좋은 예외는 컴파일 예외, 그리고 애플리케이션 로딩 시점에 발생하는 예외이다.
 * 가장 나쁜 예외는 고객 서비스 중에 발생하는 런타임 예외이다.<p><p>
 *
 * <h2>ConfigurationProperties 장점</h2>
 * <ul>
 *     <li>외부 설정을 객체로 편리하게 변환해서 사용할 수 있다.</li>
 *     <li>외부 설정의 계층을 객체로 편리하게 표현할 수 있다.</li>
 *     <li>외부 설정을 타입 안전하게 사용할 수 있다.</li>
 *     <li>검증기를 적용할 수 있다.</li>
 * </ul>
 */
@Getter
@ConfigurationProperties("my.datasource")
@Validated
public class MyDataSourcePropertiesV3 {

    @NotEmpty //필수 값
    private String url;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Etc etc;

    public MyDataSourcePropertiesV3(String url, String username, String password, Etc etc) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.etc = etc;
    }

    @Getter
    public static class Etc {
        @Min(1)
        @Max(999) //값의 허용 범위
        private int maxConnection;
        @DurationMin(seconds = 1)
        @DurationMax(seconds = 60)
        private Duration timeout;
        private List<String> options = new ArrayList<>();

        public Etc(int maxConnection, Duration timeout, @DefaultValue("DEFAULT") List<String> options) {
            this.maxConnection = maxConnection;
            this.timeout = timeout;
            this.options = options;
        }
    }
}
