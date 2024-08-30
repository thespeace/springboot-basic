package thespeace.order;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h2>주문, 취소, 재고 수량을 확인할 수 있는 주문 서비스 인터페이스</h2>
 */
public interface OrderService {
    void order();
    void cancel();
    AtomicInteger getStock(); //멀티쓰레드 상황에서 안전하기 값들을 증가, 감소시키기 위함.
}
