package State;

import CoR.ChatContext;
import Factory.StateFactory;

public class IntroState implements ChatState
{
    private ChatContext context;

    public IntroState(ChatContext context)
    {
        this.context = context;
    }

    @Override
    public void displayMenu()
    {
        System.out.println();
        System.out.println("Welcome to the Pineapple Technical Support Chat!");
        context.setState(StateFactory.creatState("MainMenuState", context)); // we are calling the MainMenu from here since rest of the code is the same.
        context.displayMenu();
    }
    
    @Override
    public void handleInput(String input) {
        // No implementation neededed since we are calling the MainMenu above.
    }
}
    