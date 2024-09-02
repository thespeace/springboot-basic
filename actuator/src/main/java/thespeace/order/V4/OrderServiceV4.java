package thespeace.order.V4;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import thespeace.order.OrderService;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>타이머는 @Timed 라는 애노테이션을 통해 AOP를 적용할 수 있다.</p><br>
 *
 * <p>@Timed("my.order") 타입이나 메서드 중에 적용할 수 있다.<br>
 * 타입에 적용하면 해당 타입의 모든 public 메서드에 타이머가 적용된다. 참고로 이 경우 getStock() 에도 타이머가 적용된다.</p>
 */
@Timed("my.order")
@Slf4j
public class OrderServiceV4 implements OrderService {

    private AtomicInteger stock = new AtomicInteger(100);

    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();
        sleep(500);
    }

    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();
        sleep(200);
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
