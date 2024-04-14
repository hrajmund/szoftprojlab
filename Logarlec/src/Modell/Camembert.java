package Modell;
/**
 * Camambert tárgy
 */
public class Camembert extends BaseItem{
    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        holder.putDownItem(this);
        room.setGas(true);
        //room.removeItem(this);
        //room=null;
    }
    @Override
    public void putDown(Room r){
        TestPrinter.printCallingMethod(holder);
        holder = null;
        room = r;
    }

    @Override
    public void tick() {
        room.getPeople().forEach(Person::stun);
        room.removeItem(this);
        room = null;

    }
}
