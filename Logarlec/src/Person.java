import java.util.List;

public abstract class Person {

    String name;

    List<Item> items;

    Room currentRoom;

    boolean stunned;

    public void dropAllItems() {}


    public void Move() {}

    public void PickUpItem(Object o) {}

    public String getName() {
        return null;
    }

    public void setName(String s) {
    }

    public void setItems(Object o) {
    }

    public int getICapacity() {
        return 0;
    }

}
