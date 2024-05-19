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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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
    
    String style = "ui.class";
    
    DefaultView viewPanel;
    
    protected GraphComponent graphComponent;
    
    Graph graph;

    Viewer v;

    GameManager gameManager;

    Student currentStudent;
    int pickedInventoryItemIndex = -1;
    int pickedRoomItemIndex = -1;
    Room pickedRoom;
    
    private void graphReDraw() {
        GraphPanel.removeAll();
        v = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        v.addDefaultView(false);
        v.enableAutoLayout();
        GraphPanel.add((Component) v.getDefaultView(), BorderLayout.CENTER);
    }
    ViewerPipe toViewer;
    
    
    private void createUIComponents() {
    }
    
    public GuiManager(List<String> playerNames) throws FileNotFoundException {
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

        graph = new SingleGraph("GameGraph");
        StringBuilder css = new StringBuilder();
        Scanner sc = new Scanner(new File("src/main/java/Grafikus/graphStyle.css"));
        while(sc.hasNextLine()) {
            css.append(sc.nextLine());
        }
        sc.close();
        
        graph.setAttribute("ui.stylesheet", css.toString());
        graph.setAttribute("layout.weight", 3);
        
        gameManager = new GameManager(this, playerNames);
        
        GraphPanel.setLayout(new BorderLayout());

        System.setProperty("org.graphstream.ui", "swing");
        
        v = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        //toViewer = v.newViewerPipe();
        v.enableAutoLayout();
        v.addDefaultView(false);
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
        GraphPanel.add((Component) v.getDefaultView(), BorderLayout.CENTER);
        
        GraphPanel.setMinimumSize(new Dimension(400, 400));
        //graph.display();
        
        add(BasePanel);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        setLocationRelativeTo(null);
        this.pack();
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
                        //InvItemButton1.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                        if (Boolean.TRUE.equals(student.getItems().get(i).getActive()))
                            InvItemButton1.setBackground(Color.RED);
                        else
                            InvItemButton1.setBackground(Color.white);
                        break;
                    case 1:

                        InvItemButton2.setEnabled(true);
                        //InvItemButton2.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                        if (Boolean.TRUE.equals(student.getItems().get(i).getActive()))
                            InvItemButton2.setBackground(Color.RED);
                        else
                            InvItemButton2.setBackground(Color.white);
                        break;
                    case 2:

                        InvItemButton3.setEnabled(true);
                        //InvItemButton3.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                        if (Boolean.TRUE.equals(student.getItems().get(i).getActive()))
                            InvItemButton3.setBackground(Color.RED);
                        else
                            InvItemButton3.setBackground(Color.white);
                        break;
                    case 3:

                        InvItemButton4.setEnabled(true);
                        //InvItemButton4.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
                        if (Boolean.TRUE.equals(student.getItems().get(i).getActive()))
                            InvItemButton4.setBackground(Color.RED);
                        else
                            InvItemButton4.setBackground(Color.white);
                        break;
                    case 4:
                        InvItemButton5.setEnabled(true);
                        //InvItemButton5.setIcon(new ImageIcon(String.valueOf(student.getItems().get(i).getPath())));
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
        }
        CurrStudentChanged(student);
        
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
                
               studentMoved(currentStudent.getCurrentRoom(), pickedRoom);
                
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

            ArrayList<Room> allNewEdges = new ArrayList<>(currentStudent.getCurrentRoom().getOutgoingDoors());
            allNewEdges.addAll(currentStudent.getCurrentRoom().getIncomingDoors());

            Room newRoom = currentStudent.getCurrentRoom();

            for(int i = 0; i < graph.getEdgeCount(); i++) {
                Edge E = graph.getEdge(i);
                Room relevantRoom = null;
                if(Objects.equals(((Room) E.getNode1().getAttribute("room")).getName(), newRoom.getName())){
                    relevantRoom = (Room)E.getNode0().getAttribute("room");
                }
                else if(Objects.equals(((Room) E.getNode0().getAttribute("room")).getName(), newRoom.getName())){
                    relevantRoom = (Room)E.getNode1().getAttribute("room");
                }

                if(relevantRoom != null){
                    if(newRoom.getOutgoingDoors().contains(relevantRoom) && newRoom.getIncomingDoors().contains(relevantRoom)){
                        E.removeAttribute(style);
                        graph.edgeAttributeChanged(graph.getId(),0,E.getId(),style,E.getAttribute(style),"two_way");
                        E.setAttribute(style, "two_way");
                    }
                    else if(newRoom.getIncomingDoors().contains(relevantRoom)){
                        E.removeAttribute(style);
                        graph.edgeAttributeChanged(graph.getId(),0,E.getId(),style,E.getAttribute(style),"coming");
                        E.setAttribute(style, "coming");
                    }
                    else if(newRoom.getOutgoingDoors().contains(relevantRoom)){
                        E.removeAttribute(style);
                        graph.edgeAttributeChanged(graph.getId(),0,E.getId(),style,E.getAttribute(style),"going");
                        E.setAttribute(style, "going");
                    }
                } else if (relevantRoom == null) {
                    E.removeAttribute(style);
                    graph.edgeAttributeChanged(graph.getId(),0,E.getId(),style,E.getAttribute(style),"default");
                    E.setAttribute(style, "default");
                }
            }
            graphReDraw();
        }
    }

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
                e.setAttribute(style, "default");
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
                n.setAttribute(style, "default");
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
            n.setAttribute(style, "default");
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
            n.setAttribute(style, "default");
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
        e.setAttribute(style, "default");
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
            oldN.setAttribute(style, "default");
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
}
