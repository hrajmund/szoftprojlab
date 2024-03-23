public class Teacher extends Person{
    public Teacher(){}

    public BaseItem[] getItems() {
        return new BaseItem[0];
    }

    @Override
    public void setItems(Object o) {

    }

    @Override
    public int getICapacity() {
        return 0;
    }
    @Override
    public void tick(){}
    public void dropAllItems(){}
    public void stun(){}

    @Override
    public void kill() {

    }

}
