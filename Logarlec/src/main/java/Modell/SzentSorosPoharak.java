package Modell;
import Modell.IRound;
import Modell.TestPrinter;

import java.io.PrintWriter;

public class SzentSorosPoharak extends BaseItem implements IRound {

    private int timeUsage=10;

    public SzentSorosPoharak(){
        name="UnknownSzentSorosPohar";
    }

    public SzentSorosPoharak(String n){
        name=n;
    }

    public SzentSorosPoharak(String n,int i){
        name=n;
        timeUsage=i;
    }

    /**
     * Beaktiválja a SzetnSorospohrak tárgyat amely védelmet nyújt a tanárok ellen
     */
    @Override
    public  void effect() {
        active=true;
        holder.setTeacherProtection(true);
    }

    @Override
    public void putDown(Room r) {
        holder.setTeacherProtection(false);
        active = false;
        holder = null;
        room = r;
    }

    /**
     *  A tick egy függvény a tárgy használatának idejét csökkenti, majd ha lejár az ideje törli a hallgató kezéből
     */
    public void tick(){
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
    @Override
    public void PrintOutItem(PrintWriter writer){
        writer.print(name + " (Active) " +
                active +
                " (TimeUsage) " + timeUsage + '\n');
    }
}
