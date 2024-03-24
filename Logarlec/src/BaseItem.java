
public abstract class BaseItem{
    String name;
    Person holder;
    Room room;

    public void setName(String s){
        System.out.println(this.toString() + " " + " :: setName() paramétere:" + s + "-re");
        name = s;
    }
    public String getName() {
        System.out.println(this.toString() + " " + " :: getName() függvénye");
        return name;
    }

    public void setRoom(Room room){
        System.out.println(this.toString() + " " + " :: setRoom() paramétere: " + room.toString() + "-re");

    }
    public void resetRoom(){
        System.out.println(this.toString() + ":: resetRoom() függvénye");
    }

    public void tick(){
        System.out.println(this.toString() + ":: tick() függvénye");
    }

    public void setHolder(Person holder){
        System.out.println(this.toString() + ":: setHolder() paramétere: " + holder.toString() + "-re");
        this.holder = holder;
    }
    public void resetHolder(){
        System.out.println(this.toString() + ":: resetHolder() függvénye");
        this.holder = null;
    }
    public Boolean death_protection(){
        System.out.println(this.toString() + ":: death_protection() függvénye");
        return false;
    }
    public abstract void effect();
    public Boolean protAgainstTeacher(){
        return false;
    }
}
