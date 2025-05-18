package ui;

import dao.CropDAO;
import model.Crop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CropPanel extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private CropDAO cropDAO = new CropDAO();

    public CropPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new String[]{"ID", "Name", "Optimal Season", "Days to Harvest", "Description"}, 0);
        table = new JTable(model);
        refreshTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new FlowLayout());
        JTextField name = new JTextField(8), season = new JTextField(8), days = new JTextField(5), desc = new JTextField(12);
        JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"), deleteBtn = new JButton("Delete");

        form.add(new JLabel("Name:")); form.add(name);
        form.add(new JLabel("Season:")); form.add(season);
        form.add(new JLabel("Days:")); form.add(days);
        form.add(new JLabel("Description:")); form.add(desc);
        form.add(addBtn); form.add(updateBtn); form.add(deleteBtn);

        add(form, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            try {
                Crop crop = new Crop(name.getText(), season.getText(), Integer.parseInt(days.getText()), desc.getText());
                cropDAO.addCrop(crop);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding crop: " + ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    Crop crop = new Crop(id, name.getText(), season.getText(), Integer.parseInt(days.getText()), desc.getText());
                    cropDAO.updateCrop(crop);
                    refreshTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error updating crop: " + ex.getMessage());
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    cropDAO.deleteCrop(id);
                    refreshTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting crop: " + ex.getMessage());
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                name.setText(model.getValueAt(row, 1).toString());
                season.setText(model.getValueAt(row, 2).toString());
                days.setText(model.getValueAt(row, 3).toString());
                desc.setText(model.getValueAt(row, 4).toString());
            }
        });
    }

    private void refreshTable() {
        try {
            List<Crop> crops = cropDAO.getAllCrops();
            model.setRowCount(0);
            for (Crop c : crops) {
                model.addRow(new Object[]{c.getCropId(), c.getName(), c.getOptimalSeason(), c.getDaysToHarvest(), c.getDescription()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading crops: " + ex.getMessage());
        }
    }
}
