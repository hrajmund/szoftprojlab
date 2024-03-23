import java.util.List;

public class Student extends Person{

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

    public void PutDownItem(Item i){}
    public void UseItem(Item i){}
    public void Teleport(){}
    public void Die(){}
}
