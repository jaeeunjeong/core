package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

    @Test
    public void singletonClientUsePrototype() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        //여기서 주입된 prototypeBean은 프로토타입이 아니라 싱글톤방식으로 생성된 빈이다.
        //따라서  Assertions.assertThat(count2).isEqualTo(1); 는 false.
        // Assertions.assertThat(count2).isEqualTo(2); 는 true
        ClientBean clientBean2 = ac.getBean((ClientBean.class));
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    static class ClientBean {

        @Autowired
//        private ApplicationContext ac;
        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        public int logic() {
            //싱글톤 빈이 프로토타입을 사용할 때마다 스프링컨테이너에 요청하기.
//            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
