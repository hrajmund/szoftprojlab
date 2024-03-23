<<<<<<< HEAD
public class Tranzisztor extends Item{
    Room room;
    Tranzisztor Connected;
    boolean active;

    public void setState(boolean activated) {
        this.active = activated;
    }
    boolean getState(){
        return active;
    }

    void deactivate(){
        this.active = false;
    }

    @Override
    void effect(Student s) {
=======
public class Tranzisztor extends BaseItem{
    Room position;
    Tranzisztor connected;
>>>>>>> 160a2fb8b8b4fc9593f286374dafec4394cd16e8

    boolean active;
    @Override
    void effect(Student student) {
    }
<<<<<<< HEAD
=======
    @Override
    public void setHolder(Person holder){
    }
    @Override
    public void setRoom(Room position){
    }
>>>>>>> 160a2fb8b8b4fc9593f286374dafec4394cd16e8
}
