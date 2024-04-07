package Modell;
import Modell.IRound;
import Modell.TestPrinter;
/**
 * A nedves táblatörlő tárgy
 */
public class NedvesTablatorlo extends BaseItem implements IRound {
    /**
     * A tárgy hatásának ideje
     */
    private int timeUsage;

    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        TestPrinter.printCallingMethod();
        active = true;
        holder.putDownItem(this);
    }

    /**
     * A tárgy kezelése körönként
     * */
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
    /**
     * A tárgy tud-e elkábítani
     */
    @Override
    public Boolean canStun(){
        TestPrinter.printCallingMethod();
        return active;
    }

}
