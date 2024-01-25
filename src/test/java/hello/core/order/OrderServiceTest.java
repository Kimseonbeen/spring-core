package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void BeforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest() {
//        OrderServiceImpl orderService = new OrderServiceImpl();

        /**
         * 필드 주입을 사용하게되면 코드는 간편해질지 모르지만,
         * 테스트 할 때 많은 불편함이 있다.
         * 스프링 환경에서는 자동으로 @AutoWired로 주입해주지만, 테스트할때는 불가능
         * 1. OrderServiceImpl 테스트 시 밑에 처럼 setter를 열어서 자바코드로 주입 해줘야함
         *
         */
        // orderService.setMemberRepository(new MemoryMemberRepository());
        // orderService.setDiscountPolicy(new FixDiscountPolicy());

        orderService.createOrder(1L, "itemA", 10000);
        // error : NullPointerException
    }
}
