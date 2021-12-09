package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component//@Component("memberServiceImpl") 뒤에 괄호를 붙이면 빈의 이름을 지정할 수 있다.
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    //의존 관계를 주입하기 위해 사용(이전에는 설정을 빈에서 직접 등록해서 넣어줬지만, 컴포넌트스캔에서는 알 수 없기에 @AutoWired를 사용해서 주입한다.)
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //싱글톤 확인을 위한 테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
