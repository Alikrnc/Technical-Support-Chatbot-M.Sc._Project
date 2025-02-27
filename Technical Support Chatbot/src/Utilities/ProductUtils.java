package Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Decorators.*;
import Products.*;

public class ProductUtils {

    private static final String[] POSSIBLE_PATHS = {
        "bin\\service_cost_test.csv",
        "service_cost_test.csv",
        "src\\service_cost_test.csv"
    };

    // Get the product by tracking number
    public static Product getProductByTrackingNumber(String trackingNumber) {
        for (String path : POSSIBLE_PATHS) {
            Product product = tryReadProductFromFile(path, trackingNumber);
            if (product != null) {
                return product;
            }
        }
        System.out.println("Could not find product with tracking number: " + trackingNumber);
        return null;
    }

    private static Product tryReadProductFromFile(String filePath, String trackingNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(trackingNumber)) {
                    String productType = data[2];
                    String partType = data[3];
                    String damageType = data[4];
                    String serviceDate = data[5];
                    String warrantyStartDate = data[6];
                    String warrantyEndDate = data[7];
                    double repairCost = Double.parseDouble(data[8]);
                    String status = data[9];

                    Product product = createProduct(trackingNumber, productType, partType, 
                        damageType, warrantyStartDate, warrantyEndDate, 
                        repairCost, serviceDate, status);

                    // Add the repair cost based on the part type using decorators
                    if (partType.equalsIgnoreCase("battery")) {
                        product = new BatteryRepairCost(product);
                    } else if (partType.equalsIgnoreCase("screen")) {
                        product = new ScreenRepairCost(product); 
                    } else if (partType.equalsIgnoreCase("speaker")) {
                        product = new SpeakerRepairCost(product); 
                    } else if (partType.equalsIgnoreCase("touchpad")) {
                        product = new TouchpadRepairCost(product); 
                    } else if (partType.equalsIgnoreCase("keyboard")) {
                        product = new KeyboardRepairCost(product); 
                    } else if (partType.equalsIgnoreCase("earpad")) {
                        product = new EarpadRepairCost(product); 
                    } else if (partType.equalsIgnoreCase("receiver")) {
                        product = new ReceiverRepairCost(product); 
                    } else {
                        System.out.println("Unknown Part.");
                        break;
                    }
                    
                    return product;
                }
            }
        } catch (IOException e) {
            // Silent catch - we'll try the next path
        }
        return null;
    }

    // Create the appropriate concrete product object based on product type
    private static Product createProduct(String productTrackingNo, String productType, String partType, String damageType, 
                                         String warrantyStartDate, String warrantyEndDate, double repairCost, 
                                         String serviceDate, String status) {
        switch (productType.toLowerCase()) {
            case "phone":
                return new Phone(productTrackingNo, productType, partType, damageType, warrantyStartDate, warrantyEndDate, repairCost, serviceDate, status);
            case "tv":
                return new Tv(productTrackingNo, productType, partType, damageType, warrantyStartDate, warrantyEndDate, repairCost, serviceDate, status);
            case "headphone":
                return new Headphone(productTrackingNo, productType, partType, damageType, warrantyStartDate, warrantyEndDate, repairCost, serviceDate, status);
            case "notebook":
                return new Notebook(productTrackingNo, productType, partType, damageType, warrantyStartDate, warrantyEndDate, repairCost, serviceDate, status);
            default:
                throw new IllegalArgumentException("Unknown product type: " + productType);
        }
    }
}
