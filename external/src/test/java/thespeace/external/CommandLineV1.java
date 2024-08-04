package thespeace.external;

import lombok.extern.slf4j.Slf4j;

/**
 * <ul>
 *     <li>CommandLine 인수는 스페이스로 구분</li>
 *     <li>java -jar app.jar dataA dataB -> [dataA, dataB] 2개</li>
 *     <li>java -jar app.jar url=devdb -> [url=devdb] 1개</li>
 *     <li>url=devdb 이라는 단어를 개발자가 직접 파싱해야 함</li>
 * </ul>
 */
@Slf4j
public class CommandLineV1 {

    public static void main(String[] args) {
        for (String arg : args) {
            log.info("arg {}", arg);
        }
    }
}
