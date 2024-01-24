package hello.core.member;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 변경 전
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    
    // 변경 후
    private final MemberRepository memberRepository;

    /**
     *
     * @Autowired 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
     * 스프링 컨테이너 안 스프링 빈 저장소에서 같은 타입을 찾는다 !
     * MemoryMemberRepository에 @Component 찾음
     * 이떄, 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다.
     */
    @Autowired  //ac.getBean(MemberRepository.class) 처럼 동작한다고 이해
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

    // 테스트 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
