package kr.co.darcie.eatgo.interfaces;

import kr.co.darcie.eatgo.application.RestaurantService;
import kr.co.darcie.eatgo.domain.MenuItem;
import kr.co.darcie.eatgo.domain.Restaurant;
import kr.co.darcie.eatgo.domain.RestaurantNotFoundException;
import kr.co.darcie.eatgo.domain.Review;
import kr.co.darcie.eatgo.interfaces.RestaurantController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    // Mock 객체 주입. Controller 직접 의존하는 Service를 가짜로 투입
    @MockBean
    private RestaurantService restaurantService;


    @Test
    public void list() throws Exception {

        List<Restaurant> restuarants = new ArrayList<>();
        restuarants.add(
                Restaurant.builder()
                        .id(1004L)
                        .name("JOCKER House")
                        .address("Seoul")
                        .build());

        given(restaurantService.getRestaurants()).willReturn(restuarants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004"))
                )
                .andExpect(content().string(
                        containsString("\"name\":\"JOCKER House\""))
                );

    }

    @Test
    public void detailWithExisted() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("JOCKER House")
                .address("Seoul")
                .build();
        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();
        restaurant.setMenuItems(Arrays.asList(menuItem));
        Review review = Review.builder()
                .name("JOKER")
                .score(5)
                .description("Great!")
                .build();
        restaurant.setReviews(Arrays.asList(review));


        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);


        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")))
                .andExpect(content().string(
                        containsString("\"name\":\"JOCKER House\"")))
                .andExpect(content().string(
                        containsString("Kimchi"))
                )
                .andExpect(content().string(
                        containsString("Great!"))
                );

    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));
        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

}