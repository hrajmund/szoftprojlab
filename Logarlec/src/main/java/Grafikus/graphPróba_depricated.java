package Grafikus;

import Modell.Room;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;

import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.*;
import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class graphPróba_depricated {

    public static class NodeData {
        
        public Room Room;
        
        public NodeData(Node node,Room room){
            node.setAttribute("ui.label", room.getName());
            node.setAttribute("Room", room);
        }
        
        
    }
    
    
    

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        // Új gráf létrehozása
        Graph graph = new SingleGraph("CustomNodeGraph");

        // Csúcsok hozzáadása a gráfhoz
        File cssPath = new File("src/main/java/Grafikus/graphStyle.css");
        StringBuilder css = new StringBuilder();
        Scanner sc = new Scanner(cssPath);
        while(sc.hasNextLine()) {
            css.append(sc.nextLine());
        }
        sc.close();
        
        graph.setAttribute("ui.stylesheet", css.toString());

        Node nodeR1 = graph.addNode("R1");
        nodeR1.setAttribute("ui.class", "default");
        nodeR1.setAttribute("ui.label", "R1");
        
        Node nodeR2 = graph.addNode("R2");
        nodeR2.setAttribute("ui.class", "default");
        nodeR2.setAttribute("ui.label", "R2");
        
        Node nodeR3 = graph.addNode("R3");
        nodeR3.setAttribute("ui.class", "default");
        nodeR3.setAttribute("ui.label", "R3");
        
        Node nodeR4 = graph.addNode("R4");
        nodeR4.setAttribute("ui.class", "student");
        nodeR4.setAttribute("ui.label", "R4");
        
        Node nodeR5 = graph.addNode("R5");
        nodeR5.setAttribute("ui.class", "default");
        nodeR5.setAttribute("ui.label", "R5");
        
        Node nodeR6 = graph.addNode("R6");
        nodeR6.setAttribute("ui.class", "gas");
        nodeR6.setAttribute("ui.label", "R6");
        
        Edge edgeR1R2 = graph.addEdge("R1R2", "R1", "R2");
        edgeR1R2.setAttribute("ui.class", "default");
        Edge edgeR4R2 = graph.addEdge("R4R2", "R4", "R2");
        edgeR4R2.setAttribute("ui.class", "going");
        Edge edgeR1R3 = graph.addEdge("R1R3", "R1", "R3");
        edgeR1R3.setAttribute("ui.class", "default");
        Edge edgeR3R4 = graph.addEdge("R3R4", "R3", "R4");
        edgeR3R4.setAttribute("ui.class", "coming");
        Edge edgeR4R5 = graph.addEdge("R4R5", "R4", "R5");
        edgeR4R5.setAttribute("ui.class", "two_way");
        Edge edgeR5R6 = graph.addEdge("R5R6", "R5", "R6");
        edgeR5R6.setAttribute("ui.class", "default");
        
        
        
        

        // Élek hozzáadása
        

        
       //System.setProperty("org.graphstream.ui", "swing");

        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        // Gráf elrendezése

        //Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        //viewer.enableAutoLayout(); // Itt engedélyezd az autolayoutot
        //Viewer v = graph.display();

        Viewer v = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        v.addDefaultView(false);
        
        
        v.enableAutoLayout();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DefaultView view = (DefaultView) v.getDefaultView();
        view.enableMouseOptions();
        
        JPanel basePanel = new JPanel(new BorderLayout());
        JPanel top = new JPanel(new GridLayout(2,2));
        JPanel bottom = new JPanel(new GridLayout(2,1));
        
        top.add(new JLabel("Player Name"));
        top.add(new JPanel(new GridLayout(1,5)),0,1);
        JTextField playerName = new JTextField("Player Name");
        top.add(playerName);
        
        
        view.setMaximumSize(new Dimension(800, 400));
        basePanel.add(top, BorderLayout.NORTH);
        basePanel.add(view, BorderLayout.CENTER);
        basePanel.updateUI();
        frame.add(basePanel);
        frame.setSize(800, 800);
        frame.setVisible(true);
        
        Thread.currentThread().sleep(3000);
        
        graph.addNode("test");
        graph.addEdge("testR4", "test", "R4");
        
        Node testNode2 = graph.addNode("test2");
        testNode2.setAttribute("ui.size", "45px");
        testNode2.setAttribute("ui.color", 0.5);
        
        graph.addEdge("test2R4", "test2", "R4");
        
        Thread.currentThread().sleep(3000);
        testNode2.setAttribute("ui.class", "student_gas");
        
        Thread.currentThread().sleep(3000);
        testNode2.setAttribute("ui.class", "student");
        
        Thread.currentThread().sleep(3000);
        testNode2.setAttribute("ui.class", "gas");
        
        Thread.currentThread().sleep(3000);
        edgeR1R2.setAttribute("ui.class", "going");
        edgeR1R3.setAttribute("ui.class", "going");
        Thread.currentThread().sleep(3000);
        edgeR3R4.setAttribute("ui.class", "coming");
        edgeR4R5.setAttribute("ui.class", "two_way");
        edgeR5R6.setAttribute("ui.class", "going");
    }
    
    
    
}