package State;

import CoR.ChatContext;

public class ComplaintState implements ChatState
{
    private ChatContext context;

    public ComplaintState(ChatContext context)
    {
        this.context = context;
    }

    // The complaint form will be sent to the QA or any related team.

    @Override
    public void displayMenu()
    {
        System.out.println();
        System.out.println("In order to leave a complaint you should fill the paper by visiting the internet adress below:");
        System.out.println("https://complaint.pineapple.com/"); // Google Forms can also be used.
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
