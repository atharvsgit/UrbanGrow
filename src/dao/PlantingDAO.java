// src/dao/PlantingDAO.java
package dao;

import model.Planting;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantingDAO {

    public void addPlanting(Planting planting) throws SQLException {
        String sql = "INSERT INTO Plantings (user_id, plot_id, crop_id, planted_on, expected_harvest_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, planting.getUserId());
            ps.setInt(2, planting.getPlotId());
            ps.setInt(3, planting.getCropId());
            ps.setDate(4, new Date(planting.getPlantedOn().getTime()));
            ps.setDate(5, new Date(planting.getExpectedHarvestDate().getTime()));
            ps.executeUpdate();
        }
    }

    public List<Planting> getAllPlantings() throws SQLException {
        List<Planting> plantings = new ArrayList<>();
        String sql = "SELECT * FROM Plantings";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                plantings.add(new Planting(
                        rs.getInt("planting_id"),
                        rs.getInt("user_id"),
                        rs.getInt("plot_id"),
                        rs.getInt("crop_id"),
                        rs.getDate("planted_on"),
                        rs.getDate("expected_harvest_date")
                ));
            }
        }
        return plantings;
    }

    public void updatePlanting(Planting planting) throws SQLException {
        String sql = "UPDATE Plantings SET user_id=?, plot_id=?, crop_id=?, planted_on=?, expected_harvest_date=? WHERE planting_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, planting.getUserId());
            ps.setInt(2, planting.getPlotId());
            ps.setInt(3, planting.getCropId());
            ps.setDate(4, new Date(planting.getPlantedOn().getTime()));
            ps.setDate(5, new Date(planting.getExpectedHarvestDate().getTime()));
            ps.setInt(6, planting.getPlantingId());
            ps.executeUpdate();
        }
    }

    public void deletePlanting(int plantingId) throws SQLException {
        String sql = "DELETE FROM Plantings WHERE planting_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, plantingId);
            ps.executeUpdate();
        }
    }
}
