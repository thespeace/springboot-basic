package thespeace.order.V0;

import lombok.extern.slf4j.Slf4j;
import thespeace.order.OrderService;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV0 implements OrderService {

    private AtomicInteger stock = new AtomicInteger(100);

    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();
    }

    @Override
    public void cancel() {
        log.info("주문");
        stock.incrementAndGet();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
