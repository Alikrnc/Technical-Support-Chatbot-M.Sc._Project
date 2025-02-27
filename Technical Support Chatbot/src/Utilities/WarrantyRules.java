package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WarrantyRules {

    // Checks if the warranty period is valid
    public boolean isWarrantyValid(String productTrackingNo, String serviceDate, String warrantyEndDate) {
        // Check the date format and convert it to the correct format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Expected format: yyyy-MM-dd

        try {
            // Convert serviceDate and warrantyEndDate to LocalDate format
            LocalDate serviceDateParsed = LocalDate.parse(serviceDate, formatter);
            LocalDate warrantyEndDateParsed = LocalDate.parse(warrantyEndDate, formatter);

            // Warranty is valid if warrantyEndDate is after serviceDate
            return !warrantyEndDateParsed.isBefore(serviceDateParsed); // Valid if warrantyEndDate >= serviceDate
        } catch (Exception e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return false; // If the date format is incorrect, consider the warranty invalid
        }
    }

    // Checks if a specific part type and damage are covered by the warranty
    public boolean isPartCovered(String productType, String partType, String damageDescription) {
        // Check the part coverage rules based on previously defined product types
        switch (productType.toLowerCase()) {
            case "phone":
                if (partType.equals("screen") && damageDescription.contains("cracked")) return false;
                if (partType.equals("battery") && damageDescription.contains("liquid")) return false;
                if (partType.equals("speaker") && damageDescription.contains("liquid")) return false;
                break;
            case "tv":
                if (partType.equals("screen") && damageDescription.contains("cracked")) return false;
                if (partType.equals("receiver") && (damageDescription.contains("cracked") || damageDescription.contains("liquid"))) return false;
                if (partType.equals("speaker") && damageDescription.contains("liquid")) return false;
                break;
            case "headphone":
                if (partType.equals("battery") && damageDescription.contains("liquid")) return false;
                if (partType.equals("earpad") && (damageDescription.contains("cracked") || damageDescription.contains("liquid"))) return false;
                break;
            case "notebook":
                if (partType.equals("screen") && damageDescription.contains("cracked")) return false;
                if (partType.equals("battery") && damageDescription.contains("liquid")) return false;
                if (partType.equals("speaker") && damageDescription.contains("liquid")) return false;
                if (partType.equals("keyboard") && damageDescription.contains("liquid")) return false;
                if (partType.equals("touchpad") && damageDescription.contains("liquid")) return false;
                break;
        }
        return true; // If the part is covered by warranty
    }
}
