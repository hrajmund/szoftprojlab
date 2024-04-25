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
    private int timeUsage = 10;


    public NedvesTablatorlo(){
        name="UnknownTablatorlo";
    }

    public NedvesTablatorlo(String n){
        name=n;
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
    /**
     * A tárgy tud-e elkábítani
     */
    @Override
    public Boolean canStun(){
        return active;
    }
    @Override
    public void PrintOutItem(){
        System.out.print(this.getName() + " (Active) " +
                this.getActive() + " (Holder) " + this.getHolder() +
                " (TimeUsage) " + this.timeUsage);
    }

}
