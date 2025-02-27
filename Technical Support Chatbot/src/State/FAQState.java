package State;

import CoR.ChatContext;
import Factory.StateFactory;

public class FAQState implements ChatState
{
    private ChatContext context;

    public FAQState(ChatContext context)
    {
        this.context = context;
    }

    public void displayFAQ()
    {
        System.out.println();
        System.out.println("\nQ: When will my product be shipped?\n" +
                        "A: Your product will be shipped once the repair process is completed. You will receive an email or SMS notification once your product is ready for shipment.");
        System.out.println("\nQ: How much do service fees cost?\n" +
                        "A: Service fees vary depending on the product type and the type of issue. For detailed pricing, please contact the service center for your specific product.");
        System.out.println("\nQ: What is the product warranty period?\n" +
                        "A: The warranty period varies by product. Generally, we offer a 1-year warranty. For warranty terms specific to your product, please contact the service center.");
        System.out.println("\nQ: What should I do before sending my product to service?\n" +
                        "A: Please back up any personal data on your device and ensure the device has sufficient battery. You should also include the warranty card when sending the product to service.");
        System.out.println("\nQ: How long will it take for my product to return after being serviced?\n" +
                        "A: The repair time depends on the nature of the issue and service workload. Typically, repairs take 7-14 business days, after which the product will be returned to you.");
        System.out.println();
    }

    @Override
    public void displayMenu()
    {
        displayFAQ();
        System.out.println("1) Contact Information");
        System.out.println("R) Main Menu");
        System.out.println("Q) Exit");
    }

    @Override
    public void handleInput(String input)
    {
        switch(input)
        {
            case "1":
                context.setState(StateFactory.creatState("ContactState", context));
                break;
                
            case "r":
            case "R":
                context.setState(StateFactory.creatState("MainMenuState", context));
                break;
                
            case "Q":
            case "q":
                context.exitApplication();
                break;

            default:
                System.out.println();    
                System.out.println("Unknown choice, please select an input from below.");
                this.context.setState(this);  // Return to the same state
        }

    }
    
}
