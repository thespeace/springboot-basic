package thespeace.order.V2;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import thespeace.order.OrderService;

@ConfigurationProperties
public class OrderConfigV2 {

    @Bean
    public OrderService orderService() {
        return new OrderServiceV2();
    }

    /**
     * <ul>
     *     <li>CountedAspect 를 등록하면 @Counted 를 인지해서 Counter 를 사용하는 AOP를 적용한다.</li>
     *     <li>주의! CountedAspect를 빈으로 등록하지 않으면 @Counted 관련 AOP가 동작하지 않는다.</li>
     * </ul>
     */
    @Bean
    public CountedAspect countedAspect(MeterRegistry registry) {
        return new CountedAspect(registry);
    }
}
