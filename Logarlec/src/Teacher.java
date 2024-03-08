public class Teacher extends Person{
    public Teacher(){}

    @Override
    public void Move() {

    }

    @Override
    public void PickUpItem(Object o) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
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
}
