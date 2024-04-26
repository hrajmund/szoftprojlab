package Modell;

import java.util.ArrayList;
import java.util.List;

/**
 * A szoba osztály reprezentál egy labirintus szobát.
 */
public class Room implements IRound{

    protected String Name;

    /**
     * A szobához tartozó labirintus.
     */
    protected Labyrinth labyrinth;

    /**
     * A szoba befogadóképessége.
     */
    protected int capacity = 5;

    /**
     * A szobából kimenő ajtók listája.
     */
    protected ArrayList<Room> outgoingDoors = new ArrayList<>();

    /**
     * A szobába belépő ajtók listája.
     */
    protected ArrayList<Room> incomingDoors = new ArrayList<>();

    /**
     * A szobában található tárgyak listája.
     */
    protected ArrayList<BaseItem> items = new ArrayList<>();

    /**
     * A szoba gáztartalmát jelző logikai érték.
     */
    protected boolean gas = false;

    /**
     * A szobában tartózkodó emberek listája.
     */
    protected ArrayList<Person> people = new ArrayList<>();

    /**
     * A szoba ragadós-e
     */
    protected Boolean sticky = false;

    /**
     * A szobába hány ember járt a takarító után
     */
    protected int personCounter = Integer.MIN_VALUE; //inicializáláskor minimumról indul, majd a takarító nullázza
    public Room(){
        Name = "UnknownRoom";
    }

    public Room(String n){
            Name = n;
    }

    /**
     * Visszaadja a szobában tartózkodó emberek listáját.
     *
     * @return A szobában tartózkodó emberek listája.
     */
    public ArrayList<Person> getPeople() {
        return people;
    }

    /**
     * Visszaadja a szoba befogadóképességét.
     *
     * @return A szoba befogadóképessége.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Beállítja a szoba befogadóképességét.
     *
     * @param capacity A beállítandó befogadóképesség.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Visszaadja a szobában található tárgyak listáját.
     *
     * @return A szobában található tárgyak listája.
     */
    public List<BaseItem> getItems() {
        return items;
    }

    /**
     * Beállítja a szobába belépő ajtók listáját.
     *
     * @param n Az új ajtók listája.
     */
    public void setIncomingDoors(ArrayList<Room> n){
        incomingDoors = n;
    }

    /**
     * Beállítja a szobából kimenő ajtók listáját.
     *
     * @param outgoingDoors Az új kimenő ajtók listája.
     */
    public void setOutgoingDoors(ArrayList<Room> outgoingDoors) {
        this.outgoingDoors = outgoingDoors;
    }

    /**
     * Visszaadja, hogy a szobában van-e gáz.
     *
     * @return True, ha a szobában van gáz, különben false.
     */
    public boolean getGas() {
        return gas;
    }

    /**
     * Beállítja, hogy a szobában van-e gáz.
     *
     * @param gas True, ha a szobában van gáz, különben false.
     */
    public void setGas(boolean gas) {
        this.gas = gas;
    }

    /**
     * Visszaadja a szobába belépő ajtók listáját.
     *
     * @return A szobába belépő ajtók listája.
     */
    public ArrayList<Room> getIncomingDoors(){
        return (ArrayList<Room>) incomingDoors;
    }

    /**
     * Visszaadja a szobából kimenő ajtók listáját.
     *
     * @return A szobából kimenő ajtók listája.
     */
    public ArrayList<Room> getOutgoingDoors(){
        return (ArrayList<Room>) outgoingDoors;
    }

    /*
     // Visszaadja a szoba által biztosított mozgási lehetőségeket.
     //
     // @return A mozgási lehetőségek listája.
    public ArrayList<Room> getMovePossibilities(){
        TestPrinter.printCallingMethod();
        ArrayList<Room> movePossibilities = new ArrayList<>();
        return outgoingDoors;
    }
    */

    /**
     * Hozzáad egy kimenő ajtót a szobához.
     *
     * @param r A hozzáadandó kimenő ajtó.
     */
    public void addOutgoingDoor(Room r){
        outgoingDoors.add(r);
    }

    /**
     * Hozzáad egy belépő ajtót a szobához.
     *
     * @param r A hozzáadandó belépő ajtó.
     */
    public void addIncomingDoor(Room r){
        incomingDoors.add(r);
    }

    /**
     * Hozzáadja a szobához a megadott tárgyakat.
     *
     * @param items A hozzáadandó tárgyak listája.
     */
    public void addItems(List<BaseItem> items) {
        this.items.addAll(items);
        for(BaseItem item: items){
            item.setRoom(this);
        }
    }

    /**
     * Hozzáad egy tárgyat a szobához.
     *
     * @param b A hozzáadandó tárgy.
     */
    public void addItem(BaseItem b){
        items.add(b);
    }

    /**
     * Eltávolít egy tárgyat a szobából.
     *
     * @param b Az eltávolítandó tárgy.
     */
    public void removeItem (BaseItem b) {
        items.remove(b);
    }

    /**
     * Hozzáad egy embert a szobához.
     *
     * @param p A hozzáadandó személy.
     */
    public void addPerson(Person p){
        people.add(p);
        if(gas) {
            p.stun();
        }
        for(BaseItem item : items){
            if(item.canStun()){
                p.stunTeacher();
            }
        }
    }

    /**
     * Eltávolít egy embert a szobából.
     *
     * @param p Az eltávolítandó személy.
     */
    public void removePerson(Person p){
        people.remove(p);
    }

    /**
     * Eltávolít egy kimenő ajtót a szobából.
     *
     * @param r Az eltávolítandó kimenő ajtó.
     */
    public void removeOutgoingDoor(Room r){
        outgoingDoors.remove(r);
    }

    /**
     * Eltávolít egy belépő ajtót a szobából.
     *
     * @param r Az eltávolítandó belépő ajtó.
     */
    public void removeIncomingDoor(Room r){
        incomingDoors.remove(r);
    }

    /**
     * Visszaadja a szoba által biztosított mozgási lehetőségeket.
     *
     * @return A mozgási lehetőségek listája.
     */
    public List<Room> movePossibilities() {
        List<Room> MovePossibilities = new ArrayList<>();
        for (Room r: outgoingDoors) {
            if (r.isEnterable(this))
                MovePossibilities.add(r);
        }
        return MovePossibilities;
    }

    /**
     * Visszaadja ,hogy a szobába van e még férőhely.
     */
    public boolean isEnterable(Room r){
        //r nincs használva, csak a cursedroom-ban
        return people.size() < capacity;
    }

    /**
     * Hozzáadja a szobához az új ajtókat, figyelve arra, hogy a duplikátumok csak egyszer szerepeljenek.
     *
     * @param newDoors Az új ajtók listája.
     * @param room     A szoba, amelyhez hozzáadjuk az ajtókat.
     */
    public void addDoors(ArrayList<Room> newDoors, ArrayList<Room> room){
    }

    /**
     * Összefésüli a szobát egy másikkal.
     *
     * @param r Az összefésülendő szoba.
     */
    public void merge(Room r){

        addDoors(r.outgoingDoors,outgoingDoors);
        addDoors(r.incomingDoors,incomingDoors);
        for(Room neighbours : r.incomingDoors){
            neighbours.removeOutgoingDoor(r);
            neighbours.addOutgoingDoor(this);
        }
        items.addAll(r.items);
        gas = gas || r.gas;
        capacity = Math.max(capacity, r.capacity);
        personCounter = Math.min(personCounter, r.personCounter); //
        labyrinth.removeRoom(r);
    }

    /**
     * Szétosztja a szobát.
     */
    public void split(){

        Room newroom = new Room("R"+labyrinth.getRooms().size()); //inkrementalis elnevezes
        labyrinth.addRoom(newroom);

        newroom.outgoingDoors = outgoingDoors;
        newroom.incomingDoors = incomingDoors;
        for (int i = 0; i< items.size(); i++){ //RANDOM XD
            if(i%2 == 0)
                newroom.addItem(items.get(i));
        }
        newroom.gas = gas;
        newroom.capacity = capacity;
        newroom.personCounter = personCounter; //
        addOutgoingDoor(newroom);
        addIncomingDoor(newroom);
        newroom.addOutgoingDoor(this);
        newroom.addIncomingDoor(this);
    }

    /**
     * Beállítja a szobához tartozó labirintust.
     *
     * @param l A szobához tartozó labirintus.
     */
    public void setLabyrinth(Labyrinth l){
        labyrinth=l;
    }

    /**
     * Viszaadja, hogy a szoba ragacsos e
     *
     */
    public Boolean getSticky(){
        return sticky;
    }
    /**
     * Viszaadja, hogy a szobában mennyi láogató volt takarítás után
     *
     */
    public int getPersonCounter(){return personCounter;}
    public String getName(){return Name;}

    public void increasePersonCounter(){
        personCounter++;
        if(personCounter==5){
            sticky=true;
        }
    }
    public void tick(){
    }

    public void PrintOutRoom(){
        System.out.println(Name +
                "\n\t [Gas] " + gas +
                "\n\t [Sticky] " + sticky +
                "\n\t [Capacity] " + capacity
        );

        System.out.print("\t [Incoming] ");
        this.getIncomingDoors().forEach(incoming -> System.out.print(incoming.getName() + " "));
        System.out.print("\n\t [Outgoing] ");
        this.getOutgoingDoors().forEach(outgoing -> System.out.print(outgoing.getName() + " "));
    }
}
