package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        //회원을 만들고
        //가입한 후
        //주문을 만듦.
        //sout로 잘 만들어졌는지 확인.

        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        Long id = 1L;
        Member member = new Member(id, "USER1", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(id, "USER1", 10000);

        System.out.println("order = "+ order);
    }
}
