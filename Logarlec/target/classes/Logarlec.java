package Modell;
/**
 * Logarlec tárgy
 */
public class Logarlec extends BaseItem{
    
    private Boolean fake = false;
    
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
        if (p == null){
            TestPrinter.printCallingMethod();
        }
        else {
            TestPrinter.printCallingMethod(p);
        }
        if(p.canPickUpLogarlec()){
            if(!fake){
                p.labyrinth.endGame();
            }else{
                holder = p;
            }
        }
    }
    @Override
    public void putDown(Room r){
        TestPrinter.printCallingMethod(holder);
        //ezt nem rakod le
    }

    public void setFake(Boolean b){
        fake= b;
    }
}
