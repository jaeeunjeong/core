package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
/**
 * 컴포넌트스캔 사용하기.
 * @Configuration이 붙은 설정 정보도 자동으로 등록되기에, 이전에 등록한 설정정보들도 등록된다.
 * excludeFilters를 사용해서 컴포넌트스캔 대상에서 제외한다.
 *
 * 또한 @Configuration이 붙은 클래스의 하위 패키지들을 탐색하기에 불필요한 패키지(라이브러리나 테스트 파일 과 같은)를 확인하지 않도록
 * 프로젝트 최상단에 위치시키자.
 * 아니면 위치를 직접 지정할 수 있다.
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        basePackages = "hello.core"
)
public class AutoAppConfig {
}
