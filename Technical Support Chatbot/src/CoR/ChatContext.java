package CoR;

import java.util.Scanner;

import Factory.StateFactory;
import State.ChatState;

// Main context class that manages chat states and user interactions
public class ChatContext {
    private ChatState currentState;
    private Scanner scanner;

    // Initialize context with scanner and set initial state
    public ChatContext() {
        this.scanner = new Scanner(System.in);
        // Start with intro state when chat begins to display welcome message
        this.currentState = StateFactory.creatState("IntroState", this);
    }

    public Scanner getScanner() {
        return scanner;
    }

    // Changes the current state of the chat
    public void setState(ChatState state) {
        this.currentState = state;
    }

    // Shows the menu for current state
    public void displayMenu() {
        currentState.displayMenu();
    }

    // Processes user input based on current state
    public void handleInput(String input) {
        currentState.handleInput(input);
    }

    // Shows standard return/exit options
    public void displayExitMenu() {
        System.out.println("R) Back to main menu");
        System.out.println("Q) Exit");
    }

    // Handles return/exit menu selections
    public void displayExitMenu(String input) {
        switch (input.toLowerCase()) {
            case "r":
                setState(StateFactory.creatState("MainMenuState", this));
                break;
            case "q":
                exitApplication();
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
    }

    public void exitApplication() {
        System.out.println();
        System.out.println("Thank you for reaching us!");
        System.out.println("Have a great day...");
        scanner.close();
        System.exit(0);
    }
}

