package Modell;

import java.io.PrintWriter;

/**
 * Logarlec tárgy
 */
public class Logarlec extends BaseItem{
    
    private Boolean fake = false;

    public Logarlec(){
        name="UnknownLogarlec";
    }

    public Logarlec(String n){
        name=n;
        filename = "logarlec.png";
        active = true;
    }
    
    /**
     * A tárgy hatását végrehajtó metódus
     */
    @Override
    public void effect() {
        //hamis logarléc nem csinál semmit
    }
    /**
     * A felvételét megvalósító metódus
     */
    @Override
    public void setHolder(Person p) {
        if(p.canPickUpLogarlec()){
            if(!fake){
                holder = p;
                p.labyrinth.endGame();
            }else{
                holder = p;
            }
        }
    }
    @Override
    public void putDown(Room r){
        //ha hamis, le kell tudni rakni
        holder = null;
        room = r;
    }

    public void setFake(Boolean b){
        fake= b;
    }

    @Override
    public void tick() {}
    @Override
    public void PrintOutItem(PrintWriter writer){
        writer.print(name + " (Active) " +
                active +
                " (Fake) " + fake + '\n');
    }

    @Override
    public Boolean getActive(){
        if(holder==null){
            return false;
        }
        return active;
    }
}
