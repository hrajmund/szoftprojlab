package Grafikus;

import Modell.CursedRoom;
import Modell.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GraphComponent {
    
    protected Graph graph;

    String style = "ui.style";
    
    GameManager gm;

    File cssPath = new File("src/main/java/Grafikus/graphStyle.css");
    
    StringBuilder css = new StringBuilder();
    
    public GraphComponent(){
        Scanner sc = null;
        try {
            sc = new Scanner(cssPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(sc.hasNextLine()) {
            css.append(sc.nextLine());
        }
        sc.close();
        
        graph = new SingleGraph("GameGraph");
    }
    
    public void setGameManager(GameManager gm){
        this.gm = gm;
    }
    
    public DefaultView getView(){
        Viewer v = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        ViewPanel viewPanel = (ViewPanel) v.addDefaultView(false);
        v.enableAutoLayout();
        DefaultView view = (DefaultView) v.getDefaultView();
        view.enableMouseOptions();
        graph.setAttribute("ui.stylesheet", css.toString());
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        return view;
    }
    
    protected Graph getGraph() {
        return graph;
    }
    
    public void CurrStudentChanged(Student NEW){
        Set<Room> allNewEdges = new HashSet<>();
        allNewEdges.addAll(NEW.getCurrentRoom().getOutgoingDoors());
        allNewEdges.addAll(NEW.getCurrentRoom().getIncomingDoors());

        ArrayList<Room> newOutgoing = new ArrayList<>(NEW.getCurrentRoom().getOutgoingDoors());
        ArrayList<Room> newIncoming = new ArrayList<>(NEW.getCurrentRoom().getIncomingDoors());
        

            
        for(int i = 0; i < graph.getEdgeCount(); i++) {
            Edge e = graph.getEdge(i);
            Room relevantRoom = null;
            if( allNewEdges.contains((Room) e.getNode0().getAttribute("room"))){
                relevantRoom = (Room)e.getNode0().getAttribute("room");
            }
            if( allNewEdges.contains((Room) e.getNode1().getAttribute("room"))){
                relevantRoom = (Room)e.getNode1().getAttribute("room");
            }
            if(relevantRoom != null){
                if(newOutgoing.contains(relevantRoom) && newIncoming.contains(relevantRoom)){
                    e.setAttribute(style, "edge.two_way");
                }
                else if(newOutgoing.contains(relevantRoom)){
                    e.setAttribute(style, "edge.going");
                }
                else if(newIncoming.contains(relevantRoom)){
                    e.setAttribute(style, "edge.coming");
                }
            }
            else{
                e.setAttribute(style, "edge.default");
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
    
    public void RoomSplit(Room originalRoom, Room newRoom){
        originalRoom.getOutgoingDoors().forEach(r -> addEdge(originalRoom, r));
        originalRoom.getIncomingDoors().forEach(r -> addEdge(r, originalRoom));
        
        addNode(newRoom);
        newRoom.getOutgoingDoors().forEach(r -> addEdge(newRoom, r));
        newRoom.getIncomingDoors().forEach(r -> addEdge(r, newRoom));
    }
    
    
    public void RoomMerged(Room stayingRoom, Room mergedRoom){
        graph.removeNode(mergedRoom.getName());
        
        stayingRoom.getOutgoingDoors().forEach(r -> addEdge(stayingRoom, r));
        stayingRoom.getIncomingDoors().forEach(r -> addEdge(r, stayingRoom));
    }
    
    public void RoomGasUpdate(Room room){
        boolean hasStudent = false;
        for(Person s : room.getPeople()){
            if(gm.labyrinth.getStudents().contains(s)) {
                hasStudent = true;
                break;
            }
        }
        Node n = graph.getNode(room.getName());
        //gázos lett
        if(room.getGas()){
            if(hasStudent){
                n.setAttribute(style, "node.student_gas");
            }
            else{
                n.setAttribute(style, "node.gas");
            }
        }
        //gáztalan lett
        else{
            if(hasStudent){
                n.setAttribute(style, "node.student");
            }
            else{
                n.setAttribute(style, "node.default");
            }
        }
    }
    
    public void StudentDied(Room r){
        Node n = graph.getNode(r.getName());
        if(r.getGas()){
            n.setAttribute(style, "node.gas");
        }
        else{
            n.setAttribute(style, "node.default");
        }
    }
    
    public void addNode(Room r){
        Node n = graph.addNode(r.getName());
        n.setAttribute("ui.label", r.getName());
        n.setAttribute("room", r);
        if(r.getGas()){
            n.setAttribute(style, "node.gas");
        }
        else{
            n.setAttribute(style, "node.default");
        }
    }
    
    public void addEdge(Room r1, Room r2){
        Node n1 = graph.getNode(r1.getName());
        Node n2 = graph.getNode(r2.getName());
        //Edge e = graph.addEdge(r1.getName() + r2.getName(), n1, n2);
        for(Edge e : n1){
            if(e.getNode0().equals(n2) || e.getNode1().equals(n2)){
                return;
            }
        }
        Edge e = graph.addEdge(r1.getName() + r2.getName(), n1, n2);
    }
    
    public void addStudent(Student s){
        Node n = graph.getNode(s.getCurrentRoom().getName());
        if(s.getCurrentRoom().getGas()){
            n.setAttribute(style, "node.student_gas");
        }
        else{
            n.setAttribute(style, "node.student");
        }
    }
    
    public void studentMoved(Room oldR, Room newR){
        Node oldN = graph.getNode(oldR.getName());
        Node newN = graph.getNode(newR.getName());
        if(oldR.getGas()){
            oldN.setAttribute(style, "node.gas");
        }
        else{
            oldN.setAttribute(style, "node.default");
        }
        if(newR.getGas()){
            newN.setAttribute(style, "node.student_gas");
        }
        else{
            newN.setAttribute(style, "node.student");
        }
    }
    
    
    
    
}
