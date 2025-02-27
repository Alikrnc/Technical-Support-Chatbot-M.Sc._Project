package Decorators;

import Products.Product;

public class BatteryRepairCost extends RepairCostDecorator {

    public BatteryRepairCost(Product product) {
        super(product);
    }

    @Override
        public String getPartType() {
            return "battery"; 
        }
}