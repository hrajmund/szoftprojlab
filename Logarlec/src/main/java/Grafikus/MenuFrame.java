package Grafikus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame {
    protected JTextField nameField = new JTextField();
    protected DefaultListModel<String> listModel = new DefaultListModel<>();
    protected JList<String> nameList = new JList<>(listModel);
    
    public MenuFrame() {
        JFrame frame = new JFrame("Menü");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);
        
        JLabel nameLabel = new JLabel("Név:");
        nameLabel.setBounds(10, 10, 50, 20);
        frame.add(nameLabel);
        
        nameField.setBounds(60, 10, 100, 20);
        frame.add(nameField);
        
        JButton addName = new JButton("Név hozzáadása");
        addName.setBounds(10, 40, 150, 20);
        addName.addActionListener(new addNameListener());
        frame.add(addName);
        
        nameList.setBounds(10, 70, 150, 150);
        frame.add(nameList);
        
        JButton gameStart = new JButton("Játék indítása");
        gameStart.setBounds(10, 230, 150, 20);
        gameStart.addActionListener(new gameStartListener());
        frame.add(gameStart);
        
        frame.setVisible(true);
    }
    
    class gameStartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (nameField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Nem adtál meg nevet!");
            } else {
                String name = nameField.getText();
                listModel.addElement(name);
                nameList.setModel(listModel);
                new GamePanel();
            }
        }
    }
    
    class addNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (nameField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Nem adtál meg nevet!");
            } else {
                String name = nameField.getText();
                listModel.addElement(name);
                nameList.setModel(listModel);
            }
        }
    }
    
}
