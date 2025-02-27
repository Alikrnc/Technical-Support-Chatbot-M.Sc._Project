package Factory;

import CoR.ChatContext;
import State.*;

// Factory class that creates different chat states based on the requested state type
// This provides loose coupling between ChatContext and individual state implementations
public class StateFactory 
{
    // Creates and returns appropriate ChatState instance based on stateType parameter
    public static ChatState creatState(String stateType, ChatContext context)
    {
        switch (stateType)
        {
            case "IntroState":
                return new IntroState(context);

            case "MainMenuState":
                return new MainMenuState(context);

            case "FAQState":
                return new FAQState(context);

            case "ContactState":
                return new ContactState(context);
            
            case "InfoState":
                return new InfoState(context);

            case "ComplaintState":
                return new ComplaintState(context);

            case "SupportState":
                return new SupportState(context);

            default:
                throw new IllegalArgumentException("Unknown State: " + stateType);
        }
    }
}
