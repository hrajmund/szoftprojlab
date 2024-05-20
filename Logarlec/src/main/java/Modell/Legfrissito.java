package Modell;

import java.io.PrintWriter;

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
        filename = "legfrissito.png";
        active = false;
    }

    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        holder.putDownItem(this);
        room.setGas(false);
        if(room.labyrinth.getGameManager().getGamePanel() != null)
            room.labyrinth.getGameManager().getGamePanel().getGraphComponent().refreshNodes();
        room.removeItem(this);
        room=null;
    }

    @Override
    public Boolean canBePickedUp()
    {
        return true;
    }
    @Override
    public Boolean canBeused()
    {
        return true;
    }
    
    @Override
    public void putDown(Room r){
        holder = null;
        room = r;
    }
    @Override
    public void tick() {}
    @Override
    public void PrintOutItem(PrintWriter writer){
        writer.print(name + " (Active) " + active + '\n');
    }
}
