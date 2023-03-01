package hello.login.domain.member;

import hello.login.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {                                    // 원래 이거는 클래스가 아닌 Interface로 관리하는게 좋다 나중에 구현체로 DB, 메모리에 회원 관리할 수 있기 때문이다.

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);

        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all = findAll();
//        for (Member member : all) {
//            if (member.getLoginId().equals(loginId)) {
//                return Optional.of(member);
//            }
//        }
//
//        return Optional.empty();                // Optional이란 통이 있고 그 안에 회원 객체를 넣을 수 있다.
        return findAll().stream()                   // List를 Stream이란 것으로 바꾸며 마치 루프를 돈다 거기서 필터 안에 만족하는 얘만 다음 단계로 넘어간다. 만족하지 않은 얘는 버려진다.
                .filter(m -> m.getLoginId().equals(loginId))            // filter DB의 Where 문 느낌
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
