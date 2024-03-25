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
        this.holder=holder;
        for(BaseItem item: holder.items){
            if(item.isConnected()!=null){
                item.setConnected(this);
                setConnected(item);
                return;
            }
        }
    }
    public Tranzisztor isConnected(){
        if(connected==null)
            return this;
        return null;
    }
    public Boolean getState(){
        return active;
    }
    public void setState(boolean state){active=state;}

    public void setConnected(BaseItem t ){
        connected =(Tranzisztor)t;
    }

}
