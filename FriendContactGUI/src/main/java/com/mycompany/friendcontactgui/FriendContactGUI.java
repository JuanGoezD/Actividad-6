import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendContactGUI extends JFrame {
    private JTextField nameField;
    private JTextField numberField;
    private JTextArea outputArea;
    private AddFriend addFriend;
    private DisplayFriends displayFriends;
    private UpdateFriend updateFriend;
    private DeleteFriend deleteFriend;

    public FriendContactGUI() {
        setTitle("Friend Contact GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 1)); // Updated to 4 rows
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // New panel for name input
        JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // New panel for number input
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // New panel for buttons

        JLabel nameLabel = new JLabel("Name:");
        JLabel numberLabel = new JLabel("Number:");

        nameField = new JTextField(15); // Set preferred width for name field
        numberField = new JTextField(15); // Set preferred width for number field

        JButton addButton = new JButton("Add");
        JButton displayButton = new JButton("Display");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        numberPanel.add(numberLabel);
        numberPanel.add(numberField);
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        inputPanel.add(namePanel);
        inputPanel.add(numberPanel);
        inputPanel.add(buttonPanel);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = nameField.getText();
                long newNumber = Long.parseLong(numberField.getText());
                addFriend(newName, newNumber);
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFriends();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = nameField.getText();
                long newNumber = Long.parseLong(numberField.getText());
                updateFriend(newName, newNumber);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameToDelete = nameField.getText();
                deleteFriend(nameToDelete);
            }
        });

        addFriend = new AddFriend();
        displayFriends = new DisplayFriends();
        updateFriend = new UpdateFriend();
        deleteFriend = new DeleteFriend();
    }

    public void addFriend(String newName, long newNumber) {
        try {
            addFriend.addFriend(newName, newNumber);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayFriends() {
        outputArea.setText("");
        String friendsInfo = displayFriends.getFriendsInfo();
        outputArea.append(friendsInfo);
    }

    public void updateFriend(String newName, long newNumber) {
        try {
            updateFriend.updateFriend(newName, newNumber);
            displayFriends();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteFriend(String nameToDelete) {
        try {
            deleteFriend.deleteFriend(nameToDelete);
            displayFriends();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FriendContactGUI gui = new FriendContactGUI();
                gui.setSize(400, 300);
                gui.setVisible(true);
            }
        });
    }
}