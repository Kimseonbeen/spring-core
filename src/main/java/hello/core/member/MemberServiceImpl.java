package hello.core.member;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

public class MemberServiceImpl implements MemberService {

    // 변경 전
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    
    // 변경 후
    private final MemberRepository memberRepository;

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
}
