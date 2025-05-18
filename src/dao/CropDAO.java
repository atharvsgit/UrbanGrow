// src/dao/CropDAO.java
package dao;

import model.Crop;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CropDAO {

    public void addCrop(Crop crop) throws SQLException {
        String sql = "INSERT INTO Crops (name, optimal_season, days_to_harvest, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, crop.getName());
            ps.setString(2, crop.getOptimalSeason());
            ps.setInt(3, crop.getDaysToHarvest());
            ps.setString(4, crop.getDescription());
            ps.executeUpdate();
        }
    }

    public List<Crop> getAllCrops() throws SQLException {
        List<Crop> crops = new ArrayList<>();
        String sql = "SELECT * FROM Crops";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                crops.add(new Crop(
                        rs.getInt("crop_id"),
                        rs.getString("name"),
                        rs.getString("optimal_season"),
                        rs.getInt("days_to_harvest"),
                        rs.getString("description")
                ));
            }
        }
        return crops;
    }

    public void updateCrop(Crop crop) throws SQLException {
        String sql = "UPDATE Crops SET name=?, optimal_season=?, days_to_harvest=?, description=? WHERE crop_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, crop.getName());
            ps.setString(2, crop.getOptimalSeason());
            ps.setInt(3, crop.getDaysToHarvest());
            ps.setString(4, crop.getDescription());
            ps.setInt(5, crop.getCropId());
            ps.executeUpdate();
        }
    }

    public void deleteCrop(int cropId) throws SQLException {
        String sql = "DELETE FROM Crops WHERE crop_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cropId);
            ps.executeUpdate();
        }
    }
}
