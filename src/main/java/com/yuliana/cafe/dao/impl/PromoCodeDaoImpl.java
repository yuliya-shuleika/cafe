package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.PromoCodeDao;
import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PromoCodeDaoImpl implements PromoCodeDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_PROMO_CODE_BY_NAME = "SELECT promo_code_id, name, discount_percents " +
            "FROM promo_codes WHERE name = ?";

    @Override
    public Optional<PromoCode> findPromoCodeByName(String name) throws DaoException{
        Connection connection = pool.getConnection();
        Optional<PromoCode> promoCodeOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PROMO_CODE_BY_NAME)){
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int promoCodeId = result.getInt(1);
                short discountPercents = result.getShort(2);
                PromoCode promoCode = new PromoCode(promoCodeId, name, discountPercents);
                promoCodeOptional = Optional.of(promoCode);
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return promoCodeOptional;
    }

    @Override
    public void addPromoCode(String name, int discountPercents) throws DaoException {

    }

    @Override
    public void deletePromoCode(String name) throws DaoException {

    }
}
