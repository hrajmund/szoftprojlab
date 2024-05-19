package Modell;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    public CursedRoom() {
        super();
    }

    public CursedRoom(String cursedroomName) {
        super(cursedroomName);
    }

    /**
     * Ennek a függvénynek a határásra változnak meg az elátkozott ajtók
     */
    public void tick(){
        if(labyrinth.random){
            cursed();
            if(labyrinth.getGameManager().getGamePanel() != null){
                labyrinth.getGameManager().getGamePanel().getGraphComponent().CurseHappened(this);
            }
        }
    }


    /**
     * Ennek a függvénynek a határásra változnak meg az elátkozott ajtók
     */
    public void cursed(){
        //Random randomNum;
        //if (Szkeleton.DETERMINISTIC_MODE) {
        //    randomNum = new Random(Szkeleton.RAND_INIT_WHEN_DETERMINISTIC); // A kezdőérték, ha determinisztikus módban vagyunk
        //} else {
        //    randomNum = new Random(); // Normál, véletlenszerű módban
        //}
//
        //int rand1 = randomNum.nextInt(incomingDoors.size());
        //for (int i = 0; i < rand1; i++) {
        //    int rand2 = randomNum.nextInt(incomingDoors.size());
        //    cursedIncomingDoors.add(incomingDoors.get(rand2));
        //    removeIncomingDoor(incomingDoors.get(rand2));
        //}
        //int rand3 = randomNum.nextInt(outgoingDoors.size());
        //for (int i = 0; i < rand3; i++) {
        //    int rand4 = randomNum.nextInt(outgoingDoors.size());
        //    cursedOutgoingDoors.add(outgoingDoors.get(rand4));
        //    removeOutgoingDoor(outgoingDoors.get(rand4));
        //}
        //INNEN
        if(outgoingDoors.isEmpty()){
            outgoingDoors.addAll(cursedOutgoingDoors);
            cursedIncomingDoors.clear();

            incomingDoors.addAll(cursedIncomingDoors);
            cursedIncomingDoors.clear();
        }
        else{
            cursedOutgoingDoors.addAll(outgoingDoors);
            outgoingDoors.clear();

            cursedIncomingDoors.addAll(incomingDoors);
            incomingDoors.clear();
        }
        }

    @Override
    public boolean isEnterable(Room r){
        if(!cursedIncomingDoors.contains(r) || people.size() < capacity){
            return true;
        }
        else{
            return false;}
    }

    @Override
    public void PrintOutRoom(PrintWriter writer){
        writer.print(Name +
                "\n    [Gas] " + gas +
                "\n    [Sticky] " + sticky +
                "\n    [Capacity] " + capacity
        );

        writer.print("\n    [Incoming] ");
        this.getIncomingDoors().forEach(incoming -> writer.print(incoming.getName() + " "));
        writer.print("\n    [Outgoing] ");
        this.getOutgoingDoors().forEach(outgoing -> writer.print(outgoing.getName() + " "));
        writer.print('\n');
        if(!items.isEmpty()){
            for(int i = 0; i < items.size(); i++){
                writer.print(String.format("    [Item %d] ", i));
                items.get(i).PrintOutItem(writer);
            }
        }
    }
}
