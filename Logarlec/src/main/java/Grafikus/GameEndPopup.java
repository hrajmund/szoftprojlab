package Grafikus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GameEndPopup extends JFrame implements ActionListener {

    private MenuFrame menuFrame; // Reference to the GameMenu
    private JLabel resultLabel; // Label for game result (Victory/Game Over)
    private JButton backButton; // "Back" button to return to the GameMenu
    private boolean isGameOver; // Flag indicating game over or victory

    // Constructor
    public GameEndPopup(MenuFrame menuFrame, boolean isGameOver) {
        this.menuFrame = menuFrame;
        this.isGameOver = isGameOver;

        // Frame setup
        setTitle("Game Result");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Closes only this window
        setSize(400, 300);
        setResizable(false);
        setLayout(null);

        // Create components
        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setBounds(10, 20, 380, 50);
        add(resultLabel);

        backButton = new JButton("Back");
        backButton.setBounds(170, 100, 100, 30);
        backButton.addActionListener(this);
        add(backButton);

        // Set result text and image based on isGameOver
        if (isGameOver) {
            resultLabel.setText("GAME OVER");
            loadImage("game_over.png");
        } else {
            resultLabel.setText("VICTORY!");
            loadImage("victory.png");
        }
    }

    private void loadImage(String imageName) {
        try {
            URL url = getClass().getResource("/" + imageName);
            Image image = new ImageIcon(url).getImage();
            JLabel backgroundLabel = new JLabel(new ImageIcon(image));
            backgroundLabel.setBounds(0, 0, 400, 300);
            add(backgroundLabel);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imageName);
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Dispose of this window and return to GameMenu
            dispose();
            menuFrame.setVisible(true); // Make GameMenu visible again
        }
    }
}
