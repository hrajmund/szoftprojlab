package Modell;

import javax.lang.model.element.Name;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Takarito extends Person{
    //DONE: nulára kell állítsa a szoba personCounterjét.
    //DONE: Ha belép egy szobába, minden mozogni képes (nem bénult / ájult) embert kitessékel onnan
    //DONE: Ha gázos szobába lép, kiszellőztet, megszüntetve a szoba gázosságát.

    public Takarito(){
        name = "UnknownRoom";
    }

    public Takarito(String n){
        name = n;
    }

    @Override
    public void addItem(BaseItem i) {

    }

    @Override
    public void move(Room r) {
        currentRoom.removePerson(this);
        currentRoom = r;
        currentRoom.gas = false; //"kiszellőztet"
        
        
        
        currentRoom.personCounter = 0; //"kitakarit"
        ArrayList<Person>peoplemove= new ArrayList<>();
        for (Person p : currentRoom.getPeople()) //"kitessekel"
        {
            if((!p.stunned) && p != this){
                if(labyrinth.getStudents().contains(p)){
                    labyrinth.getGameManager().getGamePanel().getGraphComponent().refreshNodes();
                }
                getCurrentRoom().outgoingDoors.get(0).addPerson(p);
                p.setCurrentRoom(getCurrentRoom().outgoingDoors.get(0));
                peoplemove.add(p);
                //Random rnd = new Random();
                //int whichRoom = rnd.nextInt(0, getCurrentRoom().outgoingDoors.size());
                //getCurrentRoom().outgoingDoors.get(whichRoom).addPerson(p);
                //p.setCurrentRoom(getCurrentRoom().outgoingDoors.get(whichRoom)); //mindenkit ugyanabba a szobába küld, a capacityvel nem foglalkozik, mivel ez elvi síkon is problémát okozna
            }
        }
        currentRoom.getPeople().removeAll(peoplemove);
        r.addPerson(this); //Emiatt van csak itt hozzáadva
    }

    /**
     * Kábítja a jelenlegi szobában lévő entitásokat
     */
    @Override
    public void stun() {
    }

    /**
     * N
     */
    @Override
    public void kill(Person p) {
    }

    @Override
    public void killed() {
    }

    @Override
    public void stunTeacher() {
    }

    @Override
    public void PrintOutPerson(PrintWriter writer){
        writer.println("    " + name +
                "\n        [CurrentRoom] " + currentRoom.getName() +
                "\n        [Stun] " + stunned
        );
    }
}
