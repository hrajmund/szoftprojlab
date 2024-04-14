package Modell;

public class Takarito extends Person{
    //DONE: nulára kell állítsa a szoba personCounterjét.
    //DONE: Ha belép egy szobába, minden mozogni képes (nem bénult / ájult) embert kitessékel onnan
    //DONE: Ha gázos szobába lép, kiszellőztet, megszüntetve a szoba gázosságát.

    @Override
    public void addItem(BaseItem i) {

    }

    @Override
    public void move(Room r) {
        TestPrinter.printCallingMethod(r);
        currentRoom.removePerson(this);
        currentRoom = r;
        currentRoom.gas = false; //"kiszellőztet"
        currentRoom.personCounter = 0; //"kitakarit"
        for (Person p : currentRoom.getPeople()) //"kitessekel"
        {
            if(!p.stunned && p != this){
                getCurrentRoom().outgoingDoors.get(0).addPerson(p);
                p.setCurrentRoom(getCurrentRoom().outgoingDoors.get(0)); //mindenkit ugyanabba a szobába küld, a capacityvel nem foglalkozik, mivel ez elvi síkon is problémát okozna
                //currentRoom.removePerson(p);
            } //TODO: RANDOMIZÁCIÓ AZ ELHELYEZÉSRE
        }
        currentRoom.people.clear();// EZ AZÉRT ITT VAN MIVEL NEM LEHET ELŐTTE BELERAKNI MIVEL A FOREACHNÉL HA KIVENNÉNK EGY EMBERT AKKOR MÁR BORUL A FOREACH
        r.addPerson(this); //Emiatt van csak itt hozzáadva
    }

    /**
     * Kábítja a jelenlegi szobában lévő entitásokat
     */
    @Override
    public void stun() {
    }

    /**
     * N
     */
    @Override
    public void kill(Person p) {
    }

    @Override
    public void killed() {
    }

    @Override
    public void stunTeacher() {
    }

    @Override
    public void tick() {

    }
}
