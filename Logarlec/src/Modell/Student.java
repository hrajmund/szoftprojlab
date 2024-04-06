package Modell;
import Modell.Room;
import Modell.TestPrinter;
/**
 * Student osztály
 */

public class Student extends Person{
    /**
     * Tanár elleni védelem, amit a SzentSorospohar állítja be
     */
    private boolean teacherProtected;
    /**
     * Gáz elleni védelem, amit a FFP2 maszk állítja be
     */
    private boolean gasProtected;

    /**
     * Visszaadja a hallgató kapacitását
     */
    @Override
    public int getCapacity(){
        TestPrinter.printCallingMethod();
        return 0;
    }
    /**
     * Megbénítja a hallgatót
     */
    public void stun(){
        TestPrinter.printCallingMethod();
        if (!this.isGasProtected()){
            this.dropAllItems();
            this.stunned=true;
        }
    }
    /**
     * Védelem a tanár ellen
     */
    @Override
    public void setTeacherProtection(boolean protection){
        TestPrinter.printCallingMethod(protection);
        teacherProtected = protection;
    }

    /**
     * Visszaadja, hogy a hallgató védve van-e a tanártól
     * @return
     */
    public boolean isTeacherProtected(){ TestPrinter.printCallingMethod(); return teacherProtected;}
    /**
     * Visszaadja, hogy a hallgató védve van-e a gáztól
     * @return
     */
    public boolean isGasProtected(){TestPrinter.printCallingMethod(); return gasProtected;}
    /**
     * Beállítja a hallgató gáz elleni védelmét
     * @param b igazságérték, amie állítni kell
     */
    @Override
    public void setGasProtection(Boolean b){
        TestPrinter.printCallingMethod();
        gasProtected = b;
    }
    /**
     * Használ egy tárgyat
     * @param i tárgy sorszáma
     */
    public void UseItem(int i){
        TestPrinter.printCallingMethod();
        items.get(i).effect();
    }
    /**
     * Hozzáad egy tárgyat a hallgatóhoz
     * @param i tárgy, amit hozzá kell adni
     */
    public void addItem(BaseItem i){
        TestPrinter.printCallingMethod(i);
        items.add(i);
    }

    /**
     * Eltávolít egy tárgyat a hallgatótól
     * @param i tárgy, amit el kell távolítani
     */
    @Override
    public void removeItem(BaseItem i){
        TestPrinter.printCallingMethod(i);
        items.remove(i);
    }
    /**
     * Minden tárgyat eldob a hallgató
     */
    public void dropAllItems(){
        TestPrinter.printCallingMethod();
    }
    /**
     * A hallgatót megöli
     *
     */
    public void killed() {
        TestPrinter.printCallingMethod();
        //Ha van nála aktív SzentSorosPoharak, akkor védelme van a Teacher ellen
        if(teacherProtected){
            teacherProtected=false; //AAAAAA
            //MÓDOSÍTÁS: a még aktív söröskorsót használva a hallgatók elejtik az egyik náluk levő tárgyat.
            putDownItem(getItems().get(0));
            return;
        }
        //Ha nincs aktív SzentSorospohara, akkor megnézi, hogy van-e nála olyan item, ami megvédi a Teachertől
        else{
            for(BaseItem item : items) {
                if (item.protAgainstTeacher()) {
                    return;
                }
            }
        }
        this.dropAllItems();
        currentRoom.removePerson(this);
        labyrinth.removeStudent(this);
    }
    /**
     * Ellenőrzi, hogy a hallgató felvehette-e a Logarlec-et
     *
     * @return True, ha a hallgató felvehette a Logarlec-et, egyébként False
     */
    public Boolean canPickUpLogarlec(){
        TestPrinter.printCallingMethod();
        return true;
    }

    /**
     * A hallgatót mozgatja egy adott szobába
     *
     * @param r Az a szoba, amibe a hallgatót mozgatni kell
     */
    @Override
    public void move(Room r) {
        TestPrinter.printCallingMethod(r);
        if(currentRoom.movePossibilities().contains(r)){
            currentRoom.removePerson(this);
            r.addPerson(this);
            currentRoom = r;
            for(Person p : r.getPeople()){
                p.kill(this);
            }
        }
    }

    /**
     * A hallgatót megöli egy másik személy
     *
     * @param p Az a személy, aki megölte a hallgatót
     */
    public void kill(Person p){
        TestPrinter.printCallingMethod(p);
    }

    /**
     * A teacher stunolásához használt függvény.
     */
    public void stunTeacher(){TestPrinter.printCallingMethod();}
    /**
     * Ellenőrzi, hogy a hallgató aktiválhatja-e a Tranzisztort
     *
     * @return True, ha a hallgató aktiválhatja a Tranzisztor-t, egyébként False
     */
    public Boolean canActivateTranzisztor(){
        TestPrinter.printCallingMethod();
        return true;
    }

}
