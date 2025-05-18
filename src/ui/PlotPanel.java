package ui;

import dao.PlotDAO;
import model.Plot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PlotPanel extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private PlotDAO plotDAO = new PlotDAO();

    public PlotPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new String[]{"ID", "User ID", "Location", "Size", "Soil Type", "Available"}, 0);
        table = new JTable(model);
        refreshTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new FlowLayout());
        JTextField userId = new JTextField(5), location = new JTextField(8), size = new JTextField(5), soilType = new JTextField(8);
        JCheckBox available = new JCheckBox("Available");
        JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"), deleteBtn = new JButton("Delete");

        form.add(new JLabel("User ID:")); form.add(userId);
        form.add(new JLabel("Location:")); form.add(location);
        form.add(new JLabel("Size:")); form.add(size);
        form.add(new JLabel("Soil Type:")); form.add(soilType);
        form.add(available);
        form.add(addBtn); form.add(updateBtn); form.add(deleteBtn);

        add(form, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            try {
                Plot plot = new Plot(Integer.parseInt(userId.getText()), location.getText(), Double.parseDouble(size.getText()), soilType.getText(), available.isSelected());
                plotDAO.addPlot(plot);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding plot: " + ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    Plot plot = new Plot(id, Integer.parseInt(userId.getText()), location.getText(), Double.parseDouble(size.getText()), soilType.getText(), available.isSelected());
                    plotDAO.updatePlot(plot);
                    refreshTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error updating plot: " + ex.getMessage());
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    plotDAO.deletePlot(id);
                    refreshTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting plot: " + ex.getMessage());
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                userId.setText(model.getValueAt(row, 1).toString());
                location.setText(model.getValueAt(row, 2).toString());
                size.setText(model.getValueAt(row, 3).toString());
                soilType.setText(model.getValueAt(row, 4).toString());
                available.setSelected((boolean) model.getValueAt(row, 5));
            }
        });
    }

    private void refreshTable() {
        try {
            List<Plot> plots = plotDAO.getAllPlots();
            model.setRowCount(0);
            for (Plot p : plots) {
                model.addRow(new Object[]{p.getPlotId(), p.getUserId(), p.getLocation(), p.getSize(), p.getSoilType(), p.isAvailable()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading plots: " + ex.getMessage());
        }
    }
}
