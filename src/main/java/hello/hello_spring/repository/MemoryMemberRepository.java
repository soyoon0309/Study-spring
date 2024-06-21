package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository { //alt + enter로 implement 기능 사용 가능

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //현업에선 동시성 문제로 인해 어텀 long 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id val setting
        store.put(member.getId(), member); //store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환될 가능성을 대비
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 member모두 return
    }
}
