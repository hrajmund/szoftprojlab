package Modell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Elátkozott szoba
 */
public class CursedRoom extends Room implements IRound {

    /**
     * Éppen elátkozva lévő bejövő ajtók
     */
    private List<Room> cursedIncomingDoors= new ArrayList<Room>();

    /**
     * Éppen elátkozva lévő kimenő ajtók
     */
    private List<Room> cursedOutgoingDoors= new ArrayList<Room>();

    /**
     * Ennek a függvénynek a határásra változnak meg az elátkozott ajtók
     */
    public void tick(){
        TestPrinter.printCallingMethod();
        cursed();
    }
    /**
     * Ennek a függvénynek a határásra változnak meg az elátkozott ajtók
     */
    public void cursed(){
        TestPrinter.printCallingMethod();
        /*
        Random randomNum = new Random();
        int rand1 = randomNum.nextInt(incomingDoors.size());
        for (int i = 0; i < rand1; i++) {
            int rand2 = randomNum.nextInt(incomingDoors.size());
            cursedIncomingDoors.add(incomingDoors.get(rand2));
            removeIncomingDoor(incomingDoors.get(rand2));
        }
        int rand3 = randomNum.nextInt(outgoingDoors.size());
        for (int i = 0; i < rand3; i++) {
            int rand4 = randomNum.nextInt(outgoingDoors.size());
            cursedOutgoingDoors.add(outgoingDoors.get(rand4));
            removeOutgoingDoor(outgoingDoors.get(rand4));
        }
        */
    }

    @Override
    public boolean isEnterable(Room r){

        if(!cursedIncomingDoors.contains(r) || people.size()<capacity){
            return true;
        }
        return false;
    }
}
