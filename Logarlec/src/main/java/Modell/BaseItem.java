package Modell;

import java.io.PrintWriter;

/**
 *A játékban használt tárgyak ősosztálya
 */
public abstract class BaseItem implements IRound{
    /**
     *A tárgy neve
     */
    protected String name;
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
        room=r;
    }
    /**
     *A tárgy tulajdonosának beállítása
     */
    public void setHolder(Person p) {
        holder = p;
    }
    /**
     *A tárgy tulajdonosát adja vissza
     */
    public Person getHolder(){return holder;}
    /**
     *A tárgy hatásának végrehajtása
     */
    public abstract void effect();
    /**
     *A véd-e teacher ellen
     */
    public boolean protAgainstTeacher(){
        return false;
    }
    /**
     *A tárgy tud-e bénítani
     */
    public Boolean canStun(){
        return false;
    }
    /**
     *A tárgy aktív-e
     */
    public Boolean getActive(){
        return active;
    }
    /**
     *A tárgy aktív állapotának beállítása
     */
    public void setActive(Boolean b){
        active=b;
    }
    /**
     *A tárgy párjának lekérdezése, minden tárgy esetében ez null-t ad vissza
     */
    public Tranzisztor isConnected(){
        return null;
    }
    /**
     *A tárgy párjának beállítása, nem csinál semmit
     */
    public void setConnected(BaseItem t){

    }

    /**
     *A tárgy lerakása
     * MÓDOSÍTÁS: az Itemek intézik a lerakásukat, a Student csak meghívja rajtuk
     */
    public  void putDown(Room r){
        holder = null;
        setRoom(r);
    }
    public String getName(){
        return name;
    }
    public abstract void PrintOutItem(PrintWriter writer);

    public void setFake(Boolean b){}
    public abstract void tick();

}
