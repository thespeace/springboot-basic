package thespeace.config;

import memory.MemoryController;
import memory.MemoryFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>라이브러리 설정</h1>
 * 스프링 부트 자동 구성을 사용하는 것이 아니기 때문에 빈을 직접 하나하나 등록해주어야 한다.<p><p>
 *
 * <h2>정리</h2>
 * <ul>
 *     <li>외부 라이브러리를 직접 만들고 또 그것을 프로젝트에 라이브러리로 불러서 적용해보았다.</li>
 *     <li>그런데 라이브러리를 사용하는 클라이언트 개발자 입장을 생각해보면, 라이브러리 내부에 있는 어떤 빈을
 *         등록해야하는지 알아야 하고, 그것을 또 하나하나 빈으로 등록해야 한다. 지금처럼 간단한 라이브러리가 아니라
 *         초기 설정이 복잡하다면 사용자 입장에서는 상당히 귀찮은 작업이 될 수 있다.</li>
 *     <li>이런 부분을 자동으로 처리해주는 것이 바로 스프링 부트 자동 구성(Auto Configuration)이다.</li>
 * </ul>
 */
@Configuration
public class MemoryConfig {

    @Bean
    public MemoryFinder memoryFinder() {
        return new MemoryFinder();
    }

    @Bean
    public MemoryController memoryController() {
        return new MemoryController(memoryFinder());
    }
}
