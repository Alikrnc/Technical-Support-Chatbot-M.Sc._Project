package Decorators;

import Products.Product;

public class EarpadRepairCost extends RepairCostDecorator {

    public EarpadRepairCost(Product product) {
        super(product);
    }

    @Override
    public String getPartType() {
        return "earpad"; 
    }
}