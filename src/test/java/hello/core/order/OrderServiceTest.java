package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void test() {
        //given
        Long id = 1L;
        Member member = new Member(id, "USER1", Grade.VIP);

        //when
        memberService.join(member);
        Order order = orderService.createOrder(id, "UEER1", 10000);

        //them
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest(){
        OrderServiceImpl orderService = new OrderServiceImpl();
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"user name",Grade.VIP));
        orderService.setDiscountPolicy(new RateDiscountPolicy());
        orderService.setMemberRepository(memberRepository);
    }

}
