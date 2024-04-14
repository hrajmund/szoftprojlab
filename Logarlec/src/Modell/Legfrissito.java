package Modell;
/**
 * MÓDOSÍTÁS: légfrissítő. Egyszerhasználatos tárgy. Gázos szobában lerakva semlegesíti a gázhatást.
 *A Légfrissítő tárgy
 */
public class Legfrissito extends BaseItem{
    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        TestPrinter.printCallingMethod(holder);
        holder.putDownItem(this);
        room.setGas(false);
        room.removeItem(this);
        room=null;
    }
    @Override
    public void putDown(Room r){
        TestPrinter.printCallingMethod(holder);
        holder = null;
        room = r;
    }
    @Override
    public void tick() {}
}
