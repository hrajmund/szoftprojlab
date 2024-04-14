package Modell;
import Modell.TestPrinter;
/**
 * Tranzisztor tárgy
 */
public class Tranzisztor extends BaseItem{
    private Tranzisztor connected;

    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        TestPrinter.printCallingMethod();
        if(holder.items.contains(this.connected)){
            active=true;
            holder.putDownItem(this);
            room.capacity--;
        }else{
            active=true;
            Person teleportPerson= holder;
            holder.putDownItem(this);
            connected.room.capacity++;
            teleportPerson.teleport(connected.room);
            resetTranzisztors();
        }
    }

    /**
     * A tranzisztor felvétele
     */
    @Override
    public void setHolder(Person holder){
        if (holder == null) {
            TestPrinter.printCallingMethod();
        }
        else{
            TestPrinter.printCallingMethod(holder);
            for(BaseItem item: holder.items){
                if(item.isConnected()!=null){
                    item.setConnected(this);
                    setConnected(item);
                    return;
                }
            }
        }
        this.holder=holder;

    }

    /**
     * A tranzisztor összkapcsolható-e
     */
    public Tranzisztor isConnected(){
        TestPrinter.printCallingMethod();
        if(connected==null)
            return this;
        return null;
    }

    /**
     * A tranzisztorok összekapcsolása
     */
    @Override
    public void setConnected(BaseItem t){
        TestPrinter.printCallingMethod(t);
        connected =(Tranzisztor) t;
    }

    /**
     * Tranzisztorok alaphelyzetbe állítása
     */
    public void resetTranzisztors(){
        TestPrinter.printCallingMethod();
        connected.active=false;
        connected.connected=null;
        active=false;
        connected=null;
    }
    @Override
    public void putDown(Room r){
        TestPrinter.printCallingMethod(holder);
        holder = null;
        room = r;
    }
     public void tick(){
     }
}
