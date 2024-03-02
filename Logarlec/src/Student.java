public class Student implements IPlayer{

    boolean protection;
    public Student(){

    }
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

    public void PutDownItem(int i){}
    public void UseItem(int i){}
    public void Teleport(){}
    public void Die(){}
    public boolean hasProtection(){
        return protection;
    }
}
