package Modell;
import Modell.Room;
import Modell.TestPrinter;

import java.io.PrintWriter;

/**
 * Student osztály
 */

public class Student extends Person{
    /**
     * Tanár elleni védelem, amit a SzentSorospohar állítja be
     */
    private boolean teacherProtected = false;
    /**
     * Gáz elleni védelem, amit a FFP2 maszk állítja be
     */
    private boolean gasProtected = false;

    /**
     * Visszaadja a hallgató kapacitását
     */
    @Override
    public int getCapacity(){
        return 0;
    }

    public Student(){
        name = "UnknownStudent";
    }

    public Student(String n){
        name = n;
    }

    /**
     * Megbénítja a hallgatót
     */
    public void stun(){
        if (!this.isGasProtected()){
            this.dropAllItems();
            this.stunned=true;
        }
    }
    /**
     * Védelem a tanár ellen
     */
    public void setTeacherProtection(boolean protection){
        teacherProtected = protection;
    }

    /**
     * Visszaadja, hogy a hallgató védve van-e a tanártól
     * @return
     */
    public boolean isTeacherProtected(){ return teacherProtected;}
    /**
     * Visszaadja, hogy a hallgató védve van-e a gáztól
     * @return
     */
    public boolean isGasProtected(){ return gasProtected;}
    /**
     * Beállítja a hallgató gáz elleni védelmét
     * @param b igazságérték, amie állítni kell
     */
    public void setGasProtection(Boolean b){
        gasProtected = b;
    }
    /**
     * Használ egy tárgyat
     * @param i tárgy sorszáma
     */
    @Override
    public void UseItem(int i){
        items.get(i).effect();
    }
    /**
     * Hozzáad egy tárgyat a hallgatóhoz
     * @param i tárgy, amit hozzá kell adni
     */
    public void addItem(BaseItem i){
        items.add(i);
    }

    /**
     * Eltávolít egy tárgyat a hallgatótól
     * @param i tárgy, amit el kell távolítani
     */
    @Override
    public void removeItem(BaseItem i){
        items.remove(i);
    }
    /**
     * Minden tárgyat eldob a hallgató
     */
    public void dropAllItems(){
        currentRoom.addItems(items);
        items.clear();
    }
    /**
     * A hallgatót megöli
     *
     */
    public void killed() {
        //Ha van nála aktív SzentSorosPoharak, akkor védelme van a Teacher ellen
        if(teacherProtected){
            //teacherProtected=false; //AAAAAA
            //MÓDOSÍTÁS: a még aktív söröskorsót használva a hallgatók elejtik az egyik náluk levő tárgyat.
            putDownItem(getItems().get(0));
            return;
        }
        //Ha nincs aktív SzentSorospoharak, akkor megnézi, hogy van-e nála olyan item, ami megvédi a Teachertől
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
        return true;
    }

    /**
     * A hallgatót mozgatja egy adott szobába
     *
     * @param r Az a szoba, amibe a hallgatót mozgatni kell
     */
    @Override
    public void move(Room r) {
        if(currentRoom.movePossibilities().contains(r)){
            currentRoom.removePerson(this);
            currentRoom = r;
            r.addPerson(this);
            r.increasePersonCounter();
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

    }

    /**
     * A teacher stunolásához használt függvény.
     */
    public void stunTeacher(){}
    /**
     * Ellenőrzi, hogy a hallgató aktiválhatja-e a Tranzisztort
     *
     * @return True, ha a hallgató aktiválhatja a Tranzisztor-t, egyébként False
     */
    public Boolean canActivateTranzisztor(){
        return true;
    }

    @Override
    public void PrintOutPerson(PrintWriter writer){
        writer.print("    " + name +
                "\n        [CurrentRoom] " + currentRoom.Name +
                "\n        [Stun] " + stunned +
                "\n        [TeacherProtected] " + teacherProtected +
                "\n        [GasProtected] " + gasProtected + '\n'
        );
        if(!items.isEmpty()){
            for(int i = 0; i < this.items.size(); i++){
                writer.print(String.format("        [Item %d] ", i));
                items.get(i).PrintOutItem(writer);
            }
        }
    }
}
