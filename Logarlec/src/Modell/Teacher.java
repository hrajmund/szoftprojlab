package Modell;
import Modell.Room;
import Modell.TestPrinter;
/**
 * Techer osztály
 */
public class Teacher extends Person{
    /**
     * Teacher eldobja az összes tárgyát
     */
    public void dropAllItems(){
        TestPrinter.printCallingMethod();
    }

    /**
     * Tanár megbénul gáz miatt
     */
    public void stun(){
        TestPrinter.printCallingMethod();
        dropAllItems();
        stunned=true;
    }

    /**
     * Tanár felvesz egy tárgyat
     */
    public void addItem(BaseItem i){
        TestPrinter.printCallingMethod();
        items.add(i);
    }

    /**
     * A tanár átmegy egy másik szobába
     */
    @Override
    public void move(Room r) {
        TestPrinter.printCallingMethod(r);
        currentRoom.removePerson(this);
        r.addPerson(this);
        currentRoom = r;
        for(Person p : r.getPeople()){
            p.killed();
        }
    }

    /**
     * A tanár öl
     */
    public void kill(Person p){
        TestPrinter.printCallingMethod(p);
        p.killed();
    }

    /**
     * A tanár meg lesz ölve
     */
    public void killed(){
        TestPrinter.printCallingMethod();
    }

    /**
     * A tanár megbénul
     */
    public void stunTeacher() {
        TestPrinter.printCallingMethod();
        this.stun();
    }

}
