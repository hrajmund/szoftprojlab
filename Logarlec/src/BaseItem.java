public abstract class BaseItem implements IRound{
    String name;
    Person holder;

    Room room;


    public void setRoom(Room room){
    }

    public void resetRoom(){
    }


    public void tick(){};
    abstract void effect(Student student);

    public void setHolder(Person holder){
        this.holder = holder;
    }
    public void resetHolder(){
        this.holder = null;
    }




}
