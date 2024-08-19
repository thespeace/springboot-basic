package thespeace.pay;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <h2>PayClient를 사용하는 부분</h2>
 * 상황에 따라서 LocalPayClient 또는 ProdPayClient 를 주입받는다.
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final PayClient payClient;

    public void order(int money) {
        // ...(주문로직)
        payClient.pay(money);
    }
}
