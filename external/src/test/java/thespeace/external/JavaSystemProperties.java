package thespeace.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class JavaSystemProperties {

    /**
     * <ul>
     *     <li>System.getProperties() 를 사용하면 Map 과 유사한( Map 의 자식 타입) key=value 형식의
     *         Properties 를 받을 수 있다. 이것을 통해서 모든 자바 시스템 속성을 조회할 수 있다.</li>
     *     <li>System.getProperty(key) 를 사용하면 속성값을 조회할 수 있다.</li>
     * </ul>
     */
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            log.info("prop {}={}", key, System.getProperty(String.valueOf(key)));
        }

        //JavaSystemProperties 추가, 실행할 때 자바 시스템 속성을 추가해야 한다.(-D 옵션을 통해 자바 시스템 속성들 추가)
        String url = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        log.info("url={}", url);
        log.info("username={}", username);
        log.info("password={}", password);
    }
}
