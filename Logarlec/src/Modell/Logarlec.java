package Modell;
/**
 * Logarlec tárgy
 */
public class Logarlec extends BaseItem{
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
    public void setHolder(Person holder) {
        TestPrinter.printCallingMethod(holder);
        if(holder.canPickUpLogarlec()){
            holder.labyrinth.endGame();
        }
    }
    @Override
    public void putDown(Room r){
        TestPrinter.printCallingMethod(holder);
        //ezt nem rakod le
    }
}
