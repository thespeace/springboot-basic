package thespeace.boot;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

/**
 * <ul>
 *     <li>컴포넌트 스캔 기능이 추가된 단순한 애노테이션이다.</li>
 *     <li>시작할 때 이 애노테이션을 붙여서 사용하면 된다</li>
 * </ul>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface MySpringBootApplication {
}
