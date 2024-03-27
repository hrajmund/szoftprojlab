package Modell;
import Modell.IRound;
import Modell.TestPrinter;

public class SzentSorosPoharak extends BaseItem implements IRound {
    private int timeUsage=10;

    /**
     * Beaktiválja a SzetnSorospohrak tárgyat amely védelmet nyújt a tanárok ellen
     */
    @Override
    public  void effect() {
        TestPrinter.printCallingMethod();
        holder.setTeacherProtection(true);
    }

    /**
     *  A tick egy függvény a tárgy használatának idejét csökkenti, majd ha lejár az ideje törli a hallgató kezéből
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
}
