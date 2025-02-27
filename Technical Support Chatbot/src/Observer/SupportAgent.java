package Observer;

// Represents a support agent that can respond to customer messages
public class SupportAgent implements ChatObserver {
    private String name;
    
    public SupportAgent(String name) {
        this.name = name;
    }

    // Handles incoming customer messages and generates a response
    @Override
    public void update(String message) {
        // Print agent's response with their name
        System.out.println("\nAgent " + name + ": " + getRandomResponse());
    }

    // Returns a random pre-defined response to simulate agent interaction
    private String getRandomResponse() {
        String[] responses = {
            "How can I help you today?",
            "I understand your concern. Could you provide more details?",
            "I'm checking your issue. One moment please.",
            "Let me help you with that.",
            "Is there anything else you'd like to know?"
        };
        return responses[(int)(Math.random() * responses.length)];
    }
} 