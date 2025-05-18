package ui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("UrbanGrow – Urban Farming Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Users", new UserPanel());
        tabs.addTab("Garden Plots", new PlotPanel());
        tabs.addTab("Crops", new CropPanel());
        tabs.addTab("Plantings", new PlantingPanel());
        tabs.addTab("Harvests", new HarvestPanel());
        tabs.addTab("Tips & Sharing", new TipPanel());

        add(tabs);
    }
}
