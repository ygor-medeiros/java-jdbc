package com.ygor.javajdbc.repository;

import com.ygor.javajdbc.factory.ConnectionFactory;
import com.ygor.javajdbc.interfaces.IBaseRepository;
import com.ygor.javajdbc.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository implements IBaseRepository<Address> {

    private Connection conn;

    public AddressRepository() {
        this.conn = ConnectionFactory.getInstance().getConnection();
    }

    public Address getById(int user_id) {
        String sql = "select * from address where user_id = ?";

        Address address = new Address();

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, user_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                address.setUserId(rs.getInt("user_id"));
                address.setStreet(rs.getString("street"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setZipCode(rs.getString("zip_code"));
                address.setCountry(rs.getString("country"));


            } else {
                rs.close();
                stmt.close();

                return null;
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return address;
    }

    public List<Address> getAll() {
        String sql = "select * from address";

        List<Address> addresses = new ArrayList<Address>();

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Address address = new Address();

                address.setUserId(rs.getInt("user_id"));
                address.setStreet(rs.getString("street"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setCountry(rs.getString("country"));
                address.setZipCode(rs.getString("zip_code"));

                addresses.add(address);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return addresses;
    }

    public Address create(Address address) {
        String sql = "insert into address (user_id, street, city, state, country, zip_code) values (?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, address.getUserId());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getState());
            stmt.setString(5, address.getCountry());
            stmt.setString(6, address.getZipCode());

            stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return address;
    }

    public void update(int user_id, Address address) {
        String sql = "update address set street = ?, city = ?, state = ?, " +
                "country = ?, zip_code = ? where user_id = ?";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, address.getStreet());
            stmt.setString(2, address.getCity());
            stmt.setString(3, address.getState());
            stmt.setString(4, address.getCountry());
            stmt.setString(5, address.getZipCode());
            stmt.setInt(6, user_id);

            stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(int user_id) {
        String sql = "delete from address where user_id = ?";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, user_id);

            stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
