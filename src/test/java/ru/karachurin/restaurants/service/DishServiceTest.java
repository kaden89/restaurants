package ru.karachurin.restaurants.service;




import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static ru.karachurin.restaurants.testData.DishTestData.*;
import static ru.karachurin.restaurants.testData.RestaurantTestData.RESTAURANT1_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;


import java.util.Arrays;
import java.util.List;


/**
 * Created by Денис on 15.11.2016.
 */

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    DishService service;

    @Test
    public void get() throws Exception {
        Dish resultDish = service.get(100005);
        assertThat(resultDish, equalTo(DISH1));
    }

    @Test
    public void delete() throws Exception {
        service.delete(100005);
        assertThat(service.getAll(), is(Arrays.asList(DISH2, DISH3)));
    }

    @Test
    public void update() throws Exception {
        Dish updated = getUpdated();
        service.update(updated);
        assertThat(updated, equalTo(service.get(DISH1_ID)));
    }

    @Test
    public void save() throws Exception {
        Dish created = getCreated();
        service.save(created, 100004);
        assertThat(Arrays.asList(created, DISH1, DISH2, DISH3), is(service.getAll()));
    }

    @Test
    public void getAllFromRestaurant() throws Exception {
        List<Dish> allDishes = (List<Dish>) service.getAllFromRestaurant(RESTAURANT1_ID);
        assertThat(allDishes, is(MENU));
    }

}