package Modell;
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
    public void PrintOutItem(){
        System.out.print(this.getName() + " (Active) " +
                this.getActive() + " (Holder) " + this.getHolder() +
                " (Fake) " + this.fake);
    }
}
