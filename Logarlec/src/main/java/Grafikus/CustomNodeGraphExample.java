package Grafikus;

import org.graphstream.algorithm.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Element;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.LayoutRunner;
import org.graphstream.ui.layout.Layouts;
import org.graphstream.algorithm.*;
import org.graphstream.algorithm.*;

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

    public static void main(String[] args) {
        // Új gráf létrehozása
        Graph graph = new SingleGraph("CustomNodeGraph");
        
        

        // Csúcsok hozzáadása a gráfhoz
        NodeData nodeDataA = new NodeData("A");
        NodeData nodeDataB = new NodeData("B");
        NodeData nodeDataC = new NodeData("C");
        NodeData nodeDataD = new NodeData("D");
        NodeData nodeDataE = new NodeData("E");
        NodeData nodeDataF = new NodeData("F");

        Node nodeA = graph.addNode("A");
        nodeA.setAttribute("data", nodeDataA);

        Node nodeB = graph.addNode("B");
        nodeB.setAttribute("data", nodeDataB);

        Node nodeC = graph.addNode("C");
        nodeC.setAttribute("data", nodeDataC);
        
        Node nodeD = graph.addNode("D");
        nodeD.setAttribute("data", nodeDataD);
        
        Node nodeE = graph.addNode("E");
        nodeE.setAttribute("data", nodeDataE);
        
        Node nodeF = graph.addNode("F");
        nodeF.setAttribute("data", nodeDataF);

        // Élek hozzáadása
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("CD", "C", "D");
        graph.addEdge("CE", "C", "E");
        graph.addEdge("CF", "C", "F");
        
        System.setProperty("org.graphstream.ui", "swing");
        // Gráf elrendezése
        graph.display();
        

        
    }
}