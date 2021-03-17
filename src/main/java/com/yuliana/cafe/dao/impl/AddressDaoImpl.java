package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.AddressDao;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressDaoImpl implements AddressDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String INSERT_ADDRESS = "INSERT into addresses " +
            "(city, street, house, entrance, floor, flat) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ADDRESS_ID = "SELECT address_id FROM addresses" +
            "WHERE city = ? AND street = ? AND house = ? AND entrance = ? AND floor = ? AND flat = ?";

    @Override
    public int addAddress(Address address) throws DaoException {
        int addressId;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ADDRESS)){
            statement.setString(1, address.getCity());
            statement.setString(2,address.getStreet());
            statement.setShort(3, address.getHouse());
            statement.setShort(4, address.getEntrance());
            statement.setShort(5, address.getFloor());
            statement.setShort(6, address.getFlat());
            addressId = statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return addressId;
    }


}
