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

public class GraphComponent{
    
    protected Graph graph;

    String style = "ui.class";
    
    GameManager gm;
    
    DefaultView view;

    File cssPath = new File("src/main/java/Grafikus/graphStyle.css");
    
    StringBuilder css = new StringBuilder();
    
    public GraphComponent() throws FileNotFoundException {
        graph = new SingleGraph("GameGraph");
        
        Scanner sc = new Scanner(cssPath);
        while(sc.hasNextLine()) {
            css.append(sc.nextLine());
        }
        sc.close();
        
        graph.setAttribute("ui.stylesheet", css.toString());
    }
    
    public void setGameManager(GameManager gm){
        this.gm = gm;
    }

    public ViewPanel getViewPanel(){
        Viewer v = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        ViewPanel viewPanel = (ViewPanel) v.addDefaultView(false);
        v.enableAutoLayout();
        view = (DefaultView) v.getDefaultView();
        
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        
        view.enableMouseOptions();
        
        return view;
    }
    
    
    protected Graph getGraph() {
        return graph;
    }
    
    public void CurrStudentChanged(Student NEW){
        ArrayList<Room> allNewEdges = new ArrayList<>(NEW.getCurrentRoom().getOutgoingDoors());
        allNewEdges.addAll(NEW.getCurrentRoom().getIncomingDoors());

        Room newRoom = NEW.getCurrentRoom();
            
        for(int i = 0; i < graph.getEdgeCount(); i++) {
            Edge e = graph.getEdge(i);
            Room relevantRoom = null;
            if( allNewEdges.contains(e.getNode0().getAttribute("room")) && e.getNode1().getAttribute("room") == newRoom){
                    relevantRoom = (Room)e.getNode0().getAttribute("room");
            }
            if( allNewEdges.contains(e.getNode1().getAttribute("room")) && e.getNode0().getAttribute("room") == newRoom){
                    relevantRoom = (Room)e.getNode1().getAttribute("room");
            }
            if(relevantRoom != null){
                if(newRoom.getOutgoingDoors().contains(relevantRoom) && newRoom.getIncomingDoors().contains(relevantRoom)){
                    e.removeAttribute(style);
                    e.setAttribute(style, "two_way");
                }
                else if(newRoom.getIncomingDoors().contains(relevantRoom)){
                    e.removeAttribute(style);
                    e.setAttribute(style, "going");
                }
                else if(newRoom.getOutgoingDoors().contains(relevantRoom)){
                    e.removeAttribute(style);
                    e.setAttribute(style, "coming");
                }
            }
            else{
                e.removeAttribute(style);
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
            if(gm.labyrinth.getStudents().contains(s)) {
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
    }
    
    public void StudentDied(Room r){
        Node n = graph.getNode(r.getName());
        if(r.getGas()){
            n.removeAttribute(style);
            n.setAttribute(style, "node.gas");
        }
        else{
            n.removeAttribute(style);
            n.setAttribute(style, "node.default");
        }
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
    }

}
