package Modell;
import Modell.TestPrinter;

import java.io.PrintWriter;

/**
 * Tranzisztor tárgy
 */
public class Tranzisztor extends BaseItem{
    private Tranzisztor connected=null;

    public Tranzisztor(){
        name="UnknownTranzisztor";
        filename = "tranzisztor.png";
    }

    public Tranzisztor(String n){
        name=n;
        filename = "tranzisztor.png";
    }

    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        if(holder.items.contains(this.connected)){
            //holder.putDownItem(this);
            holder.getCurrentRoom().addItem(this);
            holder.getItems().remove(this);
            this.room = holder.getCurrentRoom();
            this.holder=null;
            room.capacity--;
        }else{
            holder.currentRoom.addItem(this);
            this.room = holder.getCurrentRoom();
            holder.teleport(connected.room);
            connected.room.capacity++;
            holder.getItems().remove(this);
            holder = null;
            resetTranzisztors();
        }
    }

    /**
     * A tranzisztor felvétele
     */
    @Override
    public void setHolder(Person holder){
        this.holder=holder;
        if (holder != null) {
            if(!holder.items.isEmpty()){
                for(BaseItem item: holder.items){
                    if(item.isConnected()!=null && item != this){
                        item.setConnected(this);
                        setConnected(item);
                        active=true;
                        item.active=true;
                        return;
                    }
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

    @Override
    public Boolean canBePickedUp()
    {
        if(!active){
            return true;
        }    
        return false;
    }
    
    @Override
    public Boolean canBeused()
    {
        return active;
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
        if(this.connected!=null){
            connected.active=false;
            connected.connected=null;
        }
        active=false;
        connected=null;
    }

    @Override
    public void putDown(Room r){
        holder = null;
        room = r;
        resetTranzisztors();
    }

    public void tick(){
    }

    @Override
    public void PrintOutItem(PrintWriter writer){
        writer.print(name + " (Active) " +
                active +
                " (Connected) " + ((connected == null) ? "null" : connected.name) + '\n');
    }
}
