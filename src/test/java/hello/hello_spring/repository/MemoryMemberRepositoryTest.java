package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
//장점: class level에서 돌리면 모두 함께 돌릴 수 있음

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){ //각 호출이 끝나고 실행
        repository.clearStore(); //각 호출이 끝날 때 store를 clear하도록 함
        //호출이 순서에 의존적이지 않도록 바꾸어줄 수 있음
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //test code이므로 바로 get으로 꺼냄 (원래는 권장 X)
        Assertions.assertThat(member).isEqualTo(result); //최근 많이 사용
    }

    @Test
    public void findByName(){ //전체 다 실행할 경우 error
        // 순서와 상관 없이 Method별로 실행되도록 해야하는데,
        // 순서에 의존적으로 설계했기 때문
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);

    }

}
