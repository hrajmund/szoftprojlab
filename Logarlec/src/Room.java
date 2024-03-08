import java.util.List;

public class Room implements IRound{
    int capacity;
    List<Room> outgoingDoors;
    List<Room> incomingDoors;
    List<BaseItem> items;
    boolean gas;
    RoomState stateOfRoom;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Room> getOutgoingDoors() {
        return outgoingDoors;
    }

    public List<Room> getIncomingDoors() {
        return incomingDoors;
    }

    public void addIncomingDoor(Room r){
        incomingDoors.add(r);
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

    public boolean isGas() {
        return gas;
    }

    public void setGas(boolean gas) {
        this.gas = gas;
    }

    public Room(){

    }

    public void tick(){}
    public void addItem(BaseItem b){}
    public void addPerson(Person p){}
    public void removePerson(Person p){}

}
