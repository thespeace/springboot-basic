package thespeace.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <ul>
 *     <li>설정 정보를 동적으로 선택할 수 있게 해주는 ImportSelector 인터페이스를 구현했다.</li>
 *     <li>여기서는 단순히 `thespeace.selector.HelloConfig` 설정 정보를 반환한다.</li>
 *     <li>이렇게 반환된 설정 정보를 선택되어서 사용된다.</li>
 *     <li>여기에 설정 정보로 사용할 클래스를 동적으로 프로그래밍하면 된다.</li>
 * </ul>
 */
public class HelloImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"thespeace.selector.HelloConfig"};
    }
}
