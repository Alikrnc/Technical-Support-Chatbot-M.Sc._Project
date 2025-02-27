package Decorators;

import Products.Product;

public class ReceiverRepairCost extends RepairCostDecorator {

    public ReceiverRepairCost(Product product) {
        super(product);
    }

    @Override
    public String getPartType() {
        return "receiver";
    }
}
