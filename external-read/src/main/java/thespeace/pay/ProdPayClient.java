package thespeace.pay;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>운영 환경에서는 실제 결제를 시도한다.</p>
 */
@Slf4j
public class ProdPayClient implements PayClient{

    @Override
    public void pay(int money) {
        log.info("운영 결제 money={}", money);
    }
}
