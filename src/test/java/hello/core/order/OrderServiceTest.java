package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

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
}
