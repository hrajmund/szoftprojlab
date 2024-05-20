package Modell;

import java.io.PrintWriter;

/**
 * Camambert tárgy
 */
public class Camembert extends BaseItem{
    
    public Camembert(){
        name="UnknownCamembert";
    }

    public Camembert(String n){
        name=n;
        filename = "camembert.png";
    }
    
    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        holder.putDownItem(this);
        room.setGas(true);
        room.removeItem(this);
        if(room.labyrinth.getGameManager().getGamePanel() != null)
            room.labyrinth.getGameManager().getGamePanel().getGraphComponent().refreshNodes();
        
        //room.removeItem(this);
        //room=null;
    }
    @Override
    public void putDown(Room r){
        holder = null;
        room = r;
    }
    
    @Override
    public Boolean canBePickedUp()
    {
        return true;
    }
    @Override
    public Boolean canBeused()
    {
        return false;
    }

    @Override
    public void tick() {
    }

    @Override
    public void PrintOutItem(PrintWriter writer){
        writer.print(name + " (Active) " + active + '\n');
    }
}
