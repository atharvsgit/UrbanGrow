package model;

public class Crop {
    private int cropId;
    private String name;
    private String optimalSeason;
    private int daysToHarvest;
    private String description;

    public Crop(int cropId, String name, String optimalSeason, int daysToHarvest, String description) {
        this.cropId = cropId;
        this.name = name;
        this.optimalSeason = optimalSeason;
        this.daysToHarvest = daysToHarvest;
        this.description = description;
    }

    public Crop(String name, String optimalSeason, int daysToHarvest, String description) {
        this(0, name, optimalSeason, daysToHarvest, description);
    }

    // Getters and Setters
    public int getCropId() { return cropId; }
    public void setCropId(int cropId) { this.cropId = cropId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOptimalSeason() { return optimalSeason; }
    public void setOptimalSeason(String optimalSeason) { this.optimalSeason = optimalSeason; }

    public int getDaysToHarvest() { return daysToHarvest; }
    public void setDaysToHarvest(int daysToHarvest) { this.daysToHarvest = daysToHarvest; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
