public class Tranzisztor extends BaseItem{
    Room position;
    Tranzisztor connected;
    TranzisztorHandler handler;
    boolean active;

    @Override
    public void effect() {
    }
    @Override
    public void setHolder(Person holder){

    }
    public void setPosition(Room p){position=p;}
    public Room getPosition(){return position;}
    public Tranzisztor getConnected(){
        return connected;
    }
    public Boolean getState(){
        return active;
    }
    public void setState(boolean state){active=state;}

}
