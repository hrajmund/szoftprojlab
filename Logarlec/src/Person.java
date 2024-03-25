import java.util.ArrayList;

public abstract class Person implements IRound{
    protected Labyrinth labyrinth;
    protected String name = null;
    protected ArrayList<BaseItem> items = new ArrayList<>();
    protected boolean stunned;
    protected Room currentRoom;
    protected int capacity=5;

    public abstract String getName();
    public abstract void setName(String s);
    public abstract BaseItem[] getItems();
    public abstract void setItems(ArrayList<BaseItem> l);
    public abstract int getCapacity();
    public abstract void addItem(BaseItem i);
    public void setCurrentRoom(Room c){currentRoom=c;}
    public Room getCurrentRoom(){
        return currentRoom;
    }
    public void pickUpItem(BaseItem i){
        TestPrinter.printCallingMethod();
        if(!i.getActive()) {
            if(capacity < items.size()) {
                i.setHolder(this);
                i.setRoom(null);
                currentRoom.removeItem(i);
                addItem(i);
            }
        }
    }

    public void putDownItem(BaseItem i){
        i.setHolder(null);
        i.setRoom(currentRoom);
        items.remove(i);
        currentRoom.addItem(i);

    }
    public abstract void move(Room r);
    public void dropAllItems(){
        currentRoom.addItems(items);
    }
    public abstract void stun();
    public abstract  void kill(Person p);
    public abstract void killed();
    public void setTeacherProtection(boolean protection){
    }
    public Boolean canPickUpLogarlec(){
        return false;
    }


}
