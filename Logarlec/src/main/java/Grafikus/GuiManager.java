package Grafikus;

import Modell.*;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.view.ViewerPipe;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * A játék grafikus felületét megvalósító osztály
 */
public class GuiManager extends JFrame {

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

    private JButton[] InvItemButtons = {InvItemButton1, InvItemButton2, InvItemButton3, InvItemButton4, InvItemButton5};
    private ArrayList<JButton> roomItemButtons;
    private ArrayList<JButton> neighbourButtons;

    private JLabel RoomNameLabel;

    private JPanel RoomItemsPanel;
    private JPanel RoomPeoplePanel;

    private JLabel RoundNumberLabel;

    protected GraphComponent graphComponent;

    public GraphComponent getGraphComponent() {
        return graphComponent;
    }

    GameManager gameManager;

    public Student currentStudent;
    int pickedInventoryItemIndex = -1;
    int pickedRoomItemIndex = -1;
    Room pickedRoom;

    MenuFrame _takony;


    /**
     * A GUI létrehozása
     */
    private void createUIComponents() {
    }

    /**
     * A GUI inicializálása és indítása
     */
    public GuiManager(List<String> playerNames, MenuFrame takony) throws FileNotFoundException {
        _takony = takony;
        PickUpButton.addActionListener(new pickUpButtonListener());
        PickUpButton.setEnabled(false);
        PutDownButton.addActionListener(new putDownButtonListener());
        PutDownButton.setEnabled(false);
        UseButton.addActionListener(new useButtonListener());
        UseButton.setEnabled(false);
        MoveButton.addActionListener(new moveButtonListener());
        MoveButton.setEnabled(false);
        NextButton.addActionListener(new nextButtonListener());

        InvItemButton1.addActionListener(new inventoryButtonListener(0));
        InvItemButton2.addActionListener(new inventoryButtonListener(1));
        InvItemButton3.addActionListener(new inventoryButtonListener(2));
        InvItemButton4.addActionListener(new inventoryButtonListener(3));
        InvItemButton5.addActionListener(new inventoryButtonListener(4));

        LineBorder border = new LineBorder(Color.BLACK, 2);
        ActualPlayerPanel.setBorder(border);
        RoomDetailPanel.setBorder(border);
        RoundPanel.setBorder(border);


        graphComponent = new GraphComponent();

        gameManager = new GameManager(this, playerNames);

        GraphPanel.setLayout(new BorderLayout());
        graphComponent.setGameManager(gameManager);
        graphComponent.refreshNodes();
        GraphPanel.add(graphComponent.getViewPanel(), BorderLayout.CENTER);

        BasePanel.setMinimumSize(new Dimension(1000, 600));
        GraphPanel.setMinimumSize(new Dimension(1000, 400));

        ActualPlayerPanel.setMinimumSize(new Dimension(450, 90));
        RoomDetailPanel.setMinimumSize(new Dimension(350, 90));
        RoundPanel.setMaximumSize(new Dimension(100, 90));

        this.add(BasePanel);
        this.setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Az inventory újratöltése
     */
    protected void reloadInventory() {
        Student student = currentStudent;
        for (int i = 0; i < 5; i++) {
            if (i < student.getItems().size()) {
                InvItemButtons[i].setEnabled(true);
                InvItemButtons[i].setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                InvItemButtons[i].setBackground(Color.WHITE);
            } else {
                InvItemButtons[i].setIcon(null);
                InvItemButtons[i].setBackground(Color.BLACK);
                InvItemButtons[i].setEnabled(false);
            }
        }
    }

    /**
     * A szobában található tárgyak újratöltése
     */
    protected void reloadRoomItems() {
        Room room = pickedRoom;
        RoomItemsPanel.setLayout(new FlowLayout());
        RoomItemsPanel.removeAll();
        roomItemButtons = new ArrayList<>();
        for (int i = 0; i < room.getItems().size(); i++) {
            JButton roomItemButton = new JButton();
            roomItemButton.setIcon(new ImageIcon(String.valueOf(room.getItems().get(i).getPath())));
            roomItemButton.addActionListener(new roomItemButtonListener(i));
            RoomItemsPanel.add(roomItemButton);
            if (room != currentStudent.getCurrentRoom() || !room.getItems().get(i).canBePickedUp()) {
                roomItemButton.setEnabled(false);
            }
            roomItemButton.setBackground(Color.WHITE);
            roomItemButtons.add(roomItemButton);
        }
        RoomItemsPanel.repaint();
    }

    /**
     * A szomszédos szobák gombjainak újratöltése
     */
    protected void reloadNeighboursButton() {
        RoomButtonPanel.setLayout(new FlowLayout());
        RoomButtonPanel.removeAll();
        
        neighbourButtons = new ArrayList<>();
        
        Set<Room> neighbours = new HashSet<>();
        neighbours.add(currentStudent.getCurrentRoom());
        neighbours.addAll(currentStudent.getCurrentRoom().getOutgoingDoors());
        neighbours.addAll(currentStudent.getCurrentRoom().getIncomingDoors());

        for (Room r : neighbours) {
            JButton roomButton = new JButton(r.getName());
            roomButton.addActionListener(new NeighbourButtonListener(r));
            RoomButtonPanel.add(roomButton);
            neighbourButtons.add(roomButton);
        }

        RoomButtonPanel.repaint();
    }

    /**
     * A szobában található emberek újratöltése
     */
    public void setCurrentStudent(Student student) {
        currentStudent = student;
        PlayerNameLabel.setText(student.getName());
        setPickedRoom(student.getCurrentRoom());

        reloadInventory();

        reloadNeighboursButton();

        graphComponent.CurrStudentChanged(student);
    }

    /**
     * A körök számának frissítése
     */
    public void DeadPopUp(Person s) {
        JOptionPane.showMessageDialog(null, s.getName() + " meghalt!");
    }
    
    /**
     * A körök számának frissítése
     */
    public void StunnedPopUp(Person s) {
        JOptionPane.showMessageDialog(null, s.getName() + " megbénult!");
    }

    /**
     * A körök számának frissítése
     */
    public void GameEndPopUp() {
        this.setVisible(false);

        ImageIcon icon = null;
        if (gameManager.labyrinth.getStudents().isEmpty()) {
            icon = new ImageIcon("game_over.png"); // Kép elérési útjának megadása
        } else {
            icon = new ImageIcon("victory.png"); // Kép elérési útjának megadása
        }
        JOptionPane.showMessageDialog(null, null, "GAME END", JOptionPane.INFORMATION_MESSAGE, icon);
        _takony.setVisible(true);
    }
    
    /**
     * A körök számának frissítése
     */                                                                                                                                         
    public void updateRoundCount(int i) {
        RoundNumberLabel.setText(Integer.toString(i));
    }                                                                       
    
    /**
     * A kiválasztott szoba beállítása
     */
    public void setPickedRoom(Room r) {
        pickedRoom = r;
        RoomNameLabel.setText(r.getName());

        RoomItemsPanel.removeAll();
        RoomItemsPanel.setLayout(new FlowLayout());
        reloadRoomItems();

        RoomPeoplePanel.removeAll();
        RoomPeoplePanel.setLayout(new FlowLayout());

        for (int i = 0; i < r.getPeople().size(); i++) {
            JLabel roomPersonLabel = new JLabel(r.getPeople().get(i).getName());
            RoomPeoplePanel.add(roomPersonLabel);
        }
        RoomPeoplePanel.repaint();
        
        
        if (currentStudent.getCurrentRoom().movePossibilities().contains(r)) {
            MoveButton.setEnabled(true);
        } else if (currentStudent.getCurrentRoom() == r || (!currentStudent.getCurrentRoom().movePossibilities().contains(r))) {
            MoveButton.setEnabled(false);
        }
        

    }
    
    /**
     * A szobában található tárgyak újratöltése
     */
    protected class inventoryButtonListener implements ActionListener {

        int invButtonIndex;

        /**
         * A kiválasztott tárgy beállítása a konstruktorban
         */
        public inventoryButtonListener(int itemIndex) {
            invButtonIndex = itemIndex;
        }

        /**
         * A kiválasztott tárgy beállítása
         */
        public void actionPerformed(ActionEvent e) {
            if (currentStudent.getItems().size() > invButtonIndex) {
                pickedInventoryItemIndex = invButtonIndex;
                if (currentStudent.getItems().get(pickedInventoryItemIndex).canBeused()) {
                    UseButton.setEnabled(true);
                } else {
                    UseButton.setEnabled(false);
                }
                PutDownButton.setEnabled(true);
                for (int i = 0; i < currentStudent.getItems().size(); i++) {
                    if (i != invButtonIndex) {
                        InvItemButtons[i].setBackground(Color.WHITE);
                    } else {
                        InvItemButtons[i].setBackground(Color.GREEN);
                    }
                }
            }
        }
    }

    /**
     * A kiválasztott tárgy beállítása
     */
    protected class roomItemButtonListener implements ActionListener {

        int roomButtonIndex;

        /**
         * A kiválasztott tárgy beállítása a kosntruktorban
         */
        public roomItemButtonListener(int itemIndex) {
            roomButtonIndex = itemIndex;
        }

        /**
         * A kiválasztott tárgy beállítása
         */
        public void actionPerformed(ActionEvent e) {
            
            JButton pressedbutton = (JButton) e.getSource();
            pressedbutton.setBackground(Color.green);
            pickedRoomItemIndex = roomButtonIndex;
            if (currentStudent.getCurrentRoom().getItems().get(pickedRoomItemIndex).canBePickedUp()) {
                PickUpButton.setEnabled(true);
            } else {
                PickUpButton.setEnabled(false);
            }
            for (JButton b : roomItemButtons) {
                if (b != pressedbutton) {
                    b.setBackground(Color.WHITE);
                }
            }
            
        }
    }

    /**
     * A kiválasztott szoba beállítása
     */
    protected class NeighbourButtonListener implements ActionListener {

        Room connectedRoom;

        /**
         * A hozzá tartozó szoba beállítása konsztruktorban
         */
        public NeighbourButtonListener(Room room) {
            connectedRoom = room;
        }

        /**
         * A kiválasztott szoba beállítása
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(connectedRoom != pickedRoom){
                setPickedRoom(connectedRoom);
            }
            JButton pressed = (JButton) e.getSource();
            for (JButton b : neighbourButtons) {
                if (b != pressed) {
                    b.setBackground(Color.WHITE);
                } else{
                    b.setBackground(Color.GREEN);
                }
            }
        }
    }

    /**
     * A kiválasztott tárgy felvétele
     */
    protected class pickUpButtonListener implements ActionListener {
        /**
         * A kiválasztott tárgy felvétele
         */
        public void actionPerformed(ActionEvent e) {
            if (pickedRoomItemIndex > -1 && currentStudent.getItems().size() < 5) {
                gameManager.pickUpItem(currentStudent, pickedRoomItemIndex);
                reloadInventory();
                reloadRoomItems();
                pickedRoomItemIndex = -1;
                PickUpButton.setEnabled(false);
                if (gameManager.labyrinth.isGame_End()) {
                    GameEndPopUp();
                }
            }
        }
    }

    /**
     * A kiválasztott tárgy letétele
     */
    protected class putDownButtonListener implements ActionListener {
        /**
         * A kiválasztott tárgy letétele
         */
        public void actionPerformed(ActionEvent e) {
            if (pickedInventoryItemIndex > -1) {
                gameManager.putDownItem(currentStudent, pickedInventoryItemIndex);
                pickedInventoryItemIndex = -1;
                PutDownButton.setEnabled(false);
                UseButton.setEnabled(false);
                reloadInventory();
                reloadRoomItems();
            }
        }
    }

    /**
     * A kiválasztott tárgy használata
     */
    protected class useButtonListener implements ActionListener {
        /**
         * A kiválasztott tárgy használata
         */
        public void actionPerformed(ActionEvent e) {
            if (pickedInventoryItemIndex > -1) {
                gameManager.useItem(currentStudent, pickedInventoryItemIndex);
                if (!gameManager.labyrinth.getStudents().contains(currentStudent)) {
                    gameManager.next();
                }
                reloadInventory();
                reloadRoomItems();
                pickedInventoryItemIndex = -1;
                UseButton.setEnabled(false);
                PutDownButton.setEnabled(false);
            }
        }
    }

    /**
     * A játékos mozgatása
     */
    protected class moveButtonListener implements ActionListener {
        /**
         * A játékos mozgatása
         */
        public void actionPerformed(ActionEvent e) {
            if (pickedRoom != null && pickedRoom != currentStudent.getCurrentRoom()) {

                gameManager.movePlayer(currentStudent, pickedRoom);

                UseButton.setEnabled(false);
                PutDownButton.setEnabled(false);
                PickUpButton.setEnabled(false);
                MoveButton.setEnabled(false);

                graphComponent.refreshNodes();
            }
        }
    }

    /**
     * A következő játékosra váltás
     */
    protected class nextButtonListener implements ActionListener {
        /**
         * A következő játékosra váltás
         */
        public void actionPerformed(ActionEvent e) {
            UseButton.setEnabled(false);
            PutDownButton.setEnabled(false);
            PickUpButton.setEnabled(false);
            MoveButton.setEnabled(false);

            gameManager.next();

            graphComponent.CurrStudentChanged(currentStudent);
            graphComponent.refreshNodes();
        }
    }
}