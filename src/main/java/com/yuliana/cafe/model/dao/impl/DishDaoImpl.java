package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.model.connection.ConnectionPool;
import com.yuliana.cafe.model.dao.DishDao;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.DishCategory;
import com.yuliana.cafe.exception.DaoException;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@code DishDao} interface.
 *
 * @author Yulia Shuleiko
 */
public class DishDaoImpl implements DishDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final DishDaoImpl INSTANCE = new DishDaoImpl();
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
            "FROM dishes WHERE discount_percents > 0";
    private static final String SELECT_DISHES_WITHOUT_DISCOUNT = "SELECT dish_id, name, category, " +
            "picture_name, price, discount_percents, date, description, weight " +
            "FROM dishes WHERE discount_percents = 0";
    private static final String SELECT_DISH_BY_ID = "SELECT dish_id, name, category, picture_name, price, " +
            "discount_percents, date, description, weight " +
            "FROM dishes WHERE dish_id = ?";
    private static final String DELETE_DISH_BY_ID = "DELETE FROM dishes WHERE dish_id = ?";
    private static final String INSERT_DISH = "INSERT INTO dishes (name, category, picture_name, price, " +
            "discount_percents, date, description, weight) " +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_DISH = "UPDATE dishes " +
            "SET name = ?, category = ?, picture_name = ?, price = ?, discount_percents = ?, description = ?, weight = ? " +
            "WHERE dish_id = ?";
    private static final String SELECT_NEW_DISHES = "SELECT dish_id, name, category, picture_name, price, " +
            "discount_percents, date, description, weight " +
            "FROM dishes WHERE date > ? ORDER BY date DESC";
    private static final String SELECT_ALL_DISHES_SORTED_BY_POPULARITY = "SELECT dishes.dish_id, " +
            "dishes.name, dishes.category, dishes.picture_name, dishes.price, " +
            "dishes.discount_percents, dishes.date, dishes.description, dishes.weight " +
            "FROM ordered_dishes JOIN dishes ON ordered_dishes.dish_id = dishes.dish_id " +
            "GROUP BY ordered_dishes.dish_id ORDER BY SUM(ordered_dishes.count) DESC";

    /**
     * Forbid creation of the new objects of the class.
     */
    private DishDaoImpl(){}

    public static DishDaoImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Dish> findAllDishes() throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_ALL_DISHES);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findDishesByPrice(double min, double max) throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DISHES_BY_PRICE)) {
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                dishes.add(createDish(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findDishesByCategory(DishCategory category) throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DISHES_BY_CATEGORY)) {
            statement.setString(1, category.name().toLowerCase());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                dishes.add(createDish(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findDishesByName(String name) throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DISHES_BY_NAME)) {
            String namePattern = '%' + name + '%';
            statement.setString(1, namePattern);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                dishes.add(createDish(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findAllDishesSortedByPrice() throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_DISHES_SORTED_BY_PRICE);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findAllDishesSortedByDiscount() throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_DISHES_WITH_DISCOUNT);
            while (result.next()) {
                dishes.add(createDish(result));
            }
            result = statement.executeQuery(SELECT_DISHES_WITHOUT_DISCOUNT);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public Optional<Dish> findDishById(int dishId) throws DaoException {
        Connection connection = pool.getConnection();
        Optional dishOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DISH_BY_ID)) {
            statement.setInt(1, dishId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Dish dish = createDish(result);
                dishOptional = Optional.ofNullable(dish);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishOptional;
    }

    @Override
    public void deleteDishById(int dishId) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_DISH_BY_ID)) {
            statement.setInt(1, dishId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public int addDish(Dish dish) throws DaoException {
        Connection connection = pool.getConnection();
        int dishId;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_DISH)) {
            statement.setString(1, dish.getName());
            DishCategory category = dish.getCategory();
            statement.setString(2, category.name().toLowerCase());
            statement.setString(3, dish.getPictureName());
            statement.setDouble(4, dish.getPrice());
            statement.setInt(5, dish.getDiscountPercents());
            Date addingDate = dish.getAddedDate();
            Timestamp timestamp = new Timestamp(addingDate.getTime());
            statement.setTimestamp(6, timestamp);
            statement.setString(7, dish.getDescription());
            statement.setShort(8, dish.getWeight());
            dishId = statement.executeUpdate();
        } catch (SQLException e) {
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
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_ALL_DISHES_SORTED_BY_NAME);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public void editDish(Dish dish) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_DISH)) {
            statement.setString(1, dish.getName());
            DishCategory category = dish.getCategory();
            statement.setString(2, category.name().toLowerCase());
            statement.setString(3, dish.getPictureName());
            statement.setDouble(4, dish.getPrice());
            statement.setInt(5, dish.getDiscountPercents());
            statement.setString(6, dish.getDescription());
            statement.setShort(7, dish.getWeight());
            statement.setInt(8, dish.getDishId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Dish> findNewDishes(Date date) throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_NEW_DISHES)) {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            statement.setDate(1, sqlDate);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                dishes.add(createDish(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
    public List<Dish> findAllDishesSortedByPopularity() throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_ALL_DISHES_SORTED_BY_POPULARITY);
            while (result.next()) {
                dishes.add(createDish(result));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    /**
     * Create the {@code Dish} object from the {@code ResultSet} object.
     *
     * @param dishData the {@code ResultSet} object
     * @return the {@code Dish} object
     * @throws SQLException if there is an attempt to get data
     * from the {@code ResultSet} object of the wrong datatype
     */
    public static Dish createDish(ResultSet dishData) throws SQLException {
        int dishId = dishData.getInt(1);
        String name = dishData.getString(2);
        String dishCategory = dishData.getString(3);
        DishCategory category = DishCategory.valueOf(dishCategory.toUpperCase());
        String pictureName = dishData.getString(4);
        double price = dishData.getDouble(5);
        short discount = dishData.getShort(6);
        Date addedDate = dishData.getDate(7);
        String description = dishData.getString(8);
        short weight = dishData.getShort(9);
        Dish dish = new Dish(dishId, name, category, pictureName,
                price, discount, addedDate, description, weight);
        return dish;
    }
}
