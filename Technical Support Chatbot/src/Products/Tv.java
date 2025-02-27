package Products;

public class Tv extends Product {
    public Tv(String productTrackingNo, String productType, String partType, String damageType, String warrantyStartDate, String warrantyEndDate, 
    double repairCost, String serviceDate, String status) {
        super(productTrackingNo, productType, partType, damageType, warrantyStartDate, warrantyEndDate, repairCost, serviceDate, status);
    }

    @Override
    public double calculateServiceFee() {
        if (warrantyRules.isWarrantyValid(getProductTrackingNo(), getServiceDate(), getWarrantyEndDate())) {
            return 0.0; // No service fee if the product is under warranty
        }
        return 60.0; // Non-warranty service fee for TV
    }
}
