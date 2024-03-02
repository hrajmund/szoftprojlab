public class Tranzisztor extends BaseItem{
    Room position;
    Tranzisztor paired;
    boolean activated;
    @Override
    void effect() {

    }
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    boolean getActivated(){
        return activated;
    }
}
