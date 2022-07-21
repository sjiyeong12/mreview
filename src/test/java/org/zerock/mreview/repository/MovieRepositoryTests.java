package org.zerock.mreview.repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;

@SpringBootTest
public class MovieRepositoryTests {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository imageRepository;

    @Commit
    @Transactional
    @Test
    public void insertMovies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder().title("Movie...." + i).build();
            System.out.println("--------------------");
            movieRepository.save(movie); // 영화와 영화 이미지가 같은 시점에 insert처리가 되어야 하기 때문에 우선 save()해줘야함
            int count = (int) (Math.random() * 5) + 1; // 1,2,3,4 //영화 이미지는 최대 5개까지 임의로 저장됨 그 갯수를 여기서 정함

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder().uuid(UUID.randomUUID().toString()) // 무비이미지 클래스의 uuid불러옴
                        .movie(movie).imgName("test" + j + ".jpg").build(); // 이미지 이름은 test(0, 1, 2, 3, 4) .jpg
                imageRepository.save(movieImage);
            }
            System.out.println("=================================");
        });
    }

    @Test
    public void testListPage() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno")); // mno(영화번호) 기준으로 내림차순 정렬
        Page<Object[]> result = movieRepository.getListPage(pageRequest); // movieRepository에 있는 리스트 페이지 중 윗줄의 페이지 리퀘스트
                                                                          // 불러오기
        for (Object[] objects : result.getContent()) { // 윗줄의 결과를 읽기
            System.out.println(Arrays.toString(objects));
        }

    }

    @Test
    public void testGetgetListPageWithFirstImage() {
        PageRequest req = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
        Page<Object[]> result = movieRepository.getListPageWithFirstImage(req);
        for (Object[] obj : result.getContent()) {
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void testGetListPageWithLatestImage() {
        PageRequest req = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
        Page<Object[]> result = movieRepository.getListPageWithLatestImage(req);
        for (Object[] obj : result.getContent()) {
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void testGetMovieWithAll() {
        List<Object[]> result = movieRepository.getMovieWithAll(94L);
        System.out.println(result);
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

}
