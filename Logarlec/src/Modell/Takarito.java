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
        r.addPerson(this);
        currentRoom = r;
        currentRoom.gas = false; //"kiszellőztet"
        currentRoom.personCounter = 0; //"kitakarit"
        for (Person p : getCurrentRoom().getPeople()) //"kitessekel"
        {
            if(!p.stunned && p != this){
                p.setCurrentRoom(getCurrentRoom().outgoingDoors.get(0)); //mindenkit ugyanabba a szobába küld, a capacityvel nem foglalkozik, mivel ez elvi síkon is problémát okozna
                currentRoom.removePerson(p);
            } //TODO: RANDOMIZÁCIÓ AZ ELHELYEZÉSRE
        }
    }

    @Override
    public void stun() {
    }

    @Override
    public void kill(Person p) {
    }

    @Override
    public void killed() {
    }

    @Override
    public void stunTeacher() {
    }
}
