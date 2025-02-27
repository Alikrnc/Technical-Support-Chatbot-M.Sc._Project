package Decorators;

import Products.Product;

public class SpeakerRepairCost extends RepairCostDecorator {

    public SpeakerRepairCost(Product product) {
        super(product);
    }

    @Override
    public String getPartType() {
        return "speaker"; 
    }
}
