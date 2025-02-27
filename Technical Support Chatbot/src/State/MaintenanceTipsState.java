package State;

import CoR.ChatContext;
import Factory.StateFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceTipsState implements ChatState {

    private ChatContext context;
    private List<String> productTypes; // Store product types
    private List<String> maintenanceTips; // Store maintenance tips for products
    private static final String[] POSSIBLE_PATHS = {
        "bin\\maintenance_tips.csv",
        "maintenance_tips.csv",
        "src\\maintenance_tips.csv"
    };

    public MaintenanceTipsState(ChatContext context) {
        this.context = context;
        this.productTypes = new ArrayList<>();
        this.maintenanceTips = new ArrayList<>();
    }

    @Override
    public void displayMenu() {
        System.out.println("Select a product to view maintenance tips:");

        boolean fileRead = false;
        
        for (String path : POSSIBLE_PATHS) {
            if (tryReadMaintenanceTips(path)) {
                fileRead = true;
                break;
            }
        }

        if (!fileRead) {
            System.out.println("Error: Could not read maintenance tips file from any location");
            return;
        }

        // Provide options to return to the main menu or exit
        System.out.println("R) Back to main menu");
        System.out.println("Q) Exit");
    }

    @Override
    public void handleInput(String input) {
        // Handle user input for product selection
        if (input.equalsIgnoreCase("R")) {
            context.setState(StateFactory.creatState("MainMenuState", context)); // return to main menu
        } else if (input.equalsIgnoreCase("Q")) {
            context.exitApplication(); // exit application
        } else {
            try {
                // If the user enters a number, display the maintenance tips for that product
                int productIndex = Integer.parseInt(input) - 1;
                if (productIndex >= 0 && productIndex < productTypes.size()) {
                    String selectedProductType = productTypes.get(productIndex);
                    String maintenanceTipsForProduct = maintenanceTips.get(productIndex);

                    // Display the maintenance tips in the correct format
                    System.out.println("Displaying maintenance tips for product: " + selectedProductType);
                    displayFormattedMaintenanceTips(maintenanceTipsForProduct, selectedProductType);
                } else {
                    System.out.println("Invalid selection. Please choose a valid product number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to a product.");
            }
        }
    }

    // Method to display maintenance tips in a more readable format
    private void displayFormattedMaintenanceTips(String maintenanceTips, String selectedProductType) {
        // Split the tips by hyphen and space, and display them as bullet points
        String[] tips = maintenanceTips.split("\\- "); // Split by hyphen and space
        System.out.println("\nMaintenance Tips for " + selectedProductType + ":\n");
        for (int i = 1; i < tips.length; i++) {
            // Display the maintenance tips with numbering
            System.out.println(i + ". " + tips[i].trim() + "\n");
        }
    }

    private boolean tryReadMaintenanceTips(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip the header
            int index = 1;
            
            productTypes.clear();
            maintenanceTips.clear();
            
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println(index + ") " + data[0]);
                productTypes.add(data[0]);
                maintenanceTips.add(data[1]);
                index++;
            }
            return true;
        } catch (IOException e) {
            // Silent catch - we'll try the next path
            return false;
        }
    }
}
