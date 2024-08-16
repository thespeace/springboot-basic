package thespeace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import thespeace.config.MyDataSourceConfigV1;
import thespeace.config.MyDataSourceEnvConfig;
import thespeace.config.MyDataSourceValueConfig;

//@Import(MyDataSourceEnvConfig.class) //설정 정보를 빈으로 등록해서 사용하기 위해 추가.
//@Import(MyDataSourceValueConfig.class)
@Import(MyDataSourceConfigV1.class)
@SpringBootApplication(scanBasePackages = "thespeace.datasource") //설정 정보를 바꾸면서 사용하기 위해 스캔 위치 설정.
public class ExternalReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalReadApplication.class, args);
    }

}
