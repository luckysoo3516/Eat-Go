package kr.co.darcie.eatgo.interfaces;

import kr.co.darcie.eatgo.domain.Restaurant;
import kr.co.darcie.eatgo.domain.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class RestaurantController {

    private RestaurantRepository repository = new RestaurantRepository();

    @GetMapping("/restaurants")
    public List<Restaurant> list() {

        List<Restaurant> restaurants = repository.findAll();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        List<Restaurant> restaurants = repository.findAll();

        Restaurant restaurant = restaurants.stream()
                .filter(r->r.getId().equals(id))
                .findFirst()
                .orElse(null);

        return restaurant;
    }

}
