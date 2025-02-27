package State;

import CoR.ChatContext;

public class InfoState implements ChatState {

    private ChatContext context;

    public InfoState(ChatContext context) {
        this.context = context;
    }

    @Override
    public void displayMenu() {
        System.out.println("Information about my Product:");
        System.out.println("1. Check warranty status by product tracking number");
        System.out.println("2. Check service status by product tracking number");
        System.out.println("3. Maintenance tips");
        System.out.println("R. Back to main menu");
        System.out.println("Q. Exit");
    }

    @Override
    public void handleInput(String input) {
        switch (input) {
            case "1":
                context.setState(new WarrantyStatusState(context)); // Switch to warranty status check
                break;
            case "2":
                context.setState(new ServiceStatusState(context));  // Switch to service status check menu
                break;
            case "3":
                context.setState(new MaintenanceTipsState(context));  // Switch to maintenance tips menu
                break;
            case "r":
            case "R":
                context.setState(new MainMenuState(context));  // Return to main menu
                break;
            case "q":
            case "Q":
                context.exitApplication();  // Exit the application
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
        }
    }
}
