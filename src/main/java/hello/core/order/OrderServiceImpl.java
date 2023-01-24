package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 필수값이 붙은 final값에 대한 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService {
// 수정자 주입
//    private MemberRepository memberRepository;
//    private  DiscountPolicy discountPolicy ;
/** @Autowired(required = false)
public void setMemberRepository(MemberRepository memberRepository) {
System.out.println("memberRepository = " + memberRepository);
this.memberRepository = memberRepository;
}
 @Autowired
 public void setDiscountPolicy(DiscountPolicy discountPolicy) {
 System.out.println("discountPolicy = " + discountPolicy);
 this.discountPolicy = discountPolicy;
 }*/


   private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy ;

    //
    //    생성자를 통한  주입.   생성 1순위
        @Autowired  //중요! 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다. 물론 스프링 빈에만 해당한다
        public OrderServiceImpl(MemberRepository memberRepository,  @MainDiscountPolicy DiscountPolicy discountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
