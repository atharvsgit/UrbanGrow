package model;

import java.util.Date;

public class Harvest {
    private int harvestId;
    private int plantingId;
    private Date harvestDate;
    private double quantityKg;

    public Harvest(int harvestId, int plantingId, Date harvestDate, double quantityKg) {
        this.harvestId = harvestId;
        this.plantingId = plantingId;
        this.harvestDate = harvestDate;
        this.quantityKg = quantityKg;
    }

    public Harvest(int plantingId, Date harvestDate, double quantityKg) {
        this(0, plantingId, harvestDate, quantityKg);
    }

    // Getters and Setters
    public int getHarvestId() { return harvestId; }
    public void setHarvestId(int harvestId) { this.harvestId = harvestId; }

    public int getPlantingId() { return plantingId; }
    public void setPlantingId(int plantingId) { this.plantingId = plantingId; }

    public Date getHarvestDate() { return harvestDate; }
    public void setHarvestDate(Date harvestDate) { this.harvestDate = harvestDate; }

    public double getQuantityKg() { return quantityKg; }
    public void setQuantityKg(double quantityKg) { this.quantityKg = quantityKg; }
}
