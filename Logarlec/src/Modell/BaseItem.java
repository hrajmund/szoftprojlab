package Modell;
/**
 *A játékban használt tárgyak ősosztálya
 */
public abstract class BaseItem{
    /**
     *A tárgy neve
     */
    protected String name;


    /**
     * A tárgy hamis-e
     */
    protected boolean isFake;

    /**
     *A tárgy tulajdonosa
     */
    protected Person holder;
    /**
     *A tárgy szobája
     */
    protected Room room;

    /**
     *A tárgy aktív-e
     */
    protected Boolean active=false;

    /**
     *A szobájának beállítása
     */
    public void setRoom(Room r){
        if(r == null){
            TestPrinter.printCallingMethod();
        }
        else {
            TestPrinter.printCallingMethod(r);
        }
        room=r;
    }
    /**
     *A tárgy tulajdonosának beállítása
     */
    public void setHolder(Person p) {
        if (p == null){
            TestPrinter.printCallingMethod();
        }
        else {
            TestPrinter.printCallingMethod(p);
        }
        holder = p;
    }
    /**
     *A tárgy hatásának végrehajtása
     */
    public abstract void effect();
    /**
     *A véd-e teacher ellen
     */
    public Boolean protAgainstTeacher(){
        TestPrinter.printCallingMethod();
        return false;
    }
    /**
     *A tárgy tud-e bénítani
     */
    public Boolean canStun(){
        TestPrinter.printCallingMethod();
        return false;
    }
    /**
     *A tárgy aktív-e
     */
    public Boolean getActive(){
        TestPrinter.printCallingMethod();
        return active;
    }
    /**
     *A tárgy aktív állapotának beállítása
     */
    public void setActive(Boolean b){
        TestPrinter.printCallingMethod(b);
        active=b;
    }
    /**
     *A tárgy párjának lekérdezése, minden tárgy esetében ez null-t ad vissza
     */
    public Tranzisztor isConnected(){
        TestPrinter.printCallingMethod();
        return null;
    }
    /**
     *A tárgy párjának beállítása, nem csinál semmit
     */
    public void setConnected(BaseItem t){
        TestPrinter.printCallingMethod(t);
    }

    /**
     *A tárgy lerakása
     * MÓDOSÍTÁS: az Itemek intézik a lerakásukat, a Student csak meghívja rajtuk
     */
    public abstract void putDown(Room r);
}
