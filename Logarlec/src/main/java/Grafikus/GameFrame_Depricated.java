package Grafikus;

import Modell.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame_Depricated {

    //protected GraphComponent graph = null;
    GameManager gameManager = null;
    Person currentStudent = null;
    BaseItem pickedInventoryItem = null;
    BaseItem pickedRoomItem = null;
    Room pickedRoom = null;
    
    protected JButton pickUpButton  = new JButton("Felvesz");   
    protected JButton putDownButton = new JButton("Letesz");    
    protected JButton useButton     = new JButton("Használ");   
    protected JButton moveButton    = new JButton("Mozog");     
    protected JButton nextButton    = new JButton("Következő"); 
    
    
    public void setCurrentStudent(Person student) {
        currentStudent = student;
    }

    //public static GraphComponent getGraph() {
        //eturn graph;
    //}
    
    
    public void DeadPopUp(Person s){
        JOptionPane.showMessageDialog(null, s.getName() + " meghalt!");
    }
    
    public void StunnedPopUp(Person s){
        JOptionPane.showMessageDialog(null, s.getName() + " megbénult!");
    }
    
    public void GameEndPopUp(){
        
    }
    
    public void setPickedRoom(Room r){
        pickedRoom = r;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    ///ITEM actionok///
    
    protected class inventoryButtonListener implements ActionListener {
        
        int invButtonIndex;
        
        public inventoryButtonListener(int itemIndex){
            invButtonIndex = itemIndex;
        }
        
        public void actionPerformed(ActionEvent e) {
            if(currentStudent.getItems().size() >= invButtonIndex){
                pickedInventoryItem = currentStudent.getItems().get(invButtonIndex);
            }
        }
    }
    
    protected class roomItemButtonListener implements ActionListener {
        
        int roomButtonIndex;
        
        public roomItemButtonListener(int itemIndex){
            roomButtonIndex = itemIndex;
        }
        
        public void actionPerformed(ActionEvent e) {
            pickedRoomItem = pickedRoom.getItems().get(roomButtonIndex);
        }
    }
    
    
    ///GOMBOK ACTION LISTENERJEI///

    protected class pickUpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedRoomItem != null) {

                
            }
        }
    }

    protected class putDownButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedInventoryItem != null) {

                
            }
        }
    }

    protected class useButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedInventoryItem != null) {

            }
        }
    }

    protected class moveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pickedRoom != null) {

            }
        }
    }

    protected class nextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    } 
    
    
    
    
}
