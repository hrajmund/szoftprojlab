package Modell;
import Modell.IRound;
import Modell.TestPrinter;

import java.io.PrintWriter;

/**
 * FFP2 maszk tárgy
 */
public class FFP2 extends BaseItem implements IRound {

    private Boolean fake = false;

    /**
     * A tárgy hátralévő ideje
     */
    private int timeUsage=5;

    /**
     *
     */
    public FFP2(){
        name="UnknownFFP2Mask";
    }

    public FFP2(String n){
        name=n;
    }

    public FFP2(String n,int i){
        name=n;
        timeUsage=i;
    }

    /**
     * A tárgy hatását végrehajtó metódus
     */
    public void effect(){
        if(!fake){
            holder.setGasProtection(true);
        }
        active=true;
    }
    /**
     * A tárgyat kezeli körönként
     */
    public void tick(){
        if(active){
            if(timeUsage>0){
                if(timeUsage==1){
                    timeUsage--;
                    holder.removeItem(this);
                    holder=null;
                }else{
                    timeUsage-=1;
                }
            }
        }
    }

    @Override
    public void putDown(Room r){
        holder.setGasProtection(false);
        active = false;
        holder = null;
        room = r;
    }

    public void setFake(Boolean b){
        fake= b;
    }
    @Override
    public void PrintOutItem(PrintWriter writer){
        writer.println(name + " (Active) " +
                active  +
                " (Fake) " + fake + " (TimeUsage) " + timeUsage);
    }
}
