package Grafikus;

import Modell.*;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing.SwingGraphRenderer;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
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
    
    private JButton[] InvItemButtons = {InvItemButton1, InvItemButton2, InvItemButton3, InvItemButton4, InvItemButton5};
    
    
    private JLabel RoomNameLabel;
    
    private JPanel RoomItemsPanel;
    private JPanel RoomPeoplePanel;
    
    private JLabel RoundNumberLabel;
    
    String style = "ui.class";
    
    DefaultView viewPanel;
    
    protected GraphComponent graphComponent;
    
    public GraphComponent getGraphComponent() {
        return graphComponent;
    }

    GameManager gameManager;

    public Student currentStudent;
    int pickedInventoryItemIndex = -1;
    int pickedRoomItemIndex = -1;
    Room pickedRoom;
    
    private void graphReDraw() {
        //graph.removeAttribute("ui.stylesheet");
        //graph.setAttribute("ui.stylesheet", CssStyle);
        //GraphPanel.removeAll();
        
        //GraphPanel.add(graphComponent.getViewPanel(), BorderLayout.CENTER);
        //System.gc();
    }
    
    ViewerPipe toViewer;
    
    String CssStyle;
    
    MenuFrame _takony;
    
    
    private void createUIComponents() {
    }
    
    public GuiManager(List<String> playerNames,MenuFrame takony) throws FileNotFoundException {
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
        
        //System.setProperty("org.graphstream.ui", "swing");
        
        GraphPanel.add(graphComponent.getViewPanel(), BorderLayout.CENTER);
        //v.addView("view1", new SwingGraphRenderer());
        //oViewer.addAttributeSink(graph);
        //toViewer.addViewerListener(this);
        //toViewer.pump();
        
        //graph.addSink(toViewer);
        
        
        //toViewer.pump();
        
        //viewPanel = (DefaultView) v.addView("baseView", new SwingGraphRenderer(), false);
        //viewPanel = new DefaultView(v, "view1", new SwingGraphRenderer());
        
        //viewPanel.setMouseManager(new GraphMouseListener());
        //viewPanel = (DefaultView) v.getDefaultView();
        //graph.display();
        
        BasePanel.setMinimumSize(new Dimension(800, 600));
        GraphPanel.setMinimumSize(new Dimension(800, 400));

        ActualPlayerPanel.setMinimumSize(new Dimension(350,70));
        RoomDetailPanel.setMinimumSize(new Dimension(350,70));
        RoundPanel.setMaximumSize(new Dimension(100,70));
        
        this.add(BasePanel);
        this.setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    protected void reloadInventory(){
        Student student = currentStudent;
        for(int i = 0; i < 5; i++) {
            if (i < student.getItems().size()) {
                InvItemButtons[i].setEnabled(true);
                InvItemButtons[i].setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                if (Boolean.TRUE.equals(student.getItems().get(i).canBeused())){
                    InvItemButtons[i].setBackground(Color.RED);
                }else{
                    InvItemButtons[i].setBackground(Color.white);
                }
            } else {
                InvItemButtons[i].setIcon(null);
                InvItemButtons[i].setBackground(Color.BLACK);
                InvItemButtons[i].setEnabled(false);
            }
        }
    }
    
    protected void reloadRoomItems(){
        Room room = pickedRoom;
        RoomItemsPanel.setLayout(new FlowLayout());
        RoomItemsPanel.removeAll();
        for(int i = 0; i < room.getItems().size(); i++){
            JButton roomItemButton = new JButton();
            roomItemButton.setIcon(new ImageIcon(String.valueOf(room.getItems().get(i).getPath())));
            roomItemButton.addActionListener(new roomItemButtonListener(i));
            RoomItemsPanel.add(roomItemButton);
            if(room != currentStudent.getCurrentRoom()){
                roomItemButton.setEnabled(false);
            }
        }
        RoomItemsPanel.repaint();
    }
    
    protected void reloadNeighboursButton()
    {
        RoomButtonPanel.setLayout(new FlowLayout());
        RoomButtonPanel.removeAll();
        
        Set<Room> neighbours = new HashSet<>();
        neighbours.add(currentStudent.getCurrentRoom());
        neighbours.addAll(currentStudent.getCurrentRoom().getOutgoingDoors());
        neighbours.addAll(currentStudent.getCurrentRoom().getIncomingDoors());
        
        for (Room r : neighbours) {
            JButton roomButton = new JButton(r.getName());
            roomButton.addActionListener(new NeighbourButtonListener(r));
            RoomButtonPanel.add(roomButton);
        }
        
        RoomButtonPanel.repaint();
    }
    
    public void setCurrentStudent(Student student)
    {
        currentStudent = student;
        PlayerNameLabel.setText(student.getName());
        setPickedRoom(student.getCurrentRoom());
        
        reloadInventory();
        
        reloadNeighboursButton();
        
        graphComponent.CurrStudentChanged(student);
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
        this.setVisible(false);

        ImageIcon icon = null;
        if(gameManager.labyrinth.getStudents().isEmpty()){
            icon = new ImageIcon("game_over.png"); // Kép elérési útjának megadása
        }
        else{
            icon = new ImageIcon("victory.png"); // Kép elérési útjának megadása
        }
        JOptionPane.showMessageDialog(null, null ,"GAME END", JOptionPane.INFORMATION_MESSAGE, icon);
        _takony.setVisible(true);
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
        
        reloadRoomItems();
        
        for(int i = 0; i < r.getPeople().size(); i++){
            JLabel roomPersonLabel = new JLabel(r.getPeople().get(i).getName());
            RoomPeoplePanel.add(roomPersonLabel);
        }
        if(currentStudent.getCurrentRoom().movePossibilities().contains(r)){
            MoveButton.setEnabled(true);
        }else if (currentStudent.getCurrentRoom() == r || (!currentStudent.getCurrentRoom().movePossibilities().contains(r))){
            MoveButton.setEnabled(false);
        }
        RoomDetailPanel.repaint();
        
    }



    protected class inventoryButtonListener implements ActionListener {

        int invButtonIndex;

        public inventoryButtonListener(int itemIndex){
            invButtonIndex = itemIndex;
        }

        public void actionPerformed(ActionEvent e) {
            if(currentStudent.getItems().size() > invButtonIndex){
                pickedInventoryItemIndex = invButtonIndex;
                if(currentStudent.getItems().get(pickedInventoryItemIndex).canBeused()){
                    UseButton.setEnabled(true);
                }
                else{
                    UseButton.setEnabled(false);
                }
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

            JButton pressedbutton = (JButton) e.getSource();
            pressedbutton.setBackground(Color.green);
            pickedRoomItemIndex = roomButtonIndex;
            if(currentStudent.getCurrentRoom().getItems().get(pickedRoomItemIndex).canBePickedUp()){
                PickUpButton.setEnabled(true);
            }else{
                PickUpButton.setEnabled(false);
            }
        }
    }
    
    protected class NeighbourButtonListener implements ActionListener{

        Room connectedRoom;
        
        public NeighbourButtonListener(Room room){
            connectedRoom = room;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            setPickedRoom(connectedRoom);
        }
    }


    ///GOMBOK ACTION LISTENERJEI///

    protected class pickUpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedRoomItemIndex > -1 && currentStudent.getItems().size() < 5) {
                gameManager.pickUpItem(currentStudent, pickedRoomItemIndex);
                reloadInventory();
                reloadRoomItems();
                pickedRoomItemIndex = -1;
                PickUpButton.setEnabled(false);
                if(gameManager.labyrinth.isGame_End()) {
                    GameEndPopUp();
                }
            }
        }
    }

    protected class putDownButtonListener implements ActionListener {
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

    protected class useButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedInventoryItemIndex > -1) {
                gameManager.useItem(currentStudent,pickedInventoryItemIndex );
                reloadInventory();
                reloadRoomItems();
                pickedInventoryItemIndex = -1; 
                UseButton.setEnabled(false);
                PutDownButton.setEnabled(false);
            }
        }
    }

    protected class moveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedRoom != null && pickedRoom != currentStudent.getCurrentRoom()){
                
                gameManager.movePlayer(currentStudent, pickedRoom);
                
                UseButton.setEnabled(false);
                PutDownButton.setEnabled(false);
                PickUpButton.setEnabled(false);
                MoveButton.setEnabled(false);
                
                graphComponent.refreshNodes();
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

            graphComponent.CurrStudentChanged(currentStudent);
            graphComponent.refreshNodes();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
/*
    public void CurrStudentChanged(Student NEW){
        ArrayList<Room> allNewEdges = new ArrayList<>(NEW.getCurrentRoom().getOutgoingDoors());
        allNewEdges.addAll(NEW.getCurrentRoom().getIncomingDoors());

        Room newRoom = NEW.getCurrentRoom();

        for(int i = 0; i < graph.getEdgeCount(); i++) {
            Edge e = graph.getEdge(i);
            Room relevantRoom = null;
            if(Objects.equals(((Room) e.getNode1().getAttribute("room")).getName(), newRoom.getName())){
                relevantRoom = (Room)e.getNode0().getAttribute("room");
            }
            else if(Objects.equals(((Room) e.getNode0().getAttribute("room")).getName(), newRoom.getName())){
                relevantRoom = (Room)e.getNode1().getAttribute("room");
            }

            if(relevantRoom != null){
                if(newRoom.getOutgoingDoors().contains(relevantRoom) && newRoom.getIncomingDoors().contains(relevantRoom)){
                    e.setAttribute(style, "two_way");
                }
                else if(newRoom.getIncomingDoors().contains(relevantRoom)){
                    e.setAttribute(style, "coming");
                }
                else if(newRoom.getOutgoingDoors().contains(relevantRoom)){
                    e.setAttribute(style, "going");
                }
            } else if (relevantRoom == null) {
                e.setAttribute(style, "basic");
            }
        }
    }

    public void CurseHappened(CursedRoom cr){
        Node n = graph.getNode(cr.getName());
        ArrayList<Edge> edges = new ArrayList<>();

        for (Edge edge : n) {
            edges.add(edge);
        }
        for (Edge edge : edges) {
            graph.removeEdge(edge);
        }
        cr.getOutgoingDoors().forEach(r -> addEdge(cr, r));
        cr.getIncomingDoors().forEach(r -> addEdge(r, cr));

    }

    public void RoomSplit(Room newRoom){

        newRoom.getOutgoingDoors().forEach(r -> addEdge(newRoom, r));
        newRoom.getIncomingDoors().forEach(r -> addEdge(r, newRoom));

    }


    public void RoomMerged(Room stayingRoom){

        stayingRoom.getOutgoingDoors().forEach(r -> addEdge(stayingRoom, r));
        stayingRoom.getIncomingDoors().forEach(r -> addEdge(r, stayingRoom));

    }

    public void RoomGasUpdate(Room room){
        boolean hasStudent = false;
        for(Person s : room.getPeople()){
            if(gameManager.labyrinth.getStudents().contains(s)) {
                hasStudent = true;
                break;
            }
        }
        Node n = graph.getNode(room.getName());
        //gázos lett
        if(room.getGas()){
            if(hasStudent){
                n.setAttribute(style, "student_gas");
            }
            else{
                n.setAttribute(style, "gas");
            }
        }
        //gáztalan lett
        else{
            if(hasStudent){
                n.setAttribute(style, "student");
            }
            else{
                n.setAttribute(style, "basic");
            }
        }
        graphReDraw();
    }

    public void StudentDied(Room r){
        Node n = graph.getNode(r.getName());
        boolean stillhasStudent = false;
        for(Person s : r.getPeople()){
            if(gameManager.labyrinth.getStudents().contains(s)){
                stillhasStudent = true;
                break;
            }
        }
        if(r.getGas() && stillhasStudent){
            n.setAttribute(style, "student_gas");
        }
        else if(r.getGas() && !stillhasStudent){
            n.setAttribute(style, "gas");
        }
        else if(!r.getGas() && stillhasStudent){
            n.setAttribute(style, "student");
        }
        else if(!r.getGas() && !stillhasStudent){
            n.setAttribute(style, "basic");
        }
        graphReDraw();
    }

    public void addNode(Room r){
        Node n = graph.addNode(r.getName());
        n.setAttribute("ui.label", r.getName());
        n.setAttribute("room", r);
        if(r.getGas()){
            n.setAttribute(style, "gas");
        }
        else{
            n.setAttribute(style, "basic");
        }
    }

    public void removeNode(Room r){
        Node n = graph.getNode(r.getName());
        graph.removeNode(n.getId());
    }

    public void addEdge(Room r1, Room r2){
        Node n1 = graph.getNode(r1.getName());
        Node n2 = graph.getNode(r2.getName());
        for(Edge e : n1){
            if(e.getNode0().equals(n2) || e.getNode1().equals(n2)){
                return;
            }
        }
        Edge e = graph.addEdge(r1.getName() + r2.getName(), n1, n2);
        e.setAttribute(style, "basic");
        e.setAttribute("layout.weight", 1.1);
    }

    public void addStudent(Student s){
        Node n = graph.getNode(s.getCurrentRoom().getName());
        if(s.getCurrentRoom().getGas()){
            n.removeAttribute(style);
            n.setAttribute(style, "student_gas");
        }
        else{
            n.removeAttribute(style);
            n.setAttribute(style, "student");
        }
    }

    public void studentMoved(Room oldR, Room newR){
        Node oldN = graph.getNode(oldR.getName());
        Node newN = graph.getNode(newR.getName());
        if(oldR.getGas()){
            oldN.removeAttribute(style);
            oldN.setAttribute(style, "gas");
        }
        else{
            oldN.removeAttribute(style);
            oldN.setAttribute(style, "basic");
        }
        if(newR.getGas()){
            newN.removeAttribute(style);
            newN.setAttribute(style, "student_gas");
        }
        else{
            newN.removeAttribute(style);
            newN.setAttribute(style, "student");
        }
        graphReDraw();
    }
*/
}
