package thespeace.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class OsEnv {

    /**
     * <ul>
     *     <li>System.getenv() 를 사용하면 전체 OS 환경 변수를 Map 으로 조회할 수 있다.</li>
     *     <li>System.getenv(key) 를 사용하면 특정 OS 환경 변수의 값을 String 으로 조회할 수 있다.</li>
     * </ul>
     */
    public static void main(String[] args) {
        Map<String, String> envMap = System.getenv();
        for (String key : envMap.keySet()) {
            log.info("env {}={}", key, System.getenv(key));
        }

        //DBURL=dev.db.com 개발서버
        //DBURL=prod.db.com 운영서버
        String dburl = System.getenv("DBURL");
        System.out.println(dburl);
    }
}
