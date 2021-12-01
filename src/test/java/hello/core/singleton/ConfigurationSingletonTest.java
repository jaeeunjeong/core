package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    /**
     * 스프링컨테이너에 등록된 빈들은 의존관계에 있는 빈들을 각각 생성할 것이라고 판단되지만, 실제로는 그렇지 않다.
     * 아래의 테스트코드에서 MemberServiceImpl, OrderServiceImpl, MemberRepository 모두 MemberRepository를 사용하는데
     * 개발의 memberRepository를 사용할 것 같지만, 하나의 memberRepository을 사용한다.
     * 이는 아래의 테스트에서 알 수 잇는 CGLIB에서 싱글톤 방식으로 객체를 생성하기 떄문이다.
     **/
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository",MemberRepository.class);

        System.out.println("memberService.getMemberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService.getMemberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    /**
     * class hello.core.AppConfig$$EnhancerBySpringCGLIB$$d9fa2add
     * 단순히 AppConfig로 끝나지 않고 뒤에 ~~~CGLIB가 붙는다.
     * CGLIB 덕에 싱클톤이 보장된다.
     * @Configuration을 통해 CGLIB 기술을 이용해서 싱글톤을 보장한다.
     * **/
    @Test
    void configurationSetting(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
