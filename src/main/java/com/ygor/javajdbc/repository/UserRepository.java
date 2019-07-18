package com.ygor.javajdbc.repository;

import com.ygor.javajdbc.factory.ConnectionFactory;
import com.ygor.javajdbc.interfaces.IBaseRepository;
import com.ygor.javajdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IBaseRepository<User> {

    private Connection conn;

    public UserRepository() {
        this.conn = ConnectionFactory.getInstance().getConnection();
    }

    public User getById(int id) {
        String sql = "select * from users where id = ?";

        User user = new User();

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setCellphone(rs.getString("cellphone"));

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

        return user;
    }

    public List<User> getAll() {
        String sql = "select * from users";

        List<User> users = new ArrayList<User>();

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setCellphone(rs.getString("cellphone"));

                users.add(user);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    
    public User create(User user) {
        String sql = "insert into users (name, email, cellphone) values (?, ?, ?)";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getCellphone());

            stmt.execute();

            if (stmt.getGeneratedKeys().next()) {
                user.setId(stmt.getGeneratedKeys().getInt(1));
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public void update(int id, User user) {
        String sql = "update users set name = ?, email = ?, cellphone = ? where id = ?";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getCellphone());
            stmt.setInt(4, id);

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(int id) {

        String sql = "delete from users where id = ?";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}