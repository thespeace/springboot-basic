package thespeace.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 *     <li>외부 설정을 주입 받을 객체를 생성한다. 그리고 각 필드를 외부 설정의 키 값에 맞추어 준비한다.</li>
 *     <li>@ConfigurationProperties 이 있으면 외부 설정을 주입 받는 객체라는 뜻이다.<br>
 *         여기에 외부 설정 KEY의 묶음 시작점인 my.datasource 를 적어준다</li>
 *     <li>기본 주입 방식은 자바빈 프로퍼티 방식이다. Getter , Setter 가 필요하다.<br>
 *         (롬복의 @Data 에 의해 자동 생성된다.)</li>
 * </ul>
 */
@Data
@ConfigurationProperties("my.datasource")
public class MyDataSourcePropertiesV1 {

    private String url;
    private String username;
    private String password;
    private Etc etc = new Etc();

    @Data
    public static class Etc {
        private int maxConnection;
        private Duration timeout;
        private List<String> options = new ArrayList<>();
    }
}
