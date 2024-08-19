package thespeace.pay;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>로컬 개발 환경에서는 실제 결제를 하지 않는다.</p>
 */
@Slf4j
public class LocalPayClient implements PayClient{

    @Override
    public void pay(int money) {
        log.info("로컬 결제 money={}", money);
    }
}
