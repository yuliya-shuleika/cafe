package com.yuliana.cafe.model.entity;


/**
 * Address entity.
 *
 * @author Yulia Shuleiko
 */
public class Address {

    private int addressId;
    private String city;
    private String street;
    private short house;
    private short entrance;
    private short floor;
    private short flat;

    /**
     * @param addressId id of the address
     * @param city name of the city
     * @param street name of the street
     * @param house number of the house
     */
    public Address(int addressId, String city, String street, short house) {
        this.addressId = addressId;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    /**
     *
     * @param addressId id of the address
     * @param city name of the city
     * @param street name of the street
     * @param house number of the house
     * @param entrance number of entrance in the house
     * @param floor number of thr floor
     * @param flat number of the flat
     */
    public Address(int addressId, String city, String street, short house, short entrance, short floor, short flat) {
        this.addressId = addressId;
        this.city = city;
        this.street = street;
        this.house = house;
        this.entrance = entrance;
        this.floor = floor;
        this.flat = flat;
    }

    public Address(String city, String street, short house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Address(String city, String street, short house, short entrance, short floor, short flat) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.entrance = entrance;
        this.floor = floor;
        this.flat = flat;
    }

    public Address() {
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public short getHouse() {
        return house;
    }

    public void setHouse(short house) {
        this.house = house;
    }

    public short getEntrance() {
        return entrance;
    }

    public void setEntrance(byte entrance) {
        this.entrance = entrance;
    }

    public short getFloor() {
        return floor;
    }

    public void setFloor(short floor) {
        this.floor = floor;
    }

    public short getFlat() {
        return flat;
    }

    public void setFlat(short flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        if (addressId != address.addressId) return false;
        if (house != address.house) return false;
        if (entrance != address.entrance) return false;
        if (floor != address.floor) return false;
        if (flat != address.flat) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        return street != null ? street.equals(address.street) : address.street == null;
    }

    @Override
    public int hashCode() {
        int result = addressId;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (int) house;
        result = 31 * result + (int) entrance;
        result = 31 * result + (int) floor;
        result = 31 * result + (int) flat;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("addressId=").append(addressId);
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", house=").append(house);
        sb.append(", entrance=").append(entrance);
        sb.append(", floor=").append(floor);
        sb.append(", flat=").append(flat);
        sb.append('}');
        return sb.toString();
    }
}
