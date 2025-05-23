package Modell;
import Modell.Room;
import Modell.TestPrinter;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Személy ősosztály
 */
public abstract class Person {
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
        return name;
    }

    /**
     * A személy nevének beállítása
     */
    public void setName(String s) {
        name = s;
    }
    /**
     * A személy tárgyainak lekérdezése
     */
    public ArrayList<BaseItem> getItems() {
        return items;
    }

    /**
     * A személy tárgyainak beállítása
     */
    public void setItems(ArrayList<BaseItem> items){
        this.items=items;
    }

    /**
     * A személy kapacitásának lekérdezése
     */
    public int getCapacity() {
        return capacity;}
    /**
     * A személy jelenlegi szobájának beállítása
     */
    public void setCurrentRoom(Room c){
        currentRoom=c;
    }

    /**
     * A személy jelenlegi szobájának lekérdezése
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }

    /**
     *  Személy felvesz egy tárgyat
     */
    public void pickUpItem(BaseItem i){
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

    }
    /**
     * Fel tudja-e venni a logarlécet
     */
    public Boolean canPickUpLogarlec(){
        return false;
    }

    /**
     * Beállítja a gázok elleni védelmet
     */
    public void setGasProtection(Boolean b){

    }

    /**
     * Tud-e tranzisztort aktiválni
     */
    public Boolean canActivateTranzisztor(){
        return false;
    }
    /**
     * Tárgy eltávolítása a személytől
     */
    public void removeItem(BaseItem i){
        items.remove(i);
    }

    /**
     * A személy teleportálása
     */
    public void teleport(Room r){
        currentRoom.removePerson(this);
        r.addPerson(this);
        currentRoom = r;
        for(Person p : r.getPeople()){
            p.kill(this);
        }
        if(labyrinth.getGameManager().getGamePanel() != null){
            labyrinth.getGameManager().getGamePanel().getGraphComponent().refreshNodes();
            labyrinth.getGameManager().getGamePanel().setCurrentStudent((Student) this);
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

    public abstract void PrintOutPerson(PrintWriter writer);

    public void UseItem(int i){}

    public void setStun(boolean b) {
        stunned=b;
    }
}
