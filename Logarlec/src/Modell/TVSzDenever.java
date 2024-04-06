package Modell;
/**
 * TVSZDenevér
 */
public class TVSzDenever extends BaseItem{

    private Boolean fake = false;
    private int remainingUsage=3;

    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        TestPrinter.printCallingMethod();
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
    public Boolean protAgainstTeacher(){
        TestPrinter.printCallingMethod();
        //MÓDOSÍTÁS: csak igazi tárgy tud venni
        if(!fake){
            this.effect();
            return true;
        }else{
            return false;
        }
    }
}
