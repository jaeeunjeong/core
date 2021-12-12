package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("CONSTRUCTER"); //순서 1
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

/*
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("init"); //순서 1
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //싱글톤 검증을 위해 사용하기 위한 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
