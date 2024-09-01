package thespeace.order.V3;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import thespeace.order.OrderService;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <ul>
 *     <li>Timer.builder(name) 를 통해서 타이머를 생성한다. name 에는 메트릭 이름을 지정한다.</li>
 *     <li>tag 를 사용했는데, 프로메테우스에서 필터할 수 있는 레이블로 사용된다.</li>
 *     <li>주문과 취소는 메트릭 이름은 같고 tag 를 통해서 구분하도록 했다.</li>
 *     <li>register(registry) : 만든 타이머를 MeterRegistry 에 등록한다. 이렇게 등록해야 실제 동작한다.</li>
 *     <li>타이머를 사용할 때는 timer.record() 를 사용하면 된다. 그 안에 시간을 측정할 내용을 함수로 포함하면 된다.</li>
 * </ul>
 *
 * 걸리는 시간을 확인하기 위해 주문은 0.5초, 취소는 0.2초 대기하도록 했다.<br>
 * 추가로 가장 오래 걸린 시간을 확인하기 위해 sleep() 에서 최대 0.2초를 랜덤하게 더 추가했다.<br>
 * (모두 0.5초로 같으면 가장 오래 걸린 시간을 확인하기 어렵다.)
 */
@Slf4j
public class OrderServiceV3 implements OrderService {

    private final MeterRegistry registry;
    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV3(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void order() {
        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("order")
                .register(registry);

        timer.record(() -> {
            log.info("주문");
            stock.decrementAndGet();
            sleep(500);
        });

    }


    @Override
    public void cancel() {
        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("order")
                .register(registry);

        timer.record(() -> {
            log.info("취소");
            stock.incrementAndGet();
            sleep(200);
        });
    }

    private static void sleep(int l) {
        try {
            Thread.sleep(l + new Random().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
