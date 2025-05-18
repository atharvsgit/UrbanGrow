package model;

import java.util.Date;

public class Planting {
    private int plantingId;
    private int userId;
    private int plotId;
    private int cropId;
    private Date plantedOn;
    private Date expectedHarvestDate;

    public Planting(int plantingId, int userId, int plotId, int cropId, Date plantedOn, Date expectedHarvestDate) {
        this.plantingId = plantingId;
        this.userId = userId;
        this.plotId = plotId;
        this.cropId = cropId;
        this.plantedOn = plantedOn;
        this.expectedHarvestDate = expectedHarvestDate;
    }

    public Planting(int userId, int plotId, int cropId, Date plantedOn, Date expectedHarvestDate) {
        this(0, userId, plotId, cropId, plantedOn, expectedHarvestDate);
    }

    // Getters and Setters
    public int getPlantingId() { return plantingId; }
    public void setPlantingId(int plantingId) { this.plantingId = plantingId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getPlotId() { return plotId; }
    public void setPlotId(int plotId) { this.plotId = plotId; }

    public int getCropId() { return cropId; }
    public void setCropId(int cropId) { this.cropId = cropId; }

    public Date getPlantedOn() { return plantedOn; }
    public void setPlantedOn(Date plantedOn) { this.plantedOn = plantedOn; }

    public Date getExpectedHarvestDate() { return expectedHarvestDate; }
    public void setExpectedHarvestDate(Date expectedHarvestDate) { this.expectedHarvestDate = expectedHarvestDate; }
}
