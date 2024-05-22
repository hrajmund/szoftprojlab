package Grafikus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * A játékmenüt megvalósító osztály
 */
public class MenuFrame extends JFrame{

    MenuFrame menuFrame = this;
    // Components
    private JLabel gameNameLabel;
    private JList<String> playerList;
    private JScrollPane playerListScrollPane;
    private DefaultListModel<String> playerListModel;
    private JTextField playerNameField;
    
    private JButton addButton;
    private JButton startButton;
    private JButton exitButton;

    // Data
    private ArrayList<String> players;

    /**
     * Konstruktor
     */
    public MenuFrame() {
        // Frame setup
        setTitle("Logarléc");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400); // Updated frame size
        setResizable(false);
        setLayout(null);
        setBackground(Color.BLACK);

        // Create components
        gameNameLabel = new JLabel("LOGARLÉC", SwingConstants.CENTER);
        gameNameLabel.setBounds(45, 5, 300, 50);
        gameNameLabel.setFont(new Font(Font.MONOSPACED,  Font.BOLD, 50));
        add(gameNameLabel);

        playerListModel = new DefaultListModel<>();
        playerList = new JList<>(playerListModel);
        playerListScrollPane = new JScrollPane(playerList);
        playerListScrollPane.setBounds(45, 60, 300, 120);
        add(playerListScrollPane);

        JLabel nameText = new JLabel("Name: ");
        nameText.setBounds(45, 220, 40, 20);
        add(nameText);
        playerNameField = new JTextField();
        playerNameField.setBounds(95, 220, 200, 20);
        add(playerNameField);

        addButton = new JButton("+");
        addButton.setBounds(295, 220, 50, 20);
        addButton.addActionListener(new AddNameListener());
        add(addButton);

        startButton = new JButton("START");
        startButton.setBounds(140, 270, 120, 30); // Adjusted Y position
        startButton.setEnabled(false);
        startButton.addActionListener(new GameStartListener());
        add(startButton);

        exitButton = new JButton("EXIT");
        exitButton.setBounds( 140, 310, 120, 30); // Adjusted Y position
        //lambda function to leaving the game
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);

        // Initialization
        players = new ArrayList<>();
        updatePlayerList();

        //Képernyő közepén nyíljon meg az ablak
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        //háttér beállítása
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    /**
     * Frissíti a játékosok listáját
     */
    private void updatePlayerList() {
        playerListModel.clear();
        for (int i = 0; i < players.size(); i++) {
            playerListModel.addElement((i + 1) + ". Player: " + players.get(i));
        }
    }

    /**
     * A játék indítását kezelő osztály
     */
    class GameStartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Starting game with the following players:");
            for (String player : players) {
                System.out.println("- " + player);
            }
            
            menuFrame.setVisible(false);

            try {
                GuiManager gm = new GuiManager(players, menuFrame);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            playerListModel.clear();
            players.clear();
            updatePlayerList();
        }
    }

    /**
     * A játékos hozzáadását kezelő osztály
     */
    class AddNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (playerNameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nem adtál meg nevet!");
            } else {
                String playerName = playerNameField.getText().trim();
                players.add(playerName);
                updatePlayerList();
                playerNameField.setText("");
                startButton.setEnabled(!players.isEmpty());
            }
        }
    }


    /**
     * Main függvény
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuFrame().setVisible(true));
    }
}
