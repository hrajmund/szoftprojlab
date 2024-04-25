package Modell;
/**
 * MÓDOSÍTÁS: légfrissítő. Egyszerhasználatos tárgy. Gázos szobában lerakva semlegesíti a gázhatást.
 *A Légfrissítő tárgy
 */
public class Legfrissito extends BaseItem{

    public Legfrissito(){
        name="UnknownFresh";
    }

    public Legfrissito(String n){
        name=n;
    }

    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        holder.putDownItem(this);
        room.setGas(false);
        room.removeItem(this);
        room=null;
    }
    @Override
    public void putDown(Room r){
        holder = null;
        room = r;
    }
    @Override
    public void tick() {}
    @Override
    public void PrintOutItem(){
        System.out.print(this.getName() + " (Active) " + this.getActive() + " (Holder) " + this.getHolder());
    }
}
