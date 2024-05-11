package Grafikus;

import Modell.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiManager extends JFrame{
    
    private JPanel TopPanel;
    private JPanel GraphPanel;
    private JPanel BottomPanel;
    private JPanel ControlButtonPanel;
    private JLabel ItemsLabel;
    private JPanel RoomButtonPanel;

    private JLabel RoundLabel;
    private JLabel PeopleLabel;
    private JLabel RoomItemLabel;
    private JPanel ActualPlayerPanel;
    private JPanel RoomDetailPanel;
    private JPanel RoundPanel;
    private JPanel BasePanel;

    
    
    private JButton PickUpButton;   
    private JButton PutDownButton;  
    private JButton UseButton;      
    private JButton MoveButton;     
    private JButton NextButton;     
    
    
    private JLabel PlayerNameLabel;
    
    private JButton InvItemButton1;
    private JButton InvItemButton2;
    private JButton InvItemButton3;
    private JButton InvItemButton4;
    private JButton InvItemButton5;
    
    
    private JLabel RoomNameLabel;
    
    private JPanel RoomItemsPanel;
    private JPanel RoomPeoplePanel;
    
    private JLabel RoundNumberLabel;
    

    protected GraphComponent graph;
    GameManager gameManager;

    Student currentStudent;
    int pickedInventoryItemIndex = -1;
    int pickedRoomItemIndex = -1;
    Room pickedRoom;
    
    
    private void createUIComponents() {
        PickUpButton.addActionListener(new pickUpButtonListener());
        PutDownButton.addActionListener(new putDownButtonListener());
        UseButton.addActionListener(new useButtonListener());
        MoveButton.addActionListener(new moveButtonListener());
        NextButton.addActionListener(new nextButtonListener());

        InvItemButton1.addActionListener(new inventoryButtonListener(0));
        InvItemButton2.addActionListener(new inventoryButtonListener(1));
        InvItemButton3.addActionListener(new inventoryButtonListener(2));
        InvItemButton4.addActionListener(new inventoryButtonListener(3));
        InvItemButton5.addActionListener(new inventoryButtonListener(4));
    }
    
    public GuiManager(List<String> playerNames){
        graph = new GraphComponent();
        
        gameManager = new GameManager(this, playerNames);
        GraphPanel.add(graph.getView());
        
        setContentPane(BasePanel);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void setCurrentStudent(Student student)
    {
        currentStudent = student;
        PlayerNameLabel.setText(student.getName());
        setPickedRoom(student.getCurrentRoom());
        for(int i = 0; i < 5; i++) {
            if (i < student.getItems().size()) {
                switch (i) {
                    case 0:

                        InvItemButton1.setEnabled(true);
                        InvItemButton1.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                        if (Boolean.TRUE.equals(student.getItems().get(i).getActive()))
                            InvItemButton1.setBackground(Color.RED);
                        else
                            InvItemButton1.setBackground(Color.white);
                        break;
                    case 1:

                        InvItemButton2.setEnabled(true);
                        InvItemButton2.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                        if (Boolean.TRUE.equals(student.getItems().get(i).getActive()))
                            InvItemButton2.setBackground(Color.RED);
                        else
                            InvItemButton2.setBackground(Color.white);
                        break;
                    case 2:

                        InvItemButton3.setEnabled(true);
                        InvItemButton3.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                        if (Boolean.TRUE.equals(student.getItems().get(i).getActive()))
                            InvItemButton3.setBackground(Color.RED);
                        else
                            InvItemButton3.setBackground(Color.white);
                        break;
                    case 3:

                        InvItemButton4.setEnabled(true);
                        InvItemButton4.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                        if (Boolean.TRUE.equals(student.getItems().get(i).getActive()))
                            InvItemButton4.setBackground(Color.RED);
                        else
                            InvItemButton4.setBackground(Color.white);
                        break;
                    case 4:
                        InvItemButton5.setEnabled(true);
                        InvItemButton5.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                        if (Boolean.TRUE.equals(student.getItems().get(i).getActive()))
                            InvItemButton5.setBackground(Color.RED);
                        else
                            InvItemButton5.setBackground(Color.white);
                        break;
                }
            } else {
                switch (i) {
                    case 0:
                        InvItemButton1.setIcon(null);
                        InvItemButton1.setBackground(Color.white);
                        InvItemButton1.setEnabled(false);
                        break;
                    case 1:
                        InvItemButton2.setIcon(null);
                        InvItemButton2.setBackground(Color.white);
                        InvItemButton2.setEnabled(false);
                        break;
                    case 2:
                        InvItemButton3.setIcon(null);
                        InvItemButton3.setBackground(Color.white);
                        InvItemButton3.setEnabled(false);
                        break;
                    case 3:
                        InvItemButton4.setIcon(null);
                        InvItemButton4.setBackground(Color.white);
                        InvItemButton4.setEnabled(false);
                        break;
                    case 4:
                        InvItemButton5.setIcon(null);
                        InvItemButton5.setBackground(Color.white);
                        InvItemButton5.setEnabled(false);
                        break;
                }
            }
            graph.CurrStudentChanged(currentStudent);
        }
        
        //MAYBE SZOBAPICKER GOMBOK BEÁLLÍTÁSA
    }
    
    

    public GraphComponent getGraph() {
        return graph;
    }

    public void DeadPopUp(Person s)
    {
        JOptionPane.showMessageDialog(null, s.getName() + " meghalt!");
    }

    public void StunnedPopUp(Person s)
    {
        JOptionPane.showMessageDialog(null, s.getName() + " megbénult!");
    }

    public void GameEndPopUp()
    {
        
    }
    
    public void updateRoundCount(int i)
    {
        RoundNumberLabel.setText(Integer.toString(i));
    }

    public void setPickedRoom(Room r)
    {
        pickedRoom = r;
        RoomNameLabel.setText(r.getName());
        RoomItemsPanel.setLayout(new FlowLayout());
        RoomItemsPanel.removeAll();
        RoomPeoplePanel.setLayout(new FlowLayout());
        RoomPeoplePanel.removeAll();
        for(int i = 0; i < r.getItems().size(); i++){
            JButton roomItemButton = new JButton(r.getItems().get(i).getName());
            roomItemButton.addActionListener(new roomItemButtonListener(i));
            RoomItemsPanel.add(roomItemButton);
        }
        for(int i = 0; i < r.getPeople().size(); i++){
            JLabel roomPersonLabel = new JLabel(r.getPeople().get(i).getName());
            RoomPeoplePanel.add(roomPersonLabel);
        }
        if(currentStudent.getCurrentRoom().movePossibilities().contains(r)){
            MoveButton.setEnabled(true);
        }else if (currentStudent.getCurrentRoom() == r || (!currentStudent.getCurrentRoom().movePossibilities().contains(r))){
            MoveButton.setEnabled(false);
        }
    }
    
    
    
    protected class inventoryButtonListener implements ActionListener {

        int invButtonIndex;

        public inventoryButtonListener(int itemIndex){
            invButtonIndex = itemIndex;
        }

        public void actionPerformed(ActionEvent e) {
            if(currentStudent.getItems().size() >= invButtonIndex){
                pickedInventoryItemIndex = invButtonIndex;
                
                UseButton.setEnabled(true);
                PutDownButton.setEnabled(true);
            }
        }
    }

    protected class roomItemButtonListener implements ActionListener {

        int roomButtonIndex;

        public roomItemButtonListener(int itemIndex){
            roomButtonIndex = itemIndex;
        }

        public void actionPerformed(ActionEvent e) {
            
            JButton formerButton = (JButton) RoomItemsPanel.getComponent(pickedRoomItemIndex);
            formerButton.setBackground(Color.white);
            
            JButton pressedbutton = (JButton) e.getSource();
            pressedbutton.setBackground(Color.green);
            pickedRoomItemIndex = roomButtonIndex;
            
            PickUpButton.setEnabled(true);
        }
    }


    ///GOMBOK ACTION LISTENERJEI///

    protected class pickUpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedRoomItemIndex > -1) {
                gameManager.pickUpItem(currentStudent, pickedRoomItemIndex);
            }
        }
    }

    protected class putDownButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedInventoryItemIndex > -1) {
                gameManager.putDownItem(currentStudent, pickedInventoryItemIndex);
            }
        }
    }

    protected class useButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedInventoryItemIndex > -1) {
                gameManager.useItem(currentStudent,pickedInventoryItemIndex );
            }
        }
    }

    protected class moveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedRoom != null && pickedRoom != currentStudent.getCurrentRoom()){
                
                graph.studentMoved(currentStudent.getCurrentRoom(), pickedRoom);
                
                UseButton.setEnabled(false);
                PutDownButton.setEnabled(false);
                PickUpButton.setEnabled(false);
                MoveButton.setEnabled(false);
                
                gameManager.movePlayer(currentStudent, pickedRoom);
                
                
            }
        }
    }

    protected class nextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            UseButton.setEnabled(false);
            PutDownButton.setEnabled(false);
            PickUpButton.setEnabled(false);
            MoveButton.setEnabled(false);
            
            gameManager.next();
        }
    }
}
