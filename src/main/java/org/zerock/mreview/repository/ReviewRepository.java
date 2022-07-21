package org.zerock.mreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @EntityGraph(attributePaths = { "member" }, type = EntityGraph.EntityGraphType.FETCH) // EntityGraph는 특정한 엔티티의 속성을
                                                                                          // 같이 로딩
    List<Review> findByMovie(Movie movie); // 리뷰에 필요한 데이터 추출

    @Modifying
    @Query("delete from Review mr where mr.member = :member") // 이렇게 쿼리를 지정해줘야 review테이블에서 반복이 일어나지 않고 한번에 삭제됨
    void deleteByMember(Member member); // 회원을 삭제하는 메서드
}
