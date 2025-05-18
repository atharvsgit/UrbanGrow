package model;

public class Plot {
    private int plotId;
    private int userId;
    private String location;
    private double size;
    private String soilType;
    private boolean available;

    public Plot(int plotId, int userId, String location, double size, String soilType, boolean available) {
        this.plotId = plotId;
        this.userId = userId;
        this.location = location;
        this.size = size;
        this.soilType = soilType;
        this.available = available;
    }

    public Plot(int userId, String location, double size, String soilType, boolean available) {
        this(0, userId, location, size, soilType, available);
    }

    // Getters and Setters
    public int getPlotId() { return plotId; }
    public void setPlotId(int plotId) { this.plotId = plotId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getSize() { return size; }
    public void setSize(double size) { this.size = size; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
