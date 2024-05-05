package Grafikus;

import Modell.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel {
    
    static private GraphComponent graph = null;
    GameManager gameManager = null;
    Student currentStudent = null;
    BaseItem pickedInventoryItem = null;
    BaseItem pickedRoomItem = null;
    Room pickedRoom = null;
    
    JButton pickUpButton = new JButton("Felvesz");
    JButton putDownButton = new JButton("Letesz");
    JButton useButton = new JButton("Használ");
    JButton moveButton = new JButton("Mozog");
    JButton nextButton = new JButton("Következő");
    
    public void setCurrentStudent(Student student) {
        currentStudent = student;
    }

    public static GraphComponent getGraph() {
        return graph;
    }
    
    
    public void DeadPopUp(Student s){
        JOptionPane.showMessageDialog(null, s.getName() + " meghalt!");
    }
    
    public void StunnedPopUp(Student s){
        JOptionPane.showMessageDialog(null, s.getName() + " megbénult!");
    }
    
    public void GameEndPopUp(Student s){
        
    }
    
    
    
    
    
    
    ///GOMBOK ACTION LISTENERJEI///
    
    class pickUpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedRoomItem != null) {

                
            }
        }
    }
    
    class putDownButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedInventoryItem != null) {

                
            }
        }
    }
    
    class useButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedInventoryItem != null) {

            }
        }
    }
    
    class moveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedRoom != null) {

            }
        }
    }
    
    class nextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    } 
    
    
    
    
}
