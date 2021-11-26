package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_price() {
        //given
        Member member = new Member(1L, "USER", Grade.VIP);
        //when
        int price = discountPolicy.discount(member, 10000);
        //then
        assertThat(price).isEqualTo(1000);
    }

    @Test
    @DisplayName("basic은 할인이 적용되지 않는다.")
    void basic_price() {
        //given
        Member member = new Member(1L, "USER", Grade.BASIC);
        //when
        int price = discountPolicy.discount(member, 10000);
        //then
        //Assertions.assertThat(price).isEqualTo(1000);
        assertThat(price).isEqualTo(0);
    }
}