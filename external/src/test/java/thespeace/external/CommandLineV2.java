package thespeace.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Set;

@Slf4j
public class CommandLineV2 {

    /**
     * <p>스프링이 제공하는 ApplicationArguments 인터페이스와 DefaultApplicationArguments 구현체를 사용
     * 하면 커맨드 라인 옵션 인수를 규격대로 파싱해서 편리하게 사용할 수 있다.</p><p>
     *
     * <h2>실행</h2>
     * 커맨드 라인 인수를 다음과 같이 입력하고 실행해보자.<br>
     * <pre>{@code --url=devdb --username=dev_user --password=dev_pw mode=on}</pre>
     * 이해를 돕기 위해 -- (dash)가 없는 mode=on 이라는 옵션도 마지막에 추가했다.<p><p>
     *
     * 여기서 커맨드 라인 옵션 인수와, 옵션 인수가 아닌 것을 구분할 수 있다.
     * <ul>
     *     <li>옵션 인수 : -- 로 시작한다.</li>
     *     <li>옵션 인수가 아님 : -- 로 시작하지 않는다.</li>
     * </ul><p><p>
     *
     * <h2>참고</h2>
     * <ul>
     *     <li>참고로 옵션 인수는 --username=userA --username=userB 처럼 하나의 키에 여러 값을 포함할 수
     *         있기 때문에 appArgs.getOptionValues(key) 의 결과는 리스트( List )를 반환한다.</li>
     *     <li>커맨드 라인 옵션 인수는 자바 언어의 표준 기능이 아니다. 스프링이 편리함을 위해 제공하는 기능이다.</li>
     * </ul>
     */
    public static void main(String[] args) {
        for (String arg : args) { //커맨드 라인의 입력 결과를 그대로 출력.
            log.info("arg {}", arg);
        }

        DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
        log.info("SourceArgs = {}", List.of(appArgs.getSourceArgs())); //커맨드 라인 인수 전부를 출력한다.
        log.info("NonOptionArgs = {}", appArgs.getNonOptionArgs()); //옵션 인수가 아니다. key=value 형식으로 파싱되지 않는다. -- 를 앞에 사용하지 않았다.
        log.info("OptionNames = {}", appArgs.getOptionNames()); //key=value 형식으로 사용되는 옵션 인수다. -- 를 앞에 사용했다.

        Set<String> optionNames = appArgs.getOptionNames();
        for (String optionName : optionNames) {
            log.info("option args {}={}", optionName, appArgs.getOptionValues(optionName));
        }

        //url , username , password 는 옵션 인수이므로 appArgs.getOptionValues(key) 로 조회할 수 있다.
        //mode 는 옵션 인수가 아니므로 appArgs.getOptionValues(key) 로 조회할 수 없다. 따라서 결과는 null 이다.
        List<String> url = appArgs.getOptionValues("url");
        List<String> username = appArgs.getOptionValues("username");
        List<String> password = appArgs.getOptionValues("password");
        List<String> mode = appArgs.getOptionValues("mode");

        log.info("url={}", url);
        log.info("username={}", username);
        log.info("password={}", password);
        log.info("mode={}", mode);
    }
}
