package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class UserPanel extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private UserDAO userDAO = new UserDAO();

    public UserPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Password", "Location", "Join Date"}, 0);
        table = new JTable(model);
        refreshTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new FlowLayout());
        JTextField name = new JTextField(8), email = new JTextField(10), password = new JTextField(8), location = new JTextField(8), joinDate = new JTextField(8);
        JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"), deleteBtn = new JButton("Delete");

        form.add(new JLabel("Name:")); form.add(name);
        form.add(new JLabel("Email:")); form.add(email);
        form.add(new JLabel("Password:")); form.add(password);
        form.add(new JLabel("Location:")); form.add(location);
        form.add(new JLabel("Join Date (yyyy-mm-dd):")); form.add(joinDate);
        form.add(addBtn); form.add(updateBtn); form.add(deleteBtn);

        add(form, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            try {
                User user = new User(name.getText(), email.getText(), password.getText(), location.getText(), java.sql.Date.valueOf(joinDate.getText()));
                userDAO.addUser(user);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding user: " + ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    User user = new User(id, name.getText(), email.getText(), password.getText(), location.getText(), java.sql.Date.valueOf(joinDate.getText()));
                    userDAO.updateUser(user);
                    refreshTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error updating user: " + ex.getMessage());
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) model.getValueAt(row, 0);
                    userDAO.deleteUser(id);
                    refreshTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting user: " + ex.getMessage());
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                name.setText(model.getValueAt(row, 1).toString());
                email.setText(model.getValueAt(row, 2).toString());
                password.setText(model.getValueAt(row, 3).toString());
                location.setText(model.getValueAt(row, 4).toString());
                joinDate.setText(model.getValueAt(row, 5).toString());
            }
        });
    }

    private void refreshTable() {
        try {
            List<User> users = userDAO.getAllUsers();
            model.setRowCount(0);
            for (User u : users) {
                model.addRow(new Object[]{u.getUserId(), u.getName(), u.getEmail(), u.getPassword(), u.getLocation(), u.getJoinDate()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading users: " + ex.getMessage());
        }
    }
}
