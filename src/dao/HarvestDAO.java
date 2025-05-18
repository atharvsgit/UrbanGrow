// src/dao/HarvestDAO.java
package dao;

import model.Harvest;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HarvestDAO {

    public void addHarvest(Harvest harvest) throws SQLException {
        String sql = "INSERT INTO Harvest_Log (planting_id, harvest_date, quantity_kg) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, harvest.getPlantingId());
            ps.setDate(2, new Date(harvest.getHarvestDate().getTime()));
            ps.setDouble(3, harvest.getQuantityKg());
            ps.executeUpdate();
        }
    }

    public List<Harvest> getAllHarvests() throws SQLException {
        List<Harvest> harvests = new ArrayList<>();
        String sql = "SELECT * FROM Harvest_Log";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                harvests.add(new Harvest(
                        rs.getInt("harvest_id"),
                        rs.getInt("planting_id"),
                        rs.getDate("harvest_date"),
                        rs.getDouble("quantity_kg")
                ));
            }
        }
        return harvests;
    }

    public void updateHarvest(Harvest harvest) throws SQLException {
        String sql = "UPDATE Harvest_Log SET planting_id=?, harvest_date=?, quantity_kg=? WHERE harvest_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, harvest.getPlantingId());
            ps.setDate(2, new Date(harvest.getHarvestDate().getTime()));
            ps.setDouble(3, harvest.getQuantityKg());
            ps.setInt(4, harvest.getHarvestId());
            ps.executeUpdate();
        }
    }

    public void deleteHarvest(int harvestId) throws SQLException {
        String sql = "DELETE FROM Harvest_Log WHERE harvest_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, harvestId);
            ps.executeUpdate();
        }
    }
}
