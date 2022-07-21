package org.zerock.mreview.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {
        // 200개의 리뷰 등록
        IntStream.rangeClosed(1, 200).forEach(i -> { // 200개의 영화리뷰 저장
            // 영화 번호
            Long mno = (long) (Math.random() * 100) + 1; // 영화 번호는 임의의 값으로 현제 db에 존재하는 값으로 생성해서 처리

            // 리뷰어 번호
            Long mid = ((long) (Math.random() * 100) + 1); // 회원(리뷰어) 번호는 임의의 값으로 현제 db에 존재하는 값으로 생성해서 처리
            Member member = Member.builder().mid(mid).build(); // 위에서 설정된 회원번호로 회원 불러오기

            // movieReview객체 생성
            Review movieReview = Review.builder().member(member) // 위에서 불러진 회원(member) 불러오기
                    .movie(Movie.builder().mno(mno).build()) // 위의 영화번호(mno)를 토대로 영화 불러오기
                    .grade((int) (Math.random() * 5) + 1) // 평점1~5점 중 랜덤으로 입력
                    .text("이 영화에 대한 느낌..." + i).build(); // 리뷰 "이 영화에 대한 느낌(1부터 200까지)"

            reviewRepository.save(movieReview);
        });
    }

    @Test
    public void testGetMovieReviews() {
        Movie movie = Movie.builder().mno(92L).build(); // 영화번호 92 추출

        List<Review> result = reviewRepository.findByMovie(movie); // 영화번호 92로 리뷰 추출

        result.forEach(movieReview -> {
            System.out.println(movieReview.getRiviewnum()); // 리뷰번호
            System.out.println("\t" + movieReview.getGrade()); // 리뷰 평점
            System.out.println("\t" + movieReview.getText()); // 리뷰 텍스트
            System.out.println("\t" + movieReview.getMember().getEmail()); // 멤버를 통해 이메일을 가져옴
            System.out.println("-----------------------------");
        });
    }
}
