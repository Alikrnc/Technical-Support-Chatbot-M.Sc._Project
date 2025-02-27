package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import CoR.ChatContext;
import Singleton.ChatManager;

public class ChatbotUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private ChatContext context;
    private ChatManager chatManager;
    private JPanel startPanel;
    private JPanel chatPanel;

    public ChatbotUI() {
        setTitle("Pineapple Technical Support Chat");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Start Panel
        startPanel = new JPanel(new GridBagLayout());
        JButton startButton = new JButton("Start Chat");
        startButton.addActionListener(e -> startChat());
        startPanel.add(startButton);

        // Chat Panel
        chatPanel = new JPanel(new BorderLayout());
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setWrapStyleWord(true);
        chatArea.setLineWrap(true);
        
        JScrollPane scrollPane = new JScrollPane(chatArea);
        inputField = new JTextField();
        sendButton = new JButton("Send");

        chatPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        chatPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Initially show start panel
        setLayout(new BorderLayout());
        add(startPanel, BorderLayout.CENTER);

        // Event listeners
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    private void startChat() {
        chatManager = ChatManager.getInstance();
        if (!chatManager.openChat()) {
            JOptionPane.showMessageDialog(this, "Chat is already running in another window!");
            return;
        }

        // Create a new JFrame and add the chat panel
        JFrame chatWindow = new JFrame("Chat Window");
        chatWindow.setSize(600, 400);
        chatWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chatWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                chatManager.closeChat();
                chatWindow.dispose();
            }
        });
        
        // Position new chat window next to main window
        Point mainLocation = this.getLocation();
        chatWindow.setLocation(mainLocation.x + this.getWidth() + 10, mainLocation.y);
        
        chatWindow.setLayout(new BorderLayout());
        chatWindow.add(chatPanel, BorderLayout.CENTER);
        chatWindow.setVisible(true);

        // Set up chat environment and initial display
        context = new ChatContext();
        setupSystemOut();
        context.displayMenu();

        // Focus on input field for immediate typing
        inputField.requestFocusInWindow();
    }

    // Configure system output to handle chat messages
    private void setupSystemOut() {
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            private StringBuilder buffer = new StringBuilder();
            private String lastLine = "";

            @Override
            public void write(int b) {
                char c = (char) b;
                buffer.append(c);
                if (c == '\n') {
                    String currentLine = buffer.toString();
                    // Prevent duplicate prompts
                    if (!currentLine.trim().equals(lastLine.trim())) {
                        SwingUtilities.invokeLater(() -> {
                            chatArea.append(currentLine);
                            chatArea.setCaretPosition(chatArea.getDocument().getLength());
                        });
                        lastLine = currentLine.trim();
                    }
                    buffer.setLength(0);
                }
            }

            @Override
            public void flush() {
                if (buffer.length() > 0) {
                    String currentLine = buffer.toString();
                    if (!currentLine.trim().equals(lastLine.trim())) {
                        SwingUtilities.invokeLater(() -> {
                            chatArea.append(currentLine);
                            chatArea.setCaretPosition(chatArea.getDocument().getLength());
                        });
                        lastLine = currentLine.trim();
                    }
                    buffer.setLength(0);
                }
            }
        }));
    }

    private void sendMessage() {
        String input = inputField.getText().trim();
        if (!input.isEmpty()) {
            chatArea.append("\nYou: " + input + "\n");
            inputField.setText("");
            
            context.handleInput(input);
            context.displayMenu();
            System.out.flush();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatbotUI ui = new ChatbotUI();
            ui.setVisible(true);
        });
    }
} 