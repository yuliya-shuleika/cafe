package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.dao.DishDao;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.DishCategory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

@Test
public class DishDaoImplTest {

    private DishDao dishDao;
    private String namePart;
    private int dishId;

    @BeforeTest
    public void init(){
        dishDao = DishDaoImpl.getInstance();
        namePart = "маки";
        dishId = 15;
    }

    @Test
    public void findAllDishesTest() throws DaoException {
        List<Dish> dishes = dishDao.findAllDishes();
        int size = dishes.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findAllDishesSortedByDiscountTest() throws DaoException {
        List<Dish> dishes = dishDao.findAllDishesSortedByDiscount();
        int size = dishes.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findAllDishesSortedByNameTest() throws DaoException {
        List<Dish> dishes = dishDao.findAllDishesSortedByName();
        int size = dishes.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findAllDishesSortedByPopularityTest() throws DaoException {
        List<Dish> dishes = dishDao.findAllDishesSortedByPopularity();
        int size = dishes.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findAllDishesSortedByPriceTest() throws DaoException {
        List<Dish> dishes = dishDao.findAllDishesSortedByPrice();
        int size = dishes.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findDishesByCategoryTest() throws DaoException {
        DishCategory category = DishCategory.SUSHI;
        List<Dish> dishes = dishDao.findDishesByCategory(category);
        int size = dishes.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findDishesByNameTest() throws DaoException {
        List<Dish> dishes = dishDao.findDishesByName(namePart);
        int dishesCount = dishes.size();
        Assert.assertNotEquals(dishesCount, 0);
    }

    @Test
    public void findDishByIdTest() throws DaoException {
        Optional<Dish> dishOptional = dishDao.findDishById(dishId);
        Assert.assertNotEquals(dishOptional, Optional.empty());
    }
}
