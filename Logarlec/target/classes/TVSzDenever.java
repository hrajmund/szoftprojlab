package Modell;

import java.io.PrintWriter;

/**
 * TVSZDenevér
 */
public class TVSzDenever extends BaseItem{

    private Boolean fake = false;
    private int remainingUsage=3;
    
    public TVSzDenever(){
        name= "UnknownTVSZ";
    }

    public TVSzDenever(String n){
        name= n;
    }

    public TVSzDenever(String n, int i){
        name= n;
        remainingUsage= i;
    }

    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        if(remainingUsage>0){
            if(remainingUsage==1){
                remainingUsage-=1;
                holder.removeItem(this);
                holder=null;
            }else{
                remainingUsage-=1;
            }
        }
    }

    /**
     *A tárgynak ad-e védemet oktatóval szemben
     */
    @Override
    public boolean protAgainstTeacher(){
        //MÓDOSÍTÁS: csak igazi tárgy tud venni
        if(!fake){
            this.effect();
            return true;
        }else{
            return false;
        }
    }

    public void setFake(Boolean b){
        fake= b;
    }

    @Override
    public void tick() {
    }

    @Override
    public void PrintOutItem(PrintWriter writer){
        writer.println(name +
                " (Active) " + active  +
                " (Fake) " + fake + " (RemainingUsage) " + remainingUsage);
    }
}
