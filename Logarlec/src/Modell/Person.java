package Modell;
import Modell.Room;
import Modell.TestPrinter;

import java.util.ArrayList;

/**
 * Személy ősosztály
 */
public abstract class Person implements IRound{
    /**
     * A személy labirintusa
     */
    protected Labyrinth labyrinth;
    /**
     * A személy neve
     */
    protected String name = null;
    /**
     * A személy tárgyai
     */
    protected ArrayList<BaseItem> items = new ArrayList<>();
    /**
     * A személy bénítva van-e
     */
    protected boolean stunned = false;
    /**
     * A személy jelenlegi szobája
     */
    protected Room currentRoom;
    /**
     * A személynél lévő tárgyak kapacitása
     */
    protected int capacity=5;

    /**
     * Tárgy hozzáadása a személyhez
     */
    public abstract void addItem(BaseItem i);
    /**
     * A személy mozgatása
     */
    public abstract void move(Room r);
    /**
     * A személy bénítása
     */
    public abstract void stun();
    /**
     * A személy öl
     */
    public abstract  void kill(Person p);
    /**
     * A személy meg lett ölve
     */
    public abstract void killed();
    /**
     * A tanár bénítása
     */
    public abstract void stunTeacher();

    /**
     * A személy nevének lekérdezése
     */
    public String getName() {
        TestPrinter.printCallingMethod();
        return name;
    }

    /**
     * A személy nevének beállítása
     */
    public void setName(String s) {
        TestPrinter.printCallingMethod(s);
    }
    /**
     * A személy tárgyainak lekérdezése
     */
    public ArrayList<BaseItem> getItems() {
        TestPrinter.printCallingMethod();
        return items;
    }

    /**
     * A személy tárgyainak beállítása
     */
    public void setItems(ArrayList<BaseItem> items){
        TestPrinter.printCallingMethod(items);
        this.items=items;
    }

    /**
     * A személy kapacitásának lekérdezése
     */
    public int getCapacity() {
        TestPrinter.printCallingMethod();
        return capacity;}
    /**
     * A személy jelenlegi szobájának beállítása
     */
    public void setCurrentRoom(Room c){
        TestPrinter.printCallingMethod(c);
        currentRoom=c;
    }

    /**
     * A személy jelenlegi szobájának lekérdezése
     */
    public Room getCurrentRoom(){
        TestPrinter.printCallingMethod();
        return currentRoom;
    }

    /**
     *  Személy felvesz egy tárgyat
     */
    public void pickUpItem(BaseItem i){
        TestPrinter.printCallingMethod(i);
        if(!currentRoom.getSticky()){ //MÓDOSÍTÁS: ha a szoba ragacsos, nem vehetünk fel benne itemet
            if(!i.getActive()) {
                if(capacity > items.size()) {
                    i.setHolder(this);
                    i.setRoom(null);
                    currentRoom.removeItem(i);
                    addItem(i);
                }
            }
        }
    }

    /**
     * Személy leteszi a tárgyat
     */
    public void putDownItem(BaseItem i){
        TestPrinter.printCallingMethod();
        //kommenteltek kiszervezése az itembe hogy mindegyik item maga tudja mit kell tennie
        i.putDown(currentRoom);
        //i.setRoom(currentRoom);
        //i.setHolder(null);
        items.remove(i);
        currentRoom.addItem(i);
    }

    /**
     * Személy eldobja az össuzes tárgyat
     */
    public void dropAllItems(){
        TestPrinter.printCallingMethod();
        for(BaseItem i : items){
            i.putDown(currentRoom);
        }
        currentRoom.addItems(items);
        items.clear();
    }
    /**
     * Beállítja a tanár elleni védelmet
     */
    public void setTeacherProtection(boolean protection){
        TestPrinter.printCallingMethod();
    }
    /**
     * Fel tudja-e venni a logarlécet
     */
    public Boolean canPickUpLogarlec(){
        TestPrinter.printCallingMethod();
        return false;
    }

    /**
     * Beállítja a gázok elleni védelmet
     */
    public void setGasProtection(Boolean b){
        TestPrinter.printCallingMethod(b);
    }

    /**
     * Tud-e tranzisztort aktiválni
     */
    public Boolean canActivateTranzisztor(){
        TestPrinter.printCallingMethod();
        return false;
    }
    /**
     * Tárgy eltávolítása a személytől
     */
    public void removeItem(BaseItem i){
        TestPrinter.printCallingMethod(i);
        items.remove(i);
    }

    /**
     * A személy teleportálása
     */
    public void teleport(Room r){
        TestPrinter.printCallingMethod(r);
        currentRoom.removePerson(this);
        r.addPerson(this);
        currentRoom = r;
        for(Person p : r.getPeople()){
            p.kill(this);
        }
    }

    /**
     * Labirinta beállítása
     */
    public void setLabyrinth(Labyrinth l){
        labyrinth=l;
    }
    /**
     * Vissza adja, hogy stunolva van e.
     */
    public boolean getStun(){return stunned;}
}
