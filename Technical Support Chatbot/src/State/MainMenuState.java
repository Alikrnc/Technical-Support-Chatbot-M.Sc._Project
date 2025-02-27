package State;

import CoR.ChatContext;
import Factory.StateFactory;

public class MainMenuState implements ChatState
{
    private ChatContext context;

    public MainMenuState(ChatContext context)
    {
        this.context = context;
    }

    @Override
    public void displayMenu()
    {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("1) FAQ");
        System.out.println("2) Information about my Product");
        System.out.println("3) Send us your complaints");
        System.out.println("4) Contact live support.");
        System.out.println("Q) Exit");
    }

    @Override
    public void handleInput(String input)
    {    
        switch(input)
        {
            case "1":
                context.setState(StateFactory.creatState("FAQState", context));
                break;
                
            case "2":
                context.setState(StateFactory.creatState("InfoState", context));
                break;
            
            case "3":
                context.setState(StateFactory.creatState("ComplaintState", context));  
                break;
            
            case "4":
                context.setState(StateFactory.creatState("SupportState", context));
                break;
                
            case "Q":
            case "q":
                context.exitApplication();
                break;
                
            default:
                System.out.println();    
                System.out.println("Unknown choice, please select an input from below.");
                this.context.setState(this);  // Return to the same state.
                break;
        }

    }
    
}
