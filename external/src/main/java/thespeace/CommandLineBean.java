package thespeace;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * <h1>외부 설정 - 커맨드 라인 옵션 인수와 스프링 부트</h1>
 * 스프링 부트는 커맨드 라인을 포함해서 커맨드 라인 옵션 인수를 활용할 수 있는 ApplicationArguments 를 스프링 빈으로 등록해둔다.<br>
 * 그리고 그 안에 입력한 커맨드 라인을 저장해둔다. 그래서 해당 빈을 주입 받으면 커맨드 라인으로 입력한 값을 어디서든 사용할 수 있다.<p><p>
 *
 * <h2>커맨드 라인 인수</h2>
 * <pre>{@code --url=devdb --username=dev_user --password=dev_pw mode=on}</pre>
 */
@Slf4j
@Component
public class CommandLineBean {

    private final ApplicationArguments arguments;


    public CommandLineBean(ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    @PostConstruct
    public void init() {
        log.info("source {}", List.of(arguments.getSourceArgs()));
        log.info("optionNames {}", arguments.getOptionNames());
        Set<String> optionNames = arguments.getOptionNames();
        for (String optionName : optionNames) {
            log.info("option args {}={}", optionName, arguments.getOptionValues(optionName));
        }
    }
}
