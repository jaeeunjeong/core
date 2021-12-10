package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("setMemberRepository = " + memberRepository);//순서 2
        this.memberRepository = memberRepository;
    }

    @Autowired//(@Autowired(required = false)를 이용해서 주어진 대상이 null이어도 들어갈 수 있음.
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("setDiscountPolicy = " + discountPolicy);//순서 3
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("CONSTRUCTER"); //순서 1
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
