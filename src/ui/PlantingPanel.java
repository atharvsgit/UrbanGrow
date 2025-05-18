package ui;

import dao.PlantingDAO;
import model.Planting;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PlantingPanel extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private PlantingDAO plantingDAO = new PlantingDAO();

    public PlantingPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new String[]{"ID", "User ID", "Plot ID", "Crop ID", "Planted On", "Expected Harvest"}, 0);
        table = new JTable(model);
        refreshTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new FlowLayout());
        JTextField userId = new JTextField(5), plotId = new JTextField(5), cropId = new JTextField(5), planted = new JTextField(8), harvest = new JTextField(8);
        JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"), deleteBtn = new JButton("Delete");

        form.add(new JLabel("User ID:")); form.add(userId);
        form.add(new JLabel("Plot ID:")); form.add(plotId);
        form.add(new JLabel("Crop ID:")); form.add(cropId);
        form.add(new JLabel("Planted On (yyyy-mm-dd):")); form.add(planted);
        form.add(new JLabel("Expected Harvest (yyyy-mm-dd):")); form.add(harvest);
        form.add(addBtn); form.add(updateBtn); form.add(deleteBtn);

        add(form, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            try {
                Planting planting = new Planting(Integer.parseInt(userId.getText()), Integer.parseInt(plotId.getText()), Integer.parseInt(cropId.getText()), java.sql.Date.valueOf(planted.getText()), java.sql.Date.valueOf(harvest.getText()));
                plantingDAO.addPlanting(planting);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding planting: " + ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    Planting planting = new Planting(id, Integer.parseInt(userId.getText()), Integer.parseInt(plotId.getText()), Integer.parseInt(cropId.getText()), java.sql.Date.valueOf(planted.getText()), java.sql.Date.valueOf(harvest.getText()));
                    plantingDAO.updatePlanting(planting);
                    refreshTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error updating planting: " + ex.getMessage());
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    plantingDAO.deletePlanting(id);
                    refreshTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting planting: " + ex.getMessage());
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                userId.setText(model.getValueAt(row, 1).toString());
                plotId.setText(model.getValueAt(row, 2).toString());
                cropId.setText(model.getValueAt(row, 3).toString());
                planted.setText(model.getValueAt(row, 4).toString());
                harvest.setText(model.getValueAt(row, 5).toString());
            }
        });
    }

    private void refreshTable() {
        try {
            List<Planting> plantings = plantingDAO.getAllPlantings();
            model.setRowCount(0);
            for (Planting p : plantings) {
                model.addRow(new Object[]{p.getPlantingId(), p.getUserId(), p.getPlotId(), p.getCropId(), p.getPlantedOn(), p.getExpectedHarvestDate()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading plantings: " + ex.getMessage());
        }
    }
}
