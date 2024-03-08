public class Tranzisztor extends BaseItem{
    Room position;
    Tranzisztor connected;
    boolean active;
    @Override
    void effect(Student student) {

    }
    public void setActivated(boolean active) {
        this.active = active;
    }
    boolean getActivated(){
        return active;
    }
    Tranzisztor(){}
    public void activate(Tranzisztor t){}
    public void deactivate(){}
}
