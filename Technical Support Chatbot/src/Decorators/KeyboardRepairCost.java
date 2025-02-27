package Decorators;

import Products.Product;

public class KeyboardRepairCost extends RepairCostDecorator {

    public KeyboardRepairCost(Product product) {
        super(product);
    }

    @Override
    public String getPartType() {
        return "keyboard"; 
    }
}
