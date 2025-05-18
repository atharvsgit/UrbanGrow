package ui;

import dao.HarvestDAO;
import model.Harvest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class HarvestPanel extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private HarvestDAO harvestDAO = new HarvestDAO();

    public HarvestPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new String[]{"ID", "Planting ID", "Harvest Date", "Quantity (kg)"}, 0);
        table = new JTable(model);
        refreshTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new FlowLayout());
        JTextField plantingId = new JTextField(5), date = new JTextField(8), qty = new JTextField(5);
        JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"), deleteBtn = new JButton("Delete");

        form.add(new JLabel("Planting ID:")); form.add(plantingId);
        form.add(new JLabel("Harvest Date (yyyy-mm-dd):")); form.add(date);
        form.add(new JLabel("Quantity (kg):")); form.add(qty);
        form.add(addBtn); form.add(updateBtn); form.add(deleteBtn);

        add(form, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            try {
                Harvest harvest = new Harvest(Integer.parseInt(plantingId.getText()), java.sql.Date.valueOf(date.getText()), Double.parseDouble(qty.getText()));
                harvestDAO.addHarvest(harvest);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding harvest: " + ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    Harvest harvest = new Harvest(id, Integer.parseInt(plantingId.getText()), java.sql.Date.valueOf(date.getText()), Double.parseDouble(qty.getText()));
                    harvestDAO.updateHarvest(harvest);
                    refreshTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error updating harvest: " + ex.getMessage());
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    harvestDAO.deleteHarvest(id);
                    refreshTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting harvest: " + ex.getMessage());
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                plantingId.setText(model.getValueAt(row, 1).toString());
                date.setText(model.getValueAt(row, 2).toString());
                qty.setText(model.getValueAt(row, 3).toString());
            }
        });
    }

    private void refreshTable() {
        try {
            List<Harvest> harvests = harvestDAO.getAllHarvests();
            model.setRowCount(0);
            for (Harvest h : harvests) {
                model.addRow(new Object[]{h.getHarvestId(), h.getPlantingId(), h.getHarvestDate(), h.getQuantityKg()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading harvests: " + ex.getMessage());
        }
    }
}
