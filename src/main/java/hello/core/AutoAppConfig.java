package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /**
         * 중요 ! 지정하지 않는 디폴트는 ?
         * @ComponentScan이 붙은 설정 정보 클래스기 준으로 탐색한다.
         * AutoAppConfig.class
         *
         * 권장방법
         * 프로젝트 최상단에 두고 디폴트를 사용한다.
        */
        // 탐색할 시작 위치를 지정할 수 있다
        // 지정하는이유 ! 지정하지 않으면 모든 패키지를 탐색하기떄문에 !
        //basePackages = "hello.core.member",
        //basePackageClasses = AutoAppConfig.class,   // 지정한 클래스 패키지부터 찾음 package hello.core;
        // 기존 예제 코드를 최대한 남기고 유지하기 위해 필터를 사용해서 뺴줌
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

        // memoryMemberRepository라고 자동 빈 등록 해도
        // 수동 빈 등록이 우선권을 가진다.
        // 하지만 실무에서는 수십, 수백개의 빈이 등록되어있는데
        // 이걸 수동적으로 컨트롤하는건 무리 !
        // 그래서 스프링부트에서는 자동으로 수동 빈, 자동 빈이 같은 걸로 등록 됐을 시 오류 발생시킴
//        @Bean(name = "memoryMemberRepository")
//        MemberRepository memberRepository() {
//                return new MemoryMemberRepository();
//        }
}
