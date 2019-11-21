package kr.co.darcie.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepositoryImpl implements MenuItemRepository {
    private List<MenuItem> menuItems = new ArrayList<>();
    MenuItemRepositoryImpl(){
        menuItems.add(new MenuItem("Kimchi"));

    }

    @Override
    public List<MenuItem> findAllByRestaurantId(Long restaurantId) {



        return menuItems;
    }
}
