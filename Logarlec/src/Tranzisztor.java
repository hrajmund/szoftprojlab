public class Tranzisztor extends Item{
    Room room;
    Tranzisztor Connected;
    boolean active;

    public void setState(boolean activated) {
        this.active = activated;
    }
    boolean getState(){
        return active;
    }

    void deactivate(){
        this.active = false;
    }

    @Override
    void effect(Student s) {

    }
}
