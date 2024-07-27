package memory;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * <h1>자동 구성 라이브러리 만들기</h1>
 *
 * <h2>@AutoConfiguration</h2>
 * <p>
 *     스프링 부트가 제공하는 자동 구성 기능을 적용할 때 사용하는 애노테이션이다.
 * </p><p><p>
 *
 * <h2>@ConditionalOnProperty</h2>
 * <ul>
 *     <li>memory=on 이라는 환경 정보가 있을 때 라이브러리를 적용한다. (스프링 빈을 등록한다.)</li>
 *     <li>라이브러리를 가지고 있더라도 상황에 따라서 해당 기능을 켜고 끌 수 있게 유연한 기능을 제공한다.</li>
 * </ul>
 */
@AutoConfiguration
@ConditionalOnProperty(name = "memory", havingValue = "on")
public class MemoryAutoConfig {

    @Bean
    public MemoryController memoryController() {
        return new MemoryController(memoryFinder());
    }

    @Bean
    public MemoryFinder memoryFinder() {
        return new MemoryFinder();
    }
}
