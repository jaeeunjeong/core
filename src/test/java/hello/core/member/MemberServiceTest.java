package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memverService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memverService.join(member);
        Member findMember = memverService.findMember(1L);

        //them
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}