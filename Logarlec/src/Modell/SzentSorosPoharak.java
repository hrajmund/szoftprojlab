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
        active=true;
        holder.setTeacherProtection(true);
    }

    @Override
    public void putDown(Room r) {
        TestPrinter.printCallingMethod(holder);
        holder.setTeacherProtection(false);
        active = false;
        holder = null;
        room = r;
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
                    holder.setTeacherProtection(false);
                    holder.removeItem(this);
                    holder=null;
                }else{
                    timeUsage-=1;
                }
            }
        }
    }
}
