package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
/**
 * 빈의 스코프를 prototype으로 정하면,
 * 스프링컨테이너에 빈을 조회할 때 생성되고 초기화 메서드도 실행된다.
 * 프로토타입은 스프링 컨테이너가 빈 생성, 초기화, 의존성 주입에만 관여하기 때문에,
 * @PreDestroy와 같은 종료 메서드는 실행되지 않는다.
 *
 * 따라서 빈을 종료할 때, 직접 클라이언트가 호출해줘야한다.
 */
public class PrototypeTest {

    @Test
    public void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("FIND PROTOTYPEBEAN1");

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("FIND PROTOTYPEBEAN1");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotEqualTo(prototypeBean2);

        //종료 메서드를 호출하여 빈 반환.
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
