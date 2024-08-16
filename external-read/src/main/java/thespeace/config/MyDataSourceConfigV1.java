package thespeace.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import thespeace.datasource.MyDataSource;
import thespeace.datasource.MyDataSourcePropertiesV1;

/**
 * <ul>
 *     <li>@EnableConfigurationProperties(MyDataSourcePropertiesV1.class)
 *         <ul><li>
 *             스프링에게 사용할 @ConfigurationProperties 를 지정해주어야 한다. 이렇게 하면 해당 클래스는
 *             스프링 빈으로 등록되고, 필요한 곳에서 주입 받아서 사용할 수 있다.
 *         </li></ul>
 *     </li>
 *     <li>private final MyDataSourcePropertiesV1 properties 설정 속성을 생성자를 통해 주입 받아서 사용한다.</li>
 * </ul>
 */
@Slf4j
@EnableConfigurationProperties(MyDataSourcePropertiesV1.class)
public class MyDataSourceConfigV1 {

    private final MyDataSourcePropertiesV1 properties;

    public MyDataSourceConfigV1(MyDataSourcePropertiesV1 properties) {
        this.properties = properties;
    }

    @Bean
    public MyDataSource dataSource() {
        return new MyDataSource(
                properties.getUrl(),
                properties.getUsername(),
                properties.getPassword(),
                properties.getEtc().getMaxConnection(),
                properties.getEtc().getTimeout(),
                properties.getEtc().getOptions());
    }

}
