package State;

import CoR.ChatContext;
import Factory.StateFactory;
import Products.Product;
import Utilities.ProductUtils;

public class WarrantyStatusState implements ChatState {

    private ChatContext context;

    public WarrantyStatusState(ChatContext context) {
        this.context = context;
    }

    @Override
    public void displayMenu() {
        System.out.println("Enter the product tracking number to check warranty status:");
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

        // Get product by tracking number
        Product product = ProductUtils.getProductByTrackingNumber(input);

        if (product == null) {
            System.out.println("Product not found. Please try again.");
            return;
        }

        // Check warranty status
        boolean isWarrantyValid = product.getWarrantyRules().isWarrantyValid(
            product.getProductTrackingNo(), 
            product.getServiceDate(), 
            product.getWarrantyEndDate()
        );

        System.out.println("Warranty status for product: " + input);
        System.out.println("Warranty Valid: " + (isWarrantyValid ? "Yes" : "No"));
        System.out.println();
        
        context.displayExitMenu("r");
    }  
}
