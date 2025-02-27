package Singleton;

public class ChatManager
{
    private static ChatManager instance;
    private boolean isChatOpen;

    // Singleton class to have only one chat instance per user.

    private ChatManager()
    {
        isChatOpen = false;
    }

    public static ChatManager getInstance() // global access
    {
        if (instance == null)
        {
            instance = new ChatManager();            
        }
        return instance;
    }

    public boolean openChat()
    {
        if(isChatOpen)
        {
            System.out.println("Chat Screen is already opened.");
            return false;
        }

        else
        {
            isChatOpen = true;
            System.out.println("Opening chat...");
            return true;
        }
    }

    public void closeChat()
    {
        isChatOpen = false;
        instance = null;
        System.out.println("Chat screen is now closed.");
    }
    
}
