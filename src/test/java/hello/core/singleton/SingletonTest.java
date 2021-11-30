package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너 : 일반적으로 계속해서 객체를 생성하여 사용한다.")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        //객체 정보 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //Assertions.assertThat(memberService1).isSameAs(memberService2);//에러 발생
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void useSingletone(){
        //SingletonService singletonService1 = new SingletonService();
        //-> 'SingletonService()' has private access in 'hello.core.singleton.SingletonService
        // : 위와 같은 에러가 뜨면서 객체 생성이 불가능하다.
        //-> 따라서 인스턴스를 불러와야함.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //객체 정보 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
        singletonService2.logic();
    }
}
