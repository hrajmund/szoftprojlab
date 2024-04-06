package Modell;
import Modell.IRound;
import Modell.TestPrinter;

/**
 * FFP2 maszk tárgy
 */
public class FFP2 extends BaseItem implements IRound {
    /**
     * A tárgy hátralévő ideje
     */
    private int timeUsage;
    /**
     * A tárgy hatását végrehajtó metódus
     */
    public void effect(){
        TestPrinter.printCallingMethod();
        if(!fake){
            holder.setGasProtection(true);
        }
        active=true;
    }
    /**
     * A tárgyat kezeli körönként
     */
    public void tick(){
        TestPrinter.printCallingMethod();
        if(active){
            if(timeUsage>0){
                if(timeUsage==1){
                    timeUsage-=1;
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
        TestPrinter.printCallingMethod(holder);
        holder.setGasProtection(false);
        active = false;
        holder = null;
        room = r;
    }


}
