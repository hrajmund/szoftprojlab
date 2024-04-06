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
        holder.setGasProtection(true);
        active=true;
    }
    /**
     * A tárgyat kezeli körönként
     */
    public void tick(){
        TestPrinter.printCallingMethod();
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
