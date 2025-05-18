// src/dao/PlotDAO.java
package dao;

import model.Plot;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlotDAO {

    public void addPlot(Plot plot) throws SQLException {
        String sql = "INSERT INTO Garden_Plots (user_id, location, size, soil_type, available) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, plot.getUserId());
            ps.setString(2, plot.getLocation());
            ps.setDouble(3, plot.getSize());
            ps.setString(4, plot.getSoilType());
            ps.setBoolean(5, plot.isAvailable());
            ps.executeUpdate();
        }
    }

    public List<Plot> getAllPlots() throws SQLException {
        List<Plot> plots = new ArrayList<>();
        String sql = "SELECT * FROM Garden_Plots";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                plots.add(new Plot(
                        rs.getInt("plot_id"),
                        rs.getInt("user_id"),
                        rs.getString("location"),
                        rs.getDouble("size"),
                        rs.getString("soil_type"),
                        rs.getBoolean("available")
                ));
            }
        }
        return plots;
    }

    public void updatePlot(Plot plot) throws SQLException {
        String sql = "UPDATE Garden_Plots SET user_id=?, location=?, size=?, soil_type=?, available=? WHERE plot_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, plot.getUserId());
            ps.setString(2, plot.getLocation());
            ps.setDouble(3, plot.getSize());
            ps.setString(4, plot.getSoilType());
            ps.setBoolean(5, plot.isAvailable());
            ps.setInt(6, plot.getPlotId());
            ps.executeUpdate();
        }
    }

    public void deletePlot(int plotId) throws SQLException {
        String sql = "DELETE FROM Garden_Plots WHERE plot_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, plotId);
            ps.executeUpdate();
        }
    }
}
