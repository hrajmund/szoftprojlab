import java.util.List;

public class Student extends Person{
    boolean teacherProtected;
    boolean gasProtected;
    public Student(){

    }
    @Override
    public void Move() {

    }

    @Override
    public void Move() {}


    public void PickUpItem(Item i) {}

    public String getName() {
        return null;
    }


    public void setName(String s) {

    }

    public List<Item> getItems() {
        return null;
    }

    public void setItems(Object o) {
    }

    public int getICapacity() {
        return 0;
    }
    public void PutDownItem(int i){}
    public void UseItem(int i){}
    public boolean isTeacherProtected(){ return teacherProtected;}
    public boolean isGasProtected(){return gasProtected;}
    @Override
    public void tick(){}
    public void dropAllItems(){}
    public void stun(){}

    @Override
    public void kill() {

    }

}
