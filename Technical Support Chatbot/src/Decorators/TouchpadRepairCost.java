package Decorators;

import Products.Product;

public class TouchpadRepairCost extends RepairCostDecorator {

    public TouchpadRepairCost(Product product) {
        super(product);
    }

    @Override
    public String getPartType() {
        return "touchpad"; 
    }
}
