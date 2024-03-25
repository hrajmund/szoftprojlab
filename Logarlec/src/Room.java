import java.util.ArrayList;
import java.util.List;

public class Room implements IRound{
    Labyrinth labyrinth;
    int capacity;
    ArrayList<Room> outgoingDoors;
    ArrayList<Room> incomingDoors;
    ArrayList<BaseItem> items;
    boolean gas = false;
    ArrayList<Person> people;
    RoomState state;

    public ArrayList<Person> getPeople() {
        System.out.println(this.toString() + ":: getPeople() függvénye");
        return people;
    }

    public int getCapacity() {
        System.out.println(this.getCapacity() + ":: getCapacity() függvénye");
        return capacity;
    }
    public void setCapacity(int capacity) {
        System.out.println(this.toString() + ":: setCapacity() paramétere: " + capacity + "-re");
        this.capacity = capacity;
    }
    public List<BaseItem> getItems() {
        System.out.println(this.toString() + ":: getItems() függvénye");
        return items;
    }

    public void setIncomingDoors(ArrayList<Room> n){
        System.out.println(this.toString() + ":: setIncomingDoors() paramétere: " + n.toString() + "-re");
        incomingDoors=n;
    }
    public void setOutgoingDoors(ArrayList<Room> outgoingDoors) {
        System.out.println(this.toString() + ":: setOutgoingDoors() paramétere: " + outgoingDoors.toString() + "-re");
        this.outgoingDoors = outgoingDoors;
    }
    public boolean getGas() {
        System.out.println(this.toString() + ":: getGas() függvénye");
        return gas;
    }
    public void setGas(boolean gas) {
        System.out.println(this.toString() + ":: setGas() paramétere: " + gas + "-re");
        this.gas = gas;
    }
    public ArrayList<Room> getIncomingDoors(){
        System.out.println(this.toString() + ":: getIncomingDoors() függvénye");
        return (ArrayList<Room>) incomingDoors;
    }
    public ArrayList<Room> getOutgoingDoors(){
        System.out.println(this.toString() + ":: getOutgoingDoors() függvénye");
        return (ArrayList<Room>) outgoingDoors;
    }
    public ArrayList<Room> getMovePossibilities(){
        System.out.println(this.toString() + ":: getMovePossibilites() függvénye");
        ArrayList<Room> movePossibilities = new ArrayList<>();
        return movePossibilities;
    }

    public void addOutgoingDoor(Room r){
        System.out.println(this.toString() + ":: addOutgoingDoor() paramétere: " + r.toString() + "-re");
    }
    public void addIncomingDoor(Room r){
        System.out.println(this.toString() + ":: addIncomingDoor() paramétere: " + r.toString() + "-re");
        incomingDoors.add(r);
    }
    public void addItems(List<BaseItem> items) {
        System.out.println(this.toString() + ":: addItems() paramétere: " + items.toString() + "-re");
        this.items.addAll(items);
    }
    public void addItem(BaseItem b){
        System.out.println(this.toString() + ":: addItems() paramétere: " + b.toString() + "-re");
    }
    public void removeItem (BaseItem b) {
        System.out.println(this.toString() + ":: removeItem() paramétere: " + b.toString() + "-re");

    }
    public void addPerson(Person p){
        System.out.println(this.toString() + ":: addPerson() paramétere: " + p.toString() + "-re");
        if(gas) {
            p.stun();
        }
        for(BaseItem item : items){
            if(item.canStun()){
                p.stunTeacher();
            }
        }
    }
    public void removePerson(Person p){
        System.out.println(this.toString() + ":: removePerson() paramétere: " + p.toString() + "-re");
    }
    public void removeOutgoingDoor(Room r){
        r.removeIncomingDoor(this);
        System.out.println(this.toString() + ":: removeOutgoingDoor() paramétere: " + r.toString() + "-re");
    }
    public void removeIncomingDoor(Room r){
        r.removeOutgoingDoor(this);
        System.out.println(this.toString() + ":: removeIncomingDoor() paramétere: " + r.toString() + "-re");
    }
    public void tick(){
        System.out.println(this.toString() + ":: tick() függvénye");
    }
    public List<Room>  movePossibilities() {
        System.out.println(this.toString() + ":: movePossibilities() függvénye");
        return null;
    }

    //hozzáadja room-hoz newdoors doorjait, úgy, hogy a duplikátumok csak egyszer szerepeljenek
    public void addDoors(ArrayList<Room> newDoors, ArrayList<Room> room){

    }

    public void merge(Room r){
        addDoors(r.outgoingDoors,outgoingDoors);
        addDoors(r.incomingDoors, incomingDoors);
        for(Room neighbours : r.incomingDoors){
            neighbours.removeOutgoingDoor(r);
            neighbours.addOutgoingDoor(this);
        }
        items.addAll(r.items);
        gas = gas || r.gas;
        capacity = Math.max(capacity, r.capacity);
        labyrinth.removeRoom(r);
        System.out.println(this.toString() + ":: Merge() függvénye");
    }
    public void split(){
        Room newroom = new Room();
        labyrinth.addRoom(newroom);

        //newroom.outgoingDoors = outgoingDoors;
        //newroom.incomingDoors = incomingDoors;
        for (int i = 0; i< items.size(); i++){
            if(i%2 == 0)
                newroom.addItem(items.get(i));
        }
        newroom.gas = gas;
        newroom.capacity = capacity;

        addOutgoingDoor(newroom);
        addIncomingDoor(newroom);
        newroom.addOutgoingDoor(this);
        newroom.addIncomingDoor(this);

        System.out.println(this.toString() + ":: split() függvénye");
    }
}
