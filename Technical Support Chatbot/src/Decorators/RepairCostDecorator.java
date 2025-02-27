package Decorators;

import Products.Product;

public abstract class RepairCostDecorator extends Product {
    protected Product product;

    public RepairCostDecorator(Product product) {
        super(
            product.getProductTrackingNo(),
            product.getProductType(),
            product.getPartType(),
            product.getDamageType(),
            product.getWarrantyStartDate(),
            product.getWarrantyEndDate(),
            product.getRepairCost(),
            product.getServiceDate(),
            product.getStatus()
        );
        this.product = product; // We will use the passed Product object within this class
    }

    @Override
    public String getPartType() {
        return product.getPartType(); // The decorator will use this method to change the partType
    }

    @Override
    public void setPartType(String partType) {
        product.setPartType(partType); // The decorator will change the partType
    }

    @Override
    public double calculateServiceFee() {
        boolean isWarrantyValid = product.getWarrantyRules().isWarrantyValid(
            product.getProductTrackingNo(),
            product.getServiceDate(),
            product.getWarrantyEndDate()
        );

        boolean isPartCovered = product.getWarrantyRules().isPartCovered(
            product.getProductType(),
            getPartType(),  // The part type passed from the subclasses
            product.getDamageType()
        );

        double serviceFee = 0.0;
        if (isWarrantyValid && isPartCovered) {
            serviceFee = 0.0;
        } else if (isWarrantyValid) {
            serviceFee = product.getRepairCost();
        } else {
            serviceFee = product.getRepairCost() + product.calculateServiceFee();  
        }

        return serviceFee;
    }
}
