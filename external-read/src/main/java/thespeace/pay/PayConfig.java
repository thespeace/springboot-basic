package thespeace.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * <ul>
 *    <li>@Profile 애노테이션을 사용하면 해당 프로필이 활성화된 경우에만 빈을 등록한다.
 *        <ul>
 *            <li>default 프로필(기본값)이 활성화 되어 있으면 LocalPayClient 를 빈으로 등록한다.</li>
 *            <li>prod 프로필이 활성화 되어 있으면 ProdPayClient 를 빈으로 등록한다.</li>
 *        </ul>
 *    </li>
 * </ul><p>
 *
 * <h2>@Profile의 정체</h2>
 * <pre>{@code
 * package org.springframework.context.annotation;
 * ...
 * @Conditional(ProfileCondition.class)
 * public @interface Profile {
 *     String[] value();
 * }
 * }</pre>
 * {@code @Profile} 은 특정 조건에 따라서 해당 빈을 등록할지 말지 선택한다. 어디서 많이 본 것 같지 않은가?<br>
 * 바로 @Conditional 이다.<p>
 * 코드를 보면 @Conditional(ProfileCondition.class) 를 확인할 수 있다.<br>
 * 스프링은 @Conditional 기능을 활용해서 개발자가 더 편리하게 사용할 수 있는 @Profile 기능을 제공하는 것이다.<p><p>
 *
 * <h2>정리</h2>
 * {@code @Profile}을 사용하면 각 환경 별로 외부 설정 값을 분리하는 것을 넘어서, 등록되는 스프링 빈도 분리할 수 있다.
 *
 * @see Profile
 */
@Slf4j
@Configuration
public class PayConfig {

    @Bean
    @Profile("default")
    public LocalPayClient localPayClient() {
        log.info("LocalPayClient 빈 등록");
        return new LocalPayClient();
    }

    @Bean
    @Profile("prod")
    public ProdPayClient prodPayClient() {
        log.info("ProdPayClient 빈 등록");
        return new ProdPayClient();
    }
}
