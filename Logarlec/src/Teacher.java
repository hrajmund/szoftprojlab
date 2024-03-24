import java.util.ArrayList;

public class Teacher extends Person{
    public Teacher(){}
    public String getName(){return "";}
    public void setName(String s){}
    @Override
    public void pickUpItem(BaseItem o) {}
    public void putDownItem(BaseItem i) {}
    public void dropAllItems(){}
    public void move(){}
    public void stun(){}
    @Override
    public BaseItem[] getItems() {
        return new BaseItem[0];
    }
    public void setItems(ArrayList<BaseItem> items){
        System.out.println("");
    }
    @Override
    public int getCapacity() {
        return 0;
    }
    @Override
    public void tick(){}
    public void addItem(BaseItem i){}
    @Override
    public void move(Room r) {
        currentRoom.removePerson(this);
        r.addPerson(this);
        currentRoom = r;
        for(Person p : r.getPeople()){
            p.killed();
        }
        System.out.println(this.toString() + ":: move() paramétere: " + r.toString() + "-re");
    }
    public void kill(Person p){
        p.killed();
    }
    public void killed(){}


}
