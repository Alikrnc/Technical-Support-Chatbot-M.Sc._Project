import java.util.Scanner;

import CoR.ChatContext;
import Singleton.ChatManager;

public class ChatbotApplication {
    public static void main(String[] args)
    {

        ChatManager chatManager = ChatManager.getInstance();

        if(!chatManager.openChat()) // Try to open a chat window.
        {
            System.out.println("Chat is already running. Exiting application...");
            return;
        }

        ChatContext context = new ChatContext();
        Scanner scanner = new Scanner(System.in); // Used for handling user input
        String input = "0"; // Used for handling user input

            while (input != null)
            {
                context.displayMenu();  // Display the current menu.
                input = scanner.nextLine();   
                context.handleInput(input);  // Process the user's input.
            }
                
        scanner.close();  // Close the scanner when done
        chatManager.closeChat();  // Close the chat screen.
    }
}
