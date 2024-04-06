package Modell;
/**
 * TVSZDenevér
 */
public class TVSzDenever extends BaseItem{
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
        this.effect();
        return true;
    }
    @Override
    public void putDown(Room r){
        TestPrinter.printCallingMethod(holder);
        holder = null;
        room = r;
    }
}
