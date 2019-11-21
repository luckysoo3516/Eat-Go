package kr.co.darcie.eatgo.application;

import kr.co.darcie.eatgo.domain.MenuItem;
import kr.co.darcie.eatgo.domain.MenuItemRepository;
import kr.co.darcie.eatgo.domain.Restaurant;
import kr.co.darcie.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();


        return restaurants;
    }

    public Restaurant getRestaurant(Long id){

        Restaurant restaurant = restaurantRepository.findById(id);

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);

        restaurant.setMenuItems(menuItems);

        return restaurant;
    };
}
