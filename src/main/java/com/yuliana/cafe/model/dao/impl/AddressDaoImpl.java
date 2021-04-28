package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.model.connection.ConnectionPool;
import com.yuliana.cafe.model.dao.AddressDao;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@code AddressDao} interface.
 *
 * @author Yulia Shuleiko
 */
public class AddressDaoImpl implements AddressDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static volatile AddressDaoImpl INSTANCE = new AddressDaoImpl();
    private static final String INSERT_ADDRESS = "INSERT into addresses " +
            "(city, street, house, entrance, floor, flat) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ADDRESS_BY_ID = "SELECT address_id, city, street, house, entrance, floor, flat " +
            "FROM addresses WHERE address_id = ?";
    private static final String UPDATE_ADDRESS = "UPDATE addresses " +
            "SET city = ?, street = ?, house = ?, entrance = ?, floor = ?, flat = ? " +
            "WHERE address_id = ?";
    private static final String SELECT_ALL_CAFE_ADDRESSES = "SELECT addresses.address_id, " +
            "addresses.city, addresses.street, addresses.house " +
            "FROM cafes JOIN addresses ON cafes.address_id = addresses.address_id";

    public static AddressDaoImpl getInstance() {
        return INSTANCE;
    }

    /**
     * Forbid creation of the new objects of the class.
     */
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

    /**
     * Create the {@code Address} object from the {@code ResultSet} object.
     *
     * @param addressData {@code ResultSet} object
     * @return the {@code Address} object
     * @throws SQLException if there is an attempt to get data
     * from the {@code ResultSet} object of the wrong datatype
     */
    public static Address createAddress(ResultSet addressData) throws SQLException {
        int addressId = addressData.getInt(1);
        String city = addressData.getString(2);
        String street = addressData.getString(3);
        short house = addressData.getShort(4);
        short entrance = addressData.getShort(5);
        short floor = addressData.getShort(6);
        short flat = addressData.getShort(7);
        Address address = new Address(addressId, city, street, house, entrance, floor, flat);
        return address;
    }
}
