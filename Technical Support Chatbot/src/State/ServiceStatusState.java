package State;

import CoR.ChatContext;
import Factory.StateFactory;
import Products.Product;
import Utilities.ProductUtils;

public class ServiceStatusState implements ChatState {

    private ChatContext context;
    private Product product;

    public ServiceStatusState(ChatContext context) {
        this.context = context;
    }

    @Override
    public void displayMenu() {
        System.out.println("Enter the product tracking number:");
    }

    @Override
    public void handleInput(String input) {
        if (input.equalsIgnoreCase("R")) {
            context.setState(StateFactory.creatState("MainMenuState", context));
            return;
        } else if (input.equalsIgnoreCase("Q")) {
            context.exitApplication();
            return;
        }

        // Get the product by tracking number
        product = ProductUtils.getProductByTrackingNumber(input);

        if (product == null) {
            System.out.println("Product not found. Please try again.");
            return;
        }

        // Display service details
        System.out.println("Product Tracking Number: " + product.getProductTrackingNo());
        System.out.println("Product Type: " + product.getProductType());
        System.out.println("Service Date: " + product.getServiceDate());
        System.out.println("Warranty End Date: " + product.getWarrantyEndDate());
        System.out.println("Part Type: " + product.getPartType());
        System.out.println("Damage Type: " + product.getDamageType());
        System.out.println("Status: " + product.getStatus());
        System.out.println("Total Service Fee: " + product.calculateServiceFee());
        System.out.println();

        context.displayExitMenu("r");
    }
}
