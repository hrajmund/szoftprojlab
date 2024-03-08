public abstract class Person implements IRound{
    String name = null;
    BaseItem[] items = new BaseItem[5];
    boolean stunned;
    Room currentRoom;
    int ICapacity;
    public abstract void Move();
    public abstract void PickUpItem(Object o);
    public abstract String getName();
    public abstract void setName(String s);
    public abstract BaseItem[] getItems();
    public abstract void setItems(Object o);
    public abstract int getICapacity();
    public abstract void tick();
    public abstract void dropAllItems();
    public abstract void stun();
}
