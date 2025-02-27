package Decorators;

import Products.Product;

public class ScreenRepairCost extends RepairCostDecorator {

    public ScreenRepairCost(Product product) {
        super(product);
    }

    @Override
    public String getPartType() {
        return "screen"; 
    }
}
