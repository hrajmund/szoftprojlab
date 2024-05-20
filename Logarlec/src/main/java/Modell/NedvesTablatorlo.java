package Modell;
import Modell.IRound;
import Modell.TestPrinter;

import java.io.PrintWriter;

/**
 * A nedves táblatörlő tárgy
 */
public class NedvesTablatorlo extends BaseItem implements IRound {
    /**
     * A tárgy hatásának ideje
     */
    private int timeUsage = 10;


    public NedvesTablatorlo(){
        name="UnknownTablatorlo";
    }

    public NedvesTablatorlo(String n){
        name=n;
        filename = "tablatorlo.png";
    }

    public NedvesTablatorlo(String n, int i){
        name=n;
        timeUsage=i;
        filename = "tablatorlo.png";
    }

    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        active = true;
        holder.putDownItem(this);
    }

    /**
     * A tárgy kezelése körönként
     * */
    public void tick(){
        if(active){
            if(timeUsage>0){
                if(timeUsage==1){
                    timeUsage-=1;
                    //a szobából eltávolítja a tárgyat, ha lejárt
                    room.removeItem(this);
                    room=null;
                }else{
                    timeUsage-=1;
                }
            }
        }
    }


    @Override
    public Boolean canBePickedUp()
    {
        return !active;
    }
    
    @Override
    public Boolean canBeused()
    {
        return true;
    }
    
    
    /**
     * A tárgy tud-e elkábítani
     */
    @Override
    public Boolean canStun(){
        return active;
    }
    @Override
    public void PrintOutItem(PrintWriter writer){
        writer.print(name + " (Active) " +
                active +
                " (TimeUsage) " + timeUsage + '\n');
    }

}
