package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StateFullServiceTest {

    @Test
    @DisplayName("싱글톤 stateful")
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFullService statefulService1 = ac.getBean(StateFullService.class);
        StateFullService statefulService2 = ac.getBean(StateFullService.class);

        //ThreadA : A사용자가 1만원을 주문
        int memberA = statefulService1.order("memberA", 10000);

        //ThreadB :B사용자가 2만원ㅇ르 주문
        int memberB = statefulService2.order("memberB", 20000);

        // //ThreadA : A사용자가 주문 금액 조회 (A가 주문하고 조회하는사이에 B가 끼어든것)
//        int priceA = statefulService1.getPrice();
        System.out.println("priceA = " + memberA);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }


    static class TestConfig{
        @Bean
        public StateFullService stateFullService(){
            return new StateFullService();
        }
    }
}