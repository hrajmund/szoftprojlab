public class NedvesTablatorlo extends BaseItem implements IRound{
    int timeUsage;
    public NedvesTablatorlo(){}
    @Override
    void effect(Student student) {

    }
    public int getTimeUsage() {
        return timeUsage;
    }

    public void setTimeUsage(int timeUsage) {
        this.timeUsage = timeUsage;
    }
    public void tick(){}
    public void activate(Student s){}

}
