package Grafikus;

import Modell.CursedRoom;
import Modell.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.swing.SwingGraphRenderer;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.awt.font.GraphicAttribute;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GraphComponent{
    
    protected Graph graph;
    
    GameManager gm;
    
    Viewer v;
    
    private boolean displayable = false;
    
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
        
        graph.setAttribute("layout.weight", 3);
        //graph.setAttribute("layout.quality", 4);
    }
    
    public void setGameManager(GameManager gm){
        this.gm = gm;
    }

    public Component getViewPanel() {
        
        v = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        v.removeView("view1");
        v.addView("view1", new SwingGraphRenderer(), false);
        v.enableAutoLayout();
        
        graph.setAttribute("ui.antialias");
        //graph.setAttribute("ui.quality");
        
        return (Component) v.getView("view1");
    }
    
    
    
    public Graph getBaseGraph() {
        return graph;
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
                if(newRoom.movePossibilities().contains(relevantRoom) && newRoom.getIncomingDoors().contains(relevantRoom)){
                    e.setAttribute("ui.class", "two_way");
                }
                else if(newRoom.getIncomingDoors().contains(relevantRoom)){
                    e.setAttribute("ui.class", "coming");
                }
                else if(newRoom.movePossibilities().contains(relevantRoom)){
                    e.setAttribute("ui.class", "going");
                }
            } else if (relevantRoom == null) {
                e.setAttribute("ui.class", "default");
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
    
    public void addNode(Room r){
        Node n = graph.addNode(r.getName());
        n.setAttribute("ui.label", r.getName());
        n.setAttribute("room", r);
        if(r.getGas()){
            n.setAttribute("ui.class", "gas");
        }
        else{
            n.setAttribute("ui.class", "default");
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
        e.setAttribute("ui.class", "default");
        e.setAttribute("layout.weight", 1.1);
    }
    
    public void refreshNodes(){
        for(Node n : graph){
            Room r = (Room) n.getAttribute("room");
            Boolean hasStudent = false;
            for(Person s : r.getPeople()) {
                if (gm.labyrinth.getStudents().contains(s)) {
                    hasStudent = true;
                    break;
                }
            }
            if(r.getGas() && hasStudent){
                n.setAttribute("ui.class", "student_gas");
            }
            else if(hasStudent){
                n.setAttribute("ui.class", "student");
            }
            else if(r.getGas()){
                n.setAttribute("ui.class", "gas");
            }
            else{
                n.setAttribute("ui.class", "default");
            }
        }
    }
    
}
