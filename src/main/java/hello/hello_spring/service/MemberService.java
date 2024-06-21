package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //외부에서 넣어주도록
    }

    /*
    * 회원 가입
    * */

    public Long join(Member member) { //회원가입을 하면 ID를 반환해주겠다
        //if 같은 이름이 있는 중복 회원은 안된다. 라는 규칙이 있을 때를 가정.

        /*
        //Optional member로 넘기는 방식
        Optional<Member> result = memberRepository.findByName(member.getName());
        //ctrl + alt + V -> 자동으로 Optional return 생성
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */

        validateDuplicateMember(member); //따로 메소드로 지정

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
        //어차피 optional로 저장되어도 ifPresent를 들어갈 것이기 때문에
        //저장하지 않고 바로 ifPresent로 넘겨버린다
    }

    /*
     * 전체 회원 조회
     * */

    public List<Member> findMembers(){ //전체 members find
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){ //전체 members 중 한 사람 find
        return memberRepository.findById(memberId);
    }
}
