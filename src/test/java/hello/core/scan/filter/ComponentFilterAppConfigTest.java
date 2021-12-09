package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        //No bean named 'beanB' available 에러 발생 ->  excludeFilter의 ANNOTATION가 원인. : 기본값, 애노테이션을 인식해서 동작한다
        // @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =  MyExcludeComponent.class)
        //BeanB beanB = ac.getBean("beanB", BeanB.class);
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));

        //No bean named 'beanB' available 에러 발생 -> excludeFilters의  ASSIGNABLE_TYPE 가 원인 : 지정한 타입과 자식 타입을 인식해서 동작한다
        //@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanC.class)
        //BeanC beanC = ac.getBean("beanC", BeanC.class);
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanC", BeanC.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =  MyIncludeComponent.class),
            excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =  MyExcludeComponent.class),
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanC.class)
            }

    )
    static class ComponentFilterAppConfig {
    }
}
