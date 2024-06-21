package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*; //alt + enter (static import)
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){ //test 실행 시 마다 독립적으로 생성 (실행 전 생성)
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() { //첫 회원가입부터 예외가 발생하지 않도록 비워주어야 함 (중복 검출을 위한 것 제외)
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() { //한국어 사용 가능함 (Test code는 가능)
        //given (이게 주어지면)
        Member member = new Member();
        member.setName("spring");

        //when (이런 상황일 때)
        Long saveId = memberService.join(member);

        //then (이런 결과를 내보내야 함)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2); //error (validate)
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) { //정상적으로 예외가 성공한 경우
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}