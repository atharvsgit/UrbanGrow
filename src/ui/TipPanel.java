package ui;

import dao.TipDAO;
import model.Tip;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TipPanel extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private TipDAO tipDAO = new TipDAO();

    public TipPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new String[]{"ID", "User ID", "Topic", "Content", "Posted On"}, 0);
        table = new JTable(model);
        refreshTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new FlowLayout());
        JTextField userId = new JTextField(5), topic = new JTextField(10), content = new JTextField(18), postedOn = new JTextField(10);
        JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"), deleteBtn = new JButton("Delete");

        form.add(new JLabel("User ID:")); form.add(userId);
        form.add(new JLabel("Topic:")); form.add(topic);
        form.add(new JLabel("Content:")); form.add(content);
        form.add(new JLabel("Posted On (yyyy-mm-dd):")); form.add(postedOn);
        form.add(addBtn); form.add(updateBtn); form.add(deleteBtn);

        add(form, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            try {
                Tip tip = new Tip(Integer.parseInt(userId.getText()), topic.getText(), content.getText(), java.sql.Date.valueOf(postedOn.getText()));
                tipDAO.addTip(tip);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding tip: " + ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    Tip tip = new Tip(id, Integer.parseInt(userId.getText()), topic.getText(), content.getText(), java.sql.Date.valueOf(postedOn.getText()));
                    tipDAO.updateTip(tip);
                    refreshTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error updating tip: " + ex.getMessage());
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    tipDAO.deleteTip(id);
                    refreshTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting tip: " + ex.getMessage());
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                userId.setText(model.getValueAt(row, 1).toString());
                topic.setText(model.getValueAt(row, 2).toString());
                content.setText(model.getValueAt(row, 3).toString());
                postedOn.setText(model.getValueAt(row, 4).toString());
            }
        });
    }

    private void refreshTable() {
        try {
            List<Tip> tips = tipDAO.getAllTips();
            model.setRowCount(0);
            for (Tip t : tips) {
                model.addRow(new Object[]{t.getTipId(), t.getUserId(), t.getTopic(), t.getContent(), t.getPostedOn()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading tips: " + ex.getMessage());
        }
    }
}
