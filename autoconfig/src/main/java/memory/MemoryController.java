package memory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>메모리 정보를 조회하는 컨트롤러</h1>
 * <ul>
 *     <li>memoryFinder 를 주입 받아 사용.</li>
 *     <li>간단하게 메모리 사용량을 실시간으로 확인할 수 있다.</li>
 * </ul>
 *
 * <h2>패키지 위치</h2>
 * <p>
 *     패키지를 이렇게 나눈 이유는, memory 라는 완전히 별도의 모듈이 있고, hello 에서 memory 의 기능을 불러다
 *     사용한다고 이해하면 된다.
 * </p>
 *
 * @see <a href="http://localhost:8080/memory">test url</a>
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemoryController {

    private final MemoryFinder memoryFinder;

    @GetMapping("/memory")
    public Memory system() {
        Memory memory = memoryFinder.get();
        log.info("memory={}", memory);
        return memory;
    }
}
