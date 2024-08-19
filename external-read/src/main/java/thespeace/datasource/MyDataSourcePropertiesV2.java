package thespeace.datasource;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>외부설정 사용 - @ConfigurationProperties 생성자</h1>
 * 생성자를 만들어 두면 생성자를 통해서 설정 정보를 주입한다.<p><p>
 *
 * {@code @DefaultValue} : 해당 값을 찾을 수 없는 경우 기본값을 사용한다.
 * <ul>
 *     <li>@DefaultValue Etc etc
 *         <ul><li>
 *             etc 를 찾을 수 없을 경우 Etc 객체를 생성하고 내부에 들어가는 값은 비워둔다. ( null , 0 )
 *         </li></ul>
 *     </li>
 *     <li>@DefaultValue("DEFAULT") List<String> options
 *         <ul><li>
 *             options 를 찾을 수 없을 경우 DEFAULT 라는 이름의 값을 사용한다.
 *         </li></ul>
 *     </li>
 * </ul><p><p>
 *
 * <h2>정리</h2>
 * application_backup.properties 에 필요한 외부 설정을 추가하고, @ConfigurationProperties 의 생성자 주입을
 * 통해서 값을 읽어들였다. Setter 가 없으므로 개발자가 중간에 실수로 값을 변경하는 문제가 발생하지 않는다.<p><p>
 *
 * <h2>문제</h2>
 * 타입과 객체를 통해서 숫자에 문자가 들어오는 것 같은 기본적인 타입 문제들은 해결이 되었다. 그런데 타입은 맞는데
 * 숫자의 범위가 기대하는 것과 다르면 어떻게 될까? 예를 들어서 max-connection 의 값을 0 으로 설정하면 커넥션이
 * 하나도 만들어지지 않는 심각한 문제가 발생한다고 가정해보자.<br>
 * max-connection 은 최소 1 이상으로 설정하지 않으면 애플리케이션 로딩 시점에 예외를 발생시켜서 빠르게 문제를
 * 인지할 수 있도록 하고 싶다.<p><p>
 *
 * @참고: @ConstructorBinding<br>
 * 스프링 부트 3.0 이전에는 생성자 바인딩 시에 @ConstructorBinding 애노테이션을 필수로 사용해야 했다.<br>
 * 스프링 부트 3.0 부터는 생성자가 하나일 때는 생략할 수 있다. 생성자가 둘 이상인 경우에는 사용할 생성자에 @ConstructorBinding 애노테이션을 적용하면 된다.
 */
@Getter
@ConfigurationProperties("my.datasource")
public class MyDataSourcePropertiesV2 {

    private String url;
    private String username;
    private String password;
    private Etc etc;

//    @ConstructorBinding //한개는 생략 가능, 두개 부터 작성.
    public MyDataSourcePropertiesV2(String url, String username, String password, @DefaultValue Etc etc) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.etc = etc;
    }

    @Getter
    public static class Etc {
        private int maxConnection;
        private Duration timeout;
        private List<String> options = new ArrayList<>();

        public Etc(int maxConnection, Duration timeout, @DefaultValue("DEFAULT") List<String> options) {
            this.maxConnection = maxConnection;
            this.timeout = timeout;
            this.options = options;
        }
    }
}
