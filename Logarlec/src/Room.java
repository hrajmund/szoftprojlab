import java.util.List;

public class Room {
    int capacity;
    List<Room> neighbours;
    List<Item> items;
    boolean gas;
    List<Person> people;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Room> getNeighbours() {
        return neighbours;
    }
    public void setNeighbours(List<Room> neighbours) {
        this.neighbours = neighbours;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean getGas() {
        return gas;
    }

    public void setGas(boolean gas) {
        this.gas = gas;
    }

    public void notifyStudents(){}

    public void addPerson(Person person) {}

    public void removePerson(Person person) {}

    public RoomState getState() {return RoomState.open;}
}
