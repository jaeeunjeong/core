package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulServiceSington() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        //각각의 객체에 값을 불러오는 것이기에 둘이 다를 것 같지만, 스프링 빈에서 레퍼런스 타입으로 불러오는지, 같다.
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        statefulService1.order("USER1", 1000);
        statefulService2.order("USER2", 2000);

        int price = statefulService1.getPrice();
        String userid = statefulService1.getName();
        System.out.println("PRICE : " + price);
        System.out.println("USERID : " + userid);

        Assertions.assertThat(statefulService1).isSameAs(statefulService2);
    }
}

class TestConfig {
    @Bean
    public StatefulService statefulService() {
        return new StatefulService();
    }
}