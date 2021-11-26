package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 객체의 주입(객체 간의 관계를 설정)을 위함.
 * 하나의 인터페이스를 이용해서 다양한 객체들을 사용할 수 있게 된다.
 * 객체간의 관계 설정을 이 부분에서 해줌으로써, 다수의 코드 변경을 하지 않아도 된다. -> 생성자를 이용해서 필요한 객체들을 변경해준다.
 * 인터페이스만으로 역할을 수행하기위해 객체(실제 역할을 수행할 것)를 지정해주는 부분. -> 필요에 따라 객체를 자유자재로 변경할 수 있다.
 * 싱글톤 패턴
 **/
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
