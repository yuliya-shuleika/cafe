package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.CafeDao;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CafeDaoImpl implements CafeDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_ALL_CAFE_ADDRESSES = "SELECT addresses.city, addresses.street, addresses.house " +
            "FROM cafes JOIN " +
            "addresses ON cafes.address_id = addresses.address_id";
    private static final String SELECT_ALL_CAFES = "";

    @Override
    public List<Address> findAllCafeAddresses() throws DaoException {
        List<Address> addresses = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_ALL_CAFE_ADDRESSES);
            while (result.next()) {
                String city = result.getString(1);
                String street = result.getString(2);
                short house = result.getShort(3);
                Address address = new Address(city, street, house);
                addresses.add(address);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return addresses;
    }
}
