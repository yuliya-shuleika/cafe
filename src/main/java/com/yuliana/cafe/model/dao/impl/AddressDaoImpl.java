package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.model.connection.ConnectionPool;
import com.yuliana.cafe.model.dao.AddressDao;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.sql.*;
import java.util.Optional;

import static com.yuliana.cafe.model.dao.creator.EntityCreator.createAddress;

public class AddressDaoImpl implements AddressDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static volatile AddressDaoImpl INSTANCE;
    private static final String INSERT_ADDRESS = "INSERT into addresses " +
            "(city, street, house, entrance, floor, flat) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ADDRESS_BY_ID = "SELECT address_id, city, street, house, entrance, floor, flat " +
            "FROM addresses WHERE address_id = ?";
    private static final String UPDATE_ADDRESS = "UPDATE addresses " +
            "SET city = ?, street = ?, house = ?, entrance = ?, floor = ?, flat = ? " +
            "WHERE address_id = ?";

    public static AddressDaoImpl getInstance() {
        AddressDaoImpl localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (AddressDaoImpl.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    INSTANCE = localInstance = new AddressDaoImpl();
                }
            }
        }
        return localInstance;
    }

    private AddressDaoImpl(){}

    @Override
    public int addAddress(Address address) throws DaoException {
        int addressId = 0;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setShort(3, address.getHouse());
            statement.setShort(4, address.getEntrance());
            statement.setShort(5, address.getFloor());
            statement.setShort(6, address.getFlat());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                addressId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
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
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ADDRESS_BY_ID)) {
            statement.setInt(1, addressId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Address address = createAddress(result);
                addressOptional = Optional.ofNullable(address);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return addressOptional;
    }

    @Override
    public void updateAddress(Address address) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ADDRESS)) {
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setShort(3, address.getHouse());
            statement.setShort(4, address.getEntrance());
            statement.setShort(5, address.getFloor());
            statement.setShort(6, address.getFlat());
            statement.setInt(7, address.getAddressId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

}
