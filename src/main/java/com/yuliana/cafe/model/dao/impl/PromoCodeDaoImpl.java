package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.model.connection.ConnectionPool;
import com.yuliana.cafe.model.dao.PromoCodeDao;
import com.yuliana.cafe.model.entity.PromoCode;
import com.yuliana.cafe.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromoCodeDaoImpl implements PromoCodeDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_PROMO_CODE_BY_NAME = "SELECT promo_code_id, name, discount_percents " +
            "FROM promo_codes WHERE name = ?";
    private static final String SELECT_PROMO_CODES_BY_NAME_PART = "SELECT promo_code_id, name, discount_percents " +
            "FROM promo_codes WHERE name COLLATE UTF8_GENERAL_CI LIKE ?";
    private static final String SELECT_PROMO_CODE_BY_ID = "SELECT name, discount_percents " +
            "FROM promo_codes WHERE promo_code_id = ?";
    private static final String DELETE_PROMO_CODE = "DELETE FROM promo_codes WHERE promo_code_id = ?";
    private static final String INSERT_PROMO_CODE = "INSERT INTO promo_codes (name, discount_percents) " +
            "VALUES (?, ?)";
    private static final String SELECT_ALL_PROMO_CODES = "SELECT promo_code_id, name, discount_percents " +
            "FROM promo_codes";
    private static final String UPDATE_PROMO_CODE = "UPDATE promo_codes " +
            "SET name = ?, discount_percents = ? WHERE promo_code_id = ?";
    private static final String SELECT_ALL_PROMO_CODES_SORTED_BY_NAME = "SELECT promo_code_id, name, discount_percents " +
            "FROM promo_codes ORDER BY name";

    @Override
    public Optional<PromoCode> findPromoCodeByName(String name) throws DaoException {
        Connection connection = pool.getConnection();
        Optional<PromoCode> promoCodeOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PROMO_CODE_BY_NAME)) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int promoCodeId = result.getShort(1);
                short discountPercents = result.getShort(2);
                PromoCode promoCode = new PromoCode(promoCodeId, name, discountPercents);
                promoCodeOptional = Optional.of(promoCode);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return promoCodeOptional;
    }

    @Override
    public Optional<PromoCode> findPromoCodeById(int promoCodeId) throws DaoException {
        Connection connection = pool.getConnection();
        Optional<PromoCode> promoCodeOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PROMO_CODE_BY_ID)) {
            statement.setInt(1, promoCodeId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String name = result.getString(1);
                short discountPercents = result.getShort(2);
                PromoCode promoCode = new PromoCode(promoCodeId, name, discountPercents);
                promoCodeOptional = Optional.of(promoCode);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return promoCodeOptional;
    }

    @Override
    public int addPromoCode(PromoCode promoCode) throws DaoException {
        Connection connection = pool.getConnection();
        int promoCodeId;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PROMO_CODE)) {
            statement.setString(1, promoCode.getName());
            statement.setShort(2, promoCode.getDiscountPercents());
            promoCodeId = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return promoCodeId;
    }

    @Override
    public void deletePromoCode(int promoCodeId) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PROMO_CODE)) {
            statement.setInt(1, promoCodeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<PromoCode> findPromoCodesByNamePart(String namePart) throws DaoException {
        Connection connection = pool.getConnection();
        List<PromoCode> promoCodes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PROMO_CODES_BY_NAME_PART)) {
            String searchPattern = '%' + namePart + '%';
            statement.setString(1, searchPattern);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PromoCode promoCode = createPromoCode(result);
                promoCodes.add(promoCode);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return promoCodes;
    }

    @Override
    public List<PromoCode> findAllPromoCodes() throws DaoException {
        Connection connection = pool.getConnection();
        List<PromoCode> promoCodes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_ALL_PROMO_CODES);
            while (result.next()) {
                PromoCode promoCode = createPromoCode(result);
                promoCodes.add(promoCode);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return promoCodes;
    }

    @Override
    public void editPromoCode(PromoCode promoCode) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PROMO_CODE)) {
            statement.setString(1, promoCode.getName());
            statement.setShort(2, promoCode.getDiscountPercents());
            statement.setInt(3, promoCode.getPromoCodeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<PromoCode> findAllPromoCodesSortedByName() throws DaoException {
        Connection connection = pool.getConnection();
        List<PromoCode> promoCodes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_ALL_PROMO_CODES_SORTED_BY_NAME);
            while (result.next()) {
                PromoCode promoCode = createPromoCode(result);
                promoCodes.add(promoCode);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return promoCodes;
    }

    private PromoCode createPromoCode(ResultSet result) throws SQLException {
        int promoCodeId = result.getInt(1);
        String name = result.getString(2);
        short discountPercents = result.getShort(3);
        PromoCode promoCode = new PromoCode(promoCodeId, name, discountPercents);
        return promoCode;
    }
}
