package Modell;

import java.io.PrintWriter;

/**
 * Camambert tárgy
 */
public class Camembert extends BaseItem{
    
    public Camembert(){
        name="UnknownCamambert";
    }

    public Camembert(String n){
        name=n;
    }
    
    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        holder.putDownItem(this);
        room.setGas(true);
        //room.removeItem(this);
        //room=null;
    }
    @Override
    public void putDown(Room r){
        holder = null;
        room = r;
    }

    @Override
    public void tick() {
        room.getPeople().forEach(Person::stun);
        room.removeItem(this);
        room = null;

    }
    @Override
    public void PrintOutItem(PrintWriter writerPrintWriter writerPrintWriter writer){
        System.out.print(name + " (Active) " + active);
    }
}
