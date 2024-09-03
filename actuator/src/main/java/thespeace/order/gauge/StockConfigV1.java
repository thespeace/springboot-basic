package thespeace.order.gauge;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thespeace.order.OrderService;

/**
 * <h1>메트릭 등록5 - Gauge(게이지)</h1>
 * <ul>
 *     <li>https://prometheus.io/docs/concepts/metric_types/#gauge</li>
 *     <li>게이지는 임의로 오르내릴 수 있는 단일 숫자 값을 나타내는 메트릭</li>
 *     <li>값의 현재 상태를 보는데 사용</li>
 *     <li>값이 증가하거나 감소할 수 있음</li>
 *     <li>예) 차량의 속도, CPU 사용량, 메모리 사용량</li>
 * </ul>
 *
 * 게이지를 만들 때 함수를 전달했는데, 이 함수는 외부에서 메트릭을 확인할 때 마다 호출된다.
 * 이 함수의 반환 값이 게이지의 값이다.<br><br>
 *
 * 참고: 카운터와 게이지를 구분할 때는 값이 감소할 수 있는가를 고민해보면 도움이 된다.<br><br>
 *
 * 애플리케이션을 실행하면 stock gauge call 로그가 주기적으로 남는 것을 확인할 수 있다.<br>
 * 게이지를 확인하는 함수는 외부에서 메트릭을 확인할 때 호출 된다. 현재 프로메테우스가 다음 경로를 통해 주기적으로
 * 메트릭을 확인하기 때문이다.<br>
 * {@link http://localhost:8080/actuator/prometheus} <br>
 * 프로메테우스를 종료해보면 해당 함수가 호출되지 않는 것을 확인할 수 있다. 물론 메트릭 확인 경로를 직접 호출하면
 * 해당 함수가 호출된다.<br>
 * 카운터와 다르게 게이지는 무언가를 누적할 필요도 없고, 딱 현재 시점의 값을 보여주면 된다. 따라서 측정 시점에 현재
 * 값을 반환한다.
 *
 * @see <a href="http://localhost:8080/actuator/metrics/my.stock">액츄에이터 메트릭 확인</a>
 * @see <a href="http://localhost:8080/actuator/prometheus">프로메테우스 포멧 메트릭 확인</a>
 */
@Configuration
public class StockConfigV1 {

    @Bean
    public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry registry) {
        return new MyStockMetric(orderService, registry);
    }

    @Slf4j
    static class MyStockMetric {
        private OrderService orderService;
        private MeterRegistry registry;

        public MyStockMetric(OrderService orderService, MeterRegistry registry) {
            this.orderService = orderService;
            this.registry = registry;
        }

        @PostConstruct
        public void init() {
            Gauge.builder("my.stock", orderService, service -> {
               log.info("stock gauge call");
                int stock = service.getStock().get();
                return stock;
            }).register(registry);
        }
    }

}
