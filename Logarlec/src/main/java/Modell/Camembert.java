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
        room.removeItem(this);
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


    }

    @Override
    public void PrintOutItem(PrintWriter writer){
        writer.print(name + " (Active) " + active);
    }
}
