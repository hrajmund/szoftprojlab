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
}
