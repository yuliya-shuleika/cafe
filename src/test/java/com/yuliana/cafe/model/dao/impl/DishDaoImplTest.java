package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.dao.DishDao;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.DishCategory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class DishDaoImplTest {

    private DishDao dishDao;

    @BeforeTest
    public void init(){
        dishDao = DishDaoImpl.getInstance();
    }

    @DataProvider
    public static Object[][] dish() {
        int dishId = 20;
        String name = "Сайонара сет";
        DishCategory category = DishCategory.SUSHI_SET;
        double price = 12.20;
        short discount = 12;
        String description = "";
        short flat = 57;
        Dish dish = new Dish();
        return new Object[][]{{dish}};
    }

    @Test
    public void findAllDishesTest() throws DaoException {
        List<Dish> dishes = dishDao.findAllDishes();
        int dishesCount = dishes.size();
        Assert.assertNotEquals(dishesCount, 0);
    }

    @Test(dataProvider = "dish")
    public void addAddressTest(Dish dish) throws DaoException{
        int dishId = dishDao.addDish(dish);
        Assert.assertNotEquals(dishId, 0);
    }

}
