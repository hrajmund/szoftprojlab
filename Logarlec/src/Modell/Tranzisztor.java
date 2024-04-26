package Modell;
import Modell.TestPrinter;
/**
 * Tranzisztor tárgy
 */
public class Tranzisztor extends BaseItem{
    private Tranzisztor connected;

    public Tranzisztor(){
        name="UnknownTranzisztor";
    }

    public Tranzisztor(String n){
        name=n;
    }

    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
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
            this.holder=holder;
        }
        else{
            for(BaseItem item: holder.items){
                if(item.isConnected()!=null){
                    item.setConnected(this);
                    setConnected(item);
                    this.holder=holder;
                    return;
                }
            }
        }


    }

    /**
     * A tranzisztor összkapcsolható-e
     */
    public Tranzisztor isConnected(){
        if(connected==null)
            return this;
        return null;
    }

    /**
     * A tranzisztorok összekapcsolása
     */
    @Override
    public void setConnected(BaseItem t){
        connected =(Tranzisztor) t;
    }

    /**
     * Tranzisztorok alaphelyzetbe állítása
     */
    public void resetTranzisztors(){
        connected.active=false;
        connected.connected=null;
        active=false;
        connected=null;
    }
    @Override
    public void putDown(Room r){
        holder = null;
        room = r;
    }
    public void tick(){
    }

    @Override
    public void PrintOutItem(){
        System.out.print(name + " (Active) " +
                active +
                " (Connected) " + connected.name);
    }
}
