package Products;

import Utilities.WarrantyRules;

public abstract class Product {
    private String productTrackingNo;
    private String productType;
    private String damageType;
    private String warrantyStartDate;
    private String warrantyEndDate;
    private String serviceDate; 
    private double repairCost;
    private String status;
    protected String partType;
    protected WarrantyRules warrantyRules;

    public Product(String productTrackingNo, String productType, String partType, String damageType, String warrantyStartDate, String warrantyEndDate, 
                   double repairCost, String serviceDate, String status) {
        this.productTrackingNo = productTrackingNo;
        this.productType = productType;
        this.partType = partType;
        this.damageType = damageType;
        this.warrantyStartDate = warrantyStartDate;
        this.warrantyEndDate = warrantyEndDate;
        this.repairCost = repairCost;
        this.serviceDate = serviceDate;
        this.status = status;
        this.warrantyRules = new WarrantyRules(); 
    }

    // Getter methods
    public String getProductTrackingNo() {
        return productTrackingNo;
    }
    public String getWarrantyEndDate() {
        return warrantyEndDate;
    }
    public String getServiceDate() {
        return serviceDate;
    }
    public String getProductType() {
        return productType;
    }
    public String getWarrantyStartDate() {
        return warrantyStartDate;
    }
    public double getRepairCost() {
        return repairCost;
    }
    public String getDamageType() {
        return damageType;
    }
    public WarrantyRules getWarrantyRules() {
        return warrantyRules;
    }
    public String getStatus() {
        return status;
    }

    public String getPartType() {
        return partType; // This will not be changed in the base class, but in the decorators.
    }

    public void setPartType(String partType) {
        this.partType = partType; // Setter to change the PartType
    }
    
    public abstract double calculateServiceFee();
}
