package org.zerock.mreview.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie") // 연관 관계 주의
public class MovieImage { // 나중에 사용할 영화 이미지에 대한 정보를 기록. FK를 가지기 때문에 @manytoone을 적용해 이를 표시

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;
    private String uuid; // 고유한 번호 생성
    private String imgName;
    private String path; // 이미지 저장 경로는 '년/월/일'의 폴더 구조

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne : 나는 여러개 있지만 그래도 하나를 가리키겠다.
    private Movie movie;

}
