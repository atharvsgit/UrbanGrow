// src/dao/UserDAO.java
package dao;

import model.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Create
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (name, email, password, location, join_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getLocation());
            ps.setDate(5, new Date(user.getJoinDate().getTime()));
            ps.executeUpdate();
        }
    }

    // Read All
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("location"),
                        rs.getDate("join_date")
                ));
            }
        }
        return users;
    }

    // Read by ID
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("location"),
                            rs.getDate("join_date")
                    );
                }
            }
        }
        return null;
    }

    // Update
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET name=?, email=?, password=?, location=?, join_date=? WHERE user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getLocation());
            ps.setDate(5, new Date(user.getJoinDate().getTime()));
            ps.setInt(6, user.getUserId());
            ps.executeUpdate();
        }
    }

    // Delete
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }
}
