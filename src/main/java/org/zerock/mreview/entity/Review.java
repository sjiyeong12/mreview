package org.zerock.mreview.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = { "movie", "member" }) // 스트링 호출 시에는 다른 엔티티를 사용하지 않도록 exclude속성을 지정
public class Review extends BaseEntity { // 리뷰는 movie클래스와 member클래스 양쪽 다 참조
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riviewnum; // 리뷰번호 고유키

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie; // 무비클래스의 영화
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member; // 멤버 클래스의 회원

    private int grade; // 평점
    private String text; // 리뷰 글
}
