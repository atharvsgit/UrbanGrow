// src/dao/TipDAO.java
package dao;

import model.Tip;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipDAO {

    public void addTip(Tip tip) throws SQLException {
        String sql = "INSERT INTO Tips_And_Sharing (user_id, topic, content, posted_on) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tip.getUserId());
            ps.setString(2, tip.getTopic());
            ps.setString(3, tip.getContent());
            ps.setTimestamp(4, new Timestamp(tip.getPostedOn().getTime()));
            ps.executeUpdate();
        }
    }

    public List<Tip> getAllTips() throws SQLException {
        List<Tip> tips = new ArrayList<>();
        String sql = "SELECT * FROM Tips_And_Sharing";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tips.add(new Tip(
                        rs.getInt("tip_id"),
                        rs.getInt("user_id"),
                        rs.getString("topic"),
                        rs.getString("content"),
                        rs.getTimestamp("posted_on")
                ));
            }
        }
        return tips;
    }

    public void updateTip(Tip tip) throws SQLException {
        String sql = "UPDATE Tips_And_Sharing SET user_id=?, topic=?, content=?, posted_on=? WHERE tip_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tip.getUserId());
            ps.setString(2, tip.getTopic());
            ps.setString(3, tip.getContent());
            ps.setTimestamp(4, new Timestamp(tip.getPostedOn().getTime()));
            ps.setInt(5, tip.getTipId());
            ps.executeUpdate();
        }
    }

    public void deleteTip(int tipId) throws SQLException {
        String sql = "DELETE FROM Tips_And_Sharing WHERE tip_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tipId);
            ps.executeUpdate();
        }
    }
}
