import jdk.jshell.spi.SPIResolutionException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student extends Person{
    boolean teacherProtected;
    boolean gasProtected;
    public Student(){
    }
    @Override
    public String getName() {
        return null;
    }
    @Override
    public void setName(String s) {}
    @Override
    public BaseItem[] getItems() {
        return new BaseItem[0];
    }
    public void setItems(ArrayList<BaseItem> items){}
    @Override
    public int getCapacity(){return 0;}

    public void stun(){
        if (this.currentRoom.getGas() && !this.isGasProtected()){
            this.dropAllItems();
            this.stunned=true;
        }
    }
    public void protAgainstTeacher(){
        System.out.println(this.toString() + ":: protAgainstTeacher() függvénye");
    }

    @Override
    public void setTeacherProtection(boolean protection){
        teacherProtected = protection;
    }
    public boolean isTeacherProtected(){ return teacherProtected;}
    public boolean isGasProtected(){return gasProtected;}
    public void UseItem(int i){}
    @Override
    public void tick(){}
    public void addItem(BaseItem i){}

    public void dropAllItems(){}

    public void killed() {
        //Ha van nála aktív SzentSorosPoharak, akkor védelme van a Teacher ellen
        if(teacherProtected){
            return;
        }
        //Ha nincs aktív SzentSorospohara, akkor megnézi, hogy van-e nála olyan item, ami megvédi a Teachertől
        else{
            for(BaseItem item : items) {
                if (item.protAgainstTeacher()) {
                    return;
                }
            }
        }
        this.dropAllItems();
        currentRoom.removePerson(this);
        labyrinth.endGame();
    }
    public Boolean canPickUpLogarlec(){
        return true;
    }

    @Override
    public void move(Room r) {
        currentRoom.removePerson(this);
        r.addPerson(this);
        currentRoom = r;
        for(Person p : r.getPeople()){
            p.kill(this);
        }
        System.out.println(this.toString() + ":: move() paramétere: " + r.toString() + "-re");
    }
    public void kill(Person p){
    }
}
