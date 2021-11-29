package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
/**
 * 스프링의 ApplicationContext는 BeanFactory를 상속받는데, 두 인터페이스의 목적은 객체를 관리하는 것에 있다.
 * 이 둘을 AnnotationConfigApplicationContext이나 GenericXmlApplicationContext등이 상속받아 사용하기에
 * bean 설정을 기호에 맞게(java코드 형식이나 xml 형식과 같은) 해줄 수 있다.
 **/
public class XmlAppConfig {
    @Test
    @DisplayName("XML을 이용하여 설정을 해본다.")
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
