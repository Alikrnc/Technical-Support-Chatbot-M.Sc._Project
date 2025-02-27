package State;

import CoR.ChatContext;

public class ContactState implements ChatState
{
    private ChatContext context;

    public ContactState(ChatContext context)
    {
        this.context = context;
    }

    @Override
    public void displayMenu()
    {
        System.out.println();
        System.out.println("Mail Address: support@pineapple.com");
        System.out.println("Customer Service: 1-800-254-pine (7463)");
        System.out.println("You can also reach us on all social media sites with @pineapple");
        System.out.println();
        System.out.println("R) Back to main menu");
        System.out.println("Q) Exit");
    }

    @Override
    public void handleInput(String input)
    {
        context.displayExitMenu(input);
    }
}
