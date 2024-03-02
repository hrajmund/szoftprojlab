import java.util.List;

public class Room {
    int capacity;
    List<Room> neighbours;
    List<BaseItem> items;
    boolean gas;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Room> getNeighbours() {
        return neighbours;
    }

    public void removeNeighbour(Room r){
        neighbours.add(r);
    }

    public void removeNeighbour(int i){
        neighbours.remove(neighbours.get(i));
    }

    public void setNeighbours(List<Room> neighbours) {
        this.neighbours = neighbours;
    }

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
    public void StealSoul(){}

}
