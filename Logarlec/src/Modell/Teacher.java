package Modell;
import Modell.Room;
import Modell.TestPrinter;
import Szkeleton.Szkeleton;

import javax.naming.Name;
import java.util.Random;

/**
 * Techer osztály
 */
public class  Teacher extends Person{

    public Teacher(){
        name = "UnknownRoom";
    }

    public Teacher(String n){
            name = n;
    }

    /**
     * Teacher eldobja az összes tárgyát
     */
    public void dropAllItems(){
        for(BaseItem i : items){
            i.putDown(currentRoom);
        }
        items.clear();
    }

    /**
     * Tanár megbénul gáz miatt
     */
    public void stun(){
        dropAllItems();
        stunned=true;
    }

    /**
     * Tanár felvesz egy tárgyat
     */
    public void addItem(BaseItem i){
        items.add(i);
    }

    /**
     * A tanár átmegy egy másik szobába
     */
    @Override
    public void move(Room r) {
        if(!Szkeleton.DETERMINISTIC_MODE){
            Random randomNum = new Random();
            int rand1 = randomNum.nextInt(currentRoom.movePossibilities().size());
            r=currentRoom.movePossibilities().get(rand1);
        }

        currentRoom.removePerson(this);
        r.addPerson(this);
        r.increasePersonCounter();
        currentRoom = r;
        for(Person p : r.getPeople()){
            p.killed();
        }
    }

    /**
     * A tanár öl
     */
    public void kill(Person p){
        p.killed();
    }

    /**
     * A tanár meg lesz ölve
     */
    public void killed(){

    }

    /**
     * A tanár megbénul
     */
    public void stunTeacher() {
        this.stun();
    }

    @Override
    public void tick() {

    }
    @Override
    public void PrintOutPerson(){
        System.out.println("\n\t" + this.getName() +
                "\n\t    [CurrentRoom] " + this.getCurrentRoom().Name +
                "\n\t    [Stun] " + this.getStun()
        );
        for(int i = 0; i < this.items.size(); i++){
            System.out.println(String.format("[Item %d]", i));
            this.items.get(i).PrintOutItem();
        }
    }
}
