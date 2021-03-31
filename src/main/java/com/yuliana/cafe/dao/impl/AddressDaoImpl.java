package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.AddressDao;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String INSERT_ADDRESS = "INSERT into addresses " +
            "(city, street, house, entrance, floor, flat) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ADDRESS_BY_ID = "SELECT address_id, city, street, house, entrance, floor, flat " +
            "FROM addresses WHERE address_id = ?";

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

    @Override
    public Optional<Address> findAddressById(int addressId) throws DaoException {
        Connection connection = pool.getConnection();
        Optional<Address> addressOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ADDRESS_BY_ID)){
            statement.setInt(1, addressId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Address address = createAddress(result);
                addressOptional = Optional.of(address);
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return addressOptional;
    }

    private Address createAddress(ResultSet result) throws SQLException{
        String city = result.getString(1);
        String street = result.getString(2);
        short house = result.getShort(3);
        short entrance = result.getShort(4);
        short floor = result.getShort(5);
        short flat = result.getShort(5);
        Address address = new Address(city, street, house, entrance, floor, flat);
        return address;
    }
}
