package kr.co.darcie.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {
    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber Food", "Seoul"));

        return restaurants;
    }
}
