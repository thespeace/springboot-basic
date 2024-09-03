package thespeace.order.gauge;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thespeace.order.OrderService;

/**
 * <h1>gauge(게이지) 단순하게 등록하기</h1>
 * MeterBinder 타입을 바로 반환해도 된다.<p><p>
 *
 * <h2>그라파나 등록하기</h2>
 * 패널 옵션 - {@code Title : 재고}<br>
 * PromQL - {@code my_stock}
 */
@Slf4j
@Configuration
public class StockConfigV2 {

    @Bean
    public MeterBinder stockSize(OrderService orderService) {
        return registry -> Gauge.builder("my.stock", orderService, service -> {
            log.info("stock gauge call");
            return service.getStock().get();
        }).register(registry);
    }
}
