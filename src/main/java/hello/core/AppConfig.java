package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성 한다.
     * AppConfig는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결) 해준다.
     */

    /**
     *  구현객체를 리펙토링을 통해 메서드로 뺀 덕분에
     *  역할과 구현이 한 눈에 보이게 되었다 !
     *  중복도 제거됨
     */


    /**
     * 주의 : 빈 이름은 항상 다른 이름을 부여 !!!
     */

    // @Bean memberService ->  new MemoryMemberRepository()
    // @Bean orderService ->  new MemoryMemberRepository()

    // 우리의 의도
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 실행 결과
    // call AppConfig.memberService
    // call AppConfig.memberRepository  // 중복 3번 호출되어야하는데 1번만 호출이 됨
    // call AppConfig.orderService
    // -> 비밀은 @Configuration
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
         return new RateDiscountPolicy();
    }

}
