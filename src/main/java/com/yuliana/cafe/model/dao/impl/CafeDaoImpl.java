package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.model.connection.ConnectionPool;
import com.yuliana.cafe.model.dao.CafeDao;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CafeDaoImpl implements CafeDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_ALL_CAFE_ADDRESSES = "SELECT addresses.address_id, " +
            "addresses.city, addresses.street, addresses.house " +
            "FROM cafes JOIN addresses ON cafes.address_id = addresses.address_id";

    @Override
    public List<Address> findAllCafeAddresses() throws DaoException {
        List<Address> addresses = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_ALL_CAFE_ADDRESSES);
            while (result.next()) {
                int addressId = result.getInt(1);
                String city = result.getString(2);
                String street = result.getString(3);
                short house = result.getShort(4);
                Address address = new Address(addressId, city, street, house);
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
