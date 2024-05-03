package Grafikus;

import Modell.Room;
import Modell.Student;
import org.graphstream.algorithm.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Element;
import org.graphstream.stream.ProxyPipe;
import org.graphstream.stream.Source;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.graphicGraph.StyleGroup;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.LayoutRunner;
import org.graphstream.ui.layout.Layouts;
import org.graphstream.algorithm.*;
import org.graphstream.algorithm.*;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.view.GraphRenderer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;

public class CustomNodeGraphExample {

    public static class NodeData {
        
        public Object Room;
        
        private String label;

        public NodeData(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
    
    private static String css = 
            "node.default { fill-color: gray; size: 45px; text-size: 20;} " +
            "node.gas { fill-color: rgb(0,100,0); size: 45px; text-size: 20;} " + 
            "node.student { fill-color: grey; stroke-color: green; stroke-mode: plain; stroke-width: 5px; size: 45px; text-size: 20;}" +
            "node.student_gas { fill-color: rgb(0,100,0); stroke-color: green; stroke-mode: plain; stroke-width: 5px; size: 45px; text-size: 20;}" +
            
            "edge.default { fill-color: black; size: 5px;} " +
            "edge.coming { fill-color: red; size: 5px;}" +
            "edge.going { fill-color: blue; size: 5px;}" +
            "edge.two_way { fill-color: purple; size: 5px;}";


    public static void main(String[] args) throws InterruptedException {
        // Új gráf létrehozása
        Graph graph = new SingleGraph("CustomNodeGraph");

        // Csúcsok hozzáadása a gráfhoz
        
        graph.setAttribute("ui.stylesheet", css);

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
        

        
       System.setProperty("org.graphstream.ui", "swing");

        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        // Gráf elrendezése

        //Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        //viewer.enableAutoLayout(); // Itt engedélyezd az autolayoutot
        
        
        Viewer v = graph.display();
        
        v.enableAutoLayout();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultView view = (DefaultView) v.getDefaultView();
        view.enableMouseOptions();
        
        JPanel layout = new JPanel(new BorderLayout());
        JPanel top = new JPanel(new GridLayout(2,2));
        JPanel bottom = new JPanel(new GridLayout(2,1));
        
        top.add(new JLabel("Player Name"));
        top.add(new JPanel(new GridLayout(1,5)),0,1);
        JTextField playerName = new JTextField("Player Name");
        top.add(playerName);
        
        
        view.setMaximumSize(new Dimension(800, 400));
        layout.add(top, BorderLayout.NORTH);
        frame.add(view, BorderLayout.CENTER);
        layout.updateUI();
        frame.add(layout);
        frame.setSize(800, 800);
        frame.setVisible(true);
        
    }
    
}