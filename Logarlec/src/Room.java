import java.util.ArrayList;
import java.util.List;

public class Room implements IRound{
    int capacity;
    List<Room> outgoingDoors;
    List<Room> incomingDoors;
    List<BaseItem> items;
    boolean gas;
    ArrayList<Person> people;
    RoomState state;

    void setNeighbours(ArrayList<Room> outRooms, ArrayList<Room> inRooms){}

    /*public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }*/

    public void addIncomingDoor(Room r){
        incomingDoors.add(r);
    }

    ArrayList<Room> getIncomingDoors(){
        return (ArrayList<Room>) incomingDoors;
    }

    ArrayList<Room> getOutgoingDoors(){
        return (ArrayList<Room>) outgoingDoors;
    }

    public ArrayList<Room> getMovePossibilities(){
        ArrayList<Room> movePossibilities = new ArrayList<>();
        return movePossibilities;
    }

    public void removeIncomingDoor(Room r){}

    public void addOutgoingDoor(Room r){}
    public void removeOutgoingDoor(Room r){}

    public List<BaseItem> getItems() {
        return items;
    }

    public void setItems(List<BaseItem> items) {
        this.items = items;
    }

    public void addItems(List<BaseItem> items) {
        this.items.addAll(items);
    }

    public boolean isGas() {
        return gas;
    }

    public void setGas(boolean gas) {
        this.gas = gas;
    }


    public void tick(){}
    public void addItem(BaseItem b){}
    public void addPerson(Person p){}
    public void removePerson(Person p){}

}
