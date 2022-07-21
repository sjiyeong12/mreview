package org.zerock.mreview.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Member;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> { // 1부터 100까지 회원 설정
            Member member = Member.builder()
                    .email("r" + i + "@zerock.org").pw("1111")// 이메일은 r"(1부터100)"@zerock.org. 비번은1111
                    .nickname("reviewer" + i) // 닉네임은 리뷰어(1부터100까지)
                    .build();
            memberRepository.save(member);
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember() { // 리뷰 삭제 인터페이스
        Long mid = 1L; // Member의 mid
        Member member = Member.builder().mid(mid).build();
        // memberRepository.deleteById(mid); 이렇게만 하면 1번 회원이 작성한 리뷰가 있을 때 에러가 남.
        // reviewRepository.deleteByMember(member); 그래서 트랜젝셔널과 커밋을 추가해야함. fk쪽을 먼저 삭제해야
        // 하기 때문

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
