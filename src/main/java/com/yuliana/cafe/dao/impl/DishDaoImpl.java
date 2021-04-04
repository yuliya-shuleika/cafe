package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.dao.DishDao;
import com.yuliana.cafe.entity.DishCategory;
import com.yuliana.cafe.entity.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yuliana.cafe.dao.creator.EntityCreator.createDish;

public class DishDaoImpl implements DishDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_ALL_DISHES = "SELECT dish_id, name, category, picture_name, price, " +
            "discount_percents, date, description, weight " +
            "FROM dishes";
    private static final String SELECT_DISHES_BY_CATEGORY = "SELECT dish_id, name, category, picture_name, " +
            "price, discount_percents, date, description, weight " +
            "FROM dishes WHERE category = ?";
    private static final String SELECT_DISHES_BY_NAME = "SELECT dish_id, name, category, picture_name, price, " +
            "discount_percents, date, description, weight " +
            "FROM dishes WHERE name LIKE ?";
    private static final String SELECT_DISHES_BY_PRICE = "SELECT dish_id, name, category, picture_name, price, " +
            "discount_percents, date, description, weight " +
            "FROM dishes WHERE price > ? and price < ?";
    private static final String SELECT_DISHES_SORTED_BY_PRICE = "SELECT dish_id, name, category, picture_name, " +
            "price, discount_percents, date, description, weight " +
            "FROM dishes ORDER BY price";
    private static final String SELECT_ALL_DISHES_SORTED_BY_NAME = "SELECT dish_id, name, category, " +
            "picture_name, price, discount_percents, date, description, weight " +
            "FROM dishes ORDER BY name";
    private static final String SELECT_DISHES_WITH_DISCOUNT = "SELECT dish_id, name, category, picture_name, " +
            "price, discount_percents, date, description, weight " +
            "FROM dishes WHERE discount_price > 0.0";
    private static final String SELECT_DISHES_WITHOUT_DISCOUNT = "SELECT dish_id, name, category, " +
            "picture_name, price, discount_percents, date, description, weight " +
            "FROM dishes WHERE discount_price = 0.0";
    private static final String SELECT_DISH_BY_ID= "SELECT dish_id, name, category, picture_name, price, " +
            "discount_percents, date, description, weight " +
            "FROM dishes WHERE dish_id = ?";
    private static final String DELETE_DISH_BY_ID= "DELETE FROM dishes WHERE dish_id = ?";
    private static final String INSERT_DISH = "INSERT INTO dishes (name, category, picture_name, price, " +
            "discount_percents, date, description, weight) " +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_DISH = "UPDATE dishes " +
            "SET name = ?, category = ?, picture_name = ?, price = ?, discount_percents = ? " +
            "WHERE dish_id = ?";

    @Override
    public List<Dish> findAllDishes() throws DaoException{
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(SELECT_ALL_DISHES);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findDishesByPrice(double min, double max) throws DaoException{
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DISHES_BY_PRICE)){
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                dishes.add(createDish(result));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findDishesByCategory(DishCategory category) throws DaoException{
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DISHES_BY_CATEGORY)){
            statement.setString(1, category.name().toLowerCase());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                dishes.add(createDish(result));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findDishesByName(String name) throws DaoException{
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DISHES_BY_NAME)){
            String namePattern = '%' + name +'%';
            statement.setString(1, namePattern);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                dishes.add(createDish(result));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findDishesSortedByPrice() throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(SELECT_DISHES_SORTED_BY_PRICE);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findDishesSortedByDiscount() throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(SELECT_DISHES_WITH_DISCOUNT);
            while (result.next()) {
                dishes.add(createDish(result));
            }
            result = statement.executeQuery(SELECT_DISHES_WITHOUT_DISCOUNT);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public Optional<Dish> findDishById(int dishId) throws DaoException {
        Connection connection = pool.getConnection();
        Optional dishOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DISH_BY_ID)){
            statement.setInt(1, dishId);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                Dish dish = createDish(result);
                dishOptional = Optional.ofNullable(dish);
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return dishOptional;
    }

    @Override
    public List<Dish> findDishesSortedByName() throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(SELECT_DISHES_SORTED_BY_PRICE);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public void deleteDishById(int dishId) throws DaoException {
        Connection connection = pool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(DELETE_DISH_BY_ID)){
            statement.setInt(1, dishId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public int addDish(Dish dish) throws DaoException {
        Connection connection = pool.getConnection();
        int dishId;
        try(PreparedStatement statement = connection.prepareStatement(INSERT_DISH)){
            statement.setString(1, dish.getName());
            DishCategory category = dish.getCategory();
            statement.setString(2, category.name().toLowerCase());
            statement.setString(3, dish.getPictureName());
            statement.setDouble(4, dish.getPrice());
            statement.setInt(5, dish.getDiscountPercents());
            dishId = statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishId;
    }

    @Override
    public List<Dish> findAllDishesSortedByName() throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(SELECT_ALL_DISHES_SORTED_BY_NAME);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public void editDish(Dish dish) throws DaoException {
        Connection connection = pool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_DISH)){
            statement.setString(1, dish.getName());
            DishCategory category = dish.getCategory();
            statement.setString(2, category.name().toLowerCase());
            statement.setString(3, dish.getPictureName());
            statement.setDouble(4, dish.getPrice());
            statement.setInt(5, dish.getDiscountPercents());
            statement.setInt(6, dish.getDishId());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }


}
