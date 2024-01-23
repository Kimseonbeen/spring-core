package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /**
     * 정액 할인을 정률 할인으로 변경 시 문제점 발견 !
     * 1. 우리는 역할과 구현을 충실하게 분리했다 -> OK
     * 2. 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다 -> OK
     * 3. OCP, DIP 같은 객체지향 설계 원치를 충실히 준수했다
     * --> 그렇게 보이지만 사실은 아니다.
     * DIP: 주문서비스 클라이언트(OrderServiceImpl)는 'DiscountPolicy 인터페이스에 의조하면서 DIP를 잘 지킨거같은데 ??
     * -> 클래스 의존관계를 분석해 보자. 추상(인터페이스)뿐만 아니라 "구체(구현)클래스에도 의존"하고 있다.
     *  - 추상(인터페이스) 의존 : DiscountPolicy
     *  - 구체(구현) 클래스 : FixDiscountPolicy, RateDiscountPolicy
     *
     * OCP: 변경하지 않고 확장할 수 있다고 했는데 ?
     * -> 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다! 따라서 OCP 위반 !
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 변경 !
     * 이렇게 변경시 discountPolicy는 null이라 오류 발생 !
     *
     * 해결 방안 !
     * 누군가가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현객체를 대신 생성하고 주입해주어야한다. !!!
     */
}
