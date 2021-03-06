package kr.co.darcie.eatgo.application;

import kr.co.darcie.eatgo.application.ReviewService;
import kr.co.darcie.eatgo.domain.Review;
import kr.co.darcie.eatgo.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReviewServiceTests {


    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview() {
        Review review = Review.builder()
                .name("Jocker")
                .score(3)
                .description("JMT")
                .build();

        reviewService.addReview(1004L, review);

        verify(reviewRepository).save(any());
    }

}