package State;

import CoR.ChatContext;
import Factory.StateFactory;
import Observer.ChatObserver;
import Observer.SupportAgent;
import java.util.ArrayList;
import java.util.List;

// Manages the live support chat state where users can interact with support agents
public class SupportState implements ChatState
{
    private ChatContext context;
    private List<ChatObserver> observers;
    private boolean isConnected;

    // Initialize support state and add a default support agent
    public SupportState(ChatContext context)
    {
        this.context = context;
        this.observers = new ArrayList<>();
        this.isConnected = false;
        // Add default support agent named John
        addObserver(new SupportAgent("John"));
    }

    // Register new observer (support agent) to receive chat messages
    public void addObserver(ChatObserver observer) {
        observers.add(observer);
    }

    // Notify all support agents about new customer message
    public void notifyObservers(String message) {
        for (ChatObserver observer : observers) {
            observer.update(message);
        }
    }

    // Display initial connection message and prompt
    @Override
    public void displayMenu()
    {
        if (!isConnected) {
            System.out.println("\nConnecting to support team...");
            System.out.println("Agent John has joined the chat.");
            System.out.println("\nPlease type your message or 'R' to return to main menu:");
            isConnected = true;
        }
    }

    // Handle user input: return to menu, exit, or send message to agents
    @Override
    public void handleInput(String input)
    {
        if (input.equalsIgnoreCase("R")) {
            context.setState(StateFactory.creatState("MainMenuState", context));
            return;
        } else if (input.equalsIgnoreCase("Q")) {
            context.exitApplication();
            return;
        }

        // Forward user message to all support agents
        notifyObservers(input);
    }
}
